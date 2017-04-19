package Readers.garrett_langfeld;

/**
 * Created by glang on 4/10/2017.
 */
import org.apache.avro.generic.GenericData;
import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.util.ClassPathResource;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.deeplearning4j.eval.meta.Prediction;
import org.deeplearning4j.util.ModelSerializer;


public class dl4J_Model_To_Export_All_Indust {
    private static Logger log = LoggerFactory.getLogger(dl4J_NN_1Q_Ahead_Compustat_1.class);

    public static void main(String[] args) throws  Exception {


        //First: get the dataset using the record reader. CSVRecordReader handles loading/parsing
        int numLinesToSkip = 1;
        String delimiter = ",";
        RecordReader recordReader = new CSVRecordReader(numLinesToSkip,delimiter);
        //recordReader.initialize(new FileSplit(new File("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/More_Data_for_NN_1Q_Ahead_wo_rating_5_labels4.csv")));
        recordReader.initialize(new FileSplit(new File("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Compustat/Compustat_Data_NN_2.csv")));

        //Second: the RecordReaderDataSetIterator handles conversion to DataSet objects, ready for use in neural network
        int labelIndex = 17;     //5 values in each row of the iris.txt CSV: 4 input features followed by an integer label (class) index. Labels are the 5th value (index 4) in each row
        int numClasses = 5;     //
        //int batchSize = 1253;    //
        int batchSize = 8782;

        DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader,batchSize,labelIndex,numClasses);
        DataSet allData = iterator.next();
        allData.shuffle();
        //SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(0.65);  //Use 65% of data for training


        //list to keep track of wrong predictions
        //ArrayList<Prediction> wrong = new ArrayList<Prediction>();
        List<Prediction> wrong = new ArrayList<Prediction>();

            //creating trainingData
            DataSet trainingData = new DataSet();
            int split = (int) (batchSize * 0.7);
            trainingData = (DataSet) allData.getRange(0, split);
            DataSet testData = new DataSet();
            testData = (DataSet) allData.getRange(split + 1, batchSize - 1);

            //We need to normalize our data. We'll use NormalizeStandardize (which gives us mean 0, unit variance):
            DataNormalization normalizer = new NormalizerStandardize();
            normalizer.fit(trainingData);           //Collect the statistics (mean/stdev) from the training data. This does not modify the input data
            normalizer.transform(trainingData);     //Apply normalization to the training data
            normalizer.transform(testData);         //Apply normalization to the test data. This is using statistics calculated from the *training* set


            final int numInputs = 18;
            int outputNum = 5;
            int iterations = 3000;
            long seed = 6;


            log.info("Build model....");
            MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                    .seed(seed)
                    .iterations(iterations)
                    .activation(Activation.TANH)
                    .weightInit(WeightInit.XAVIER)
                    .learningRate(0.1)
                    .regularization(true).l2(1e-4)
                    .list()
                    .layer(0, new DenseLayer.Builder().nIn(numInputs).nOut(15)
                            .build())
                    .layer(1, new DenseLayer.Builder().nIn(15).nOut(10)
                            .build())
                    .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                            .activation(Activation.SOFTMAX)
                            .nIn(10).nOut(outputNum).build())
                    .backprop(true).pretrain(false)
                    .build();

            //run the model
            MultiLayerNetwork model = new MultiLayerNetwork(conf);
            model.init();
            model.setListeners(new ScoreIterationListener(100));

            model.fit(trainingData);

            //evaluate the model on the test set
            Evaluation eval = new Evaluation(5);
            INDArray output = model.output(testData.getFeatureMatrix());
            //eval.eval(testData.getLabels(), output);
            java.util.List<? extends java.io.Serializable> recordMetaData = new ArrayList<>(batchSize);
            eval.eval(testData.getLabels(), output, recordMetaData);
            //log.info(eval.stats());
            ArrayList<Prediction> errors = new ArrayList<Prediction>();
            //List<Prediction> errors2 = eval.eval(testData.getLabels(), output).getPredictionErrors();
            List<Prediction> errors2 = eval.getPredictionErrors();
            log.info(eval.stats());
            File locationToSave = new File("dl4J_All_Indust.zip");      //Where to save the network. Note: the file is in .zip format - can be opened externally
            boolean saveUpdater = true;                                             //Updater: i.e., the state for Momentum, RMSProp, Adagrad etc. Save this if you want to train your network more in the future
            ModelSerializer.writeModel(model, locationToSave, saveUpdater);

    }


}

