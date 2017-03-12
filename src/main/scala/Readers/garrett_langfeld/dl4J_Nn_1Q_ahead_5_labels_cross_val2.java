package Readers.garrett_langfeld;

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
import java.util.ArrayList;
import java.util.List;


/**
 * Created by glang on 3/12/2017.
 */
public class dl4J_Nn_1Q_ahead_5_labels_cross_val2 {

    private static Logger log = LoggerFactory.getLogger(dl4J_NN_1Q_ahead_wo_rating_5_labels.class);

    public static void main(String[] args) throws  Exception {


        //First: get the dataset using the record reader. CSVRecordReader handles loading/parsing
        int numLinesToSkip = 1;
        String delimiter = ",";
        RecordReader recordReader = new CSVRecordReader(numLinesToSkip,delimiter);
        recordReader.initialize(new FileSplit(new File("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Data_for_NN_1Q_Ahead_wo_rating_5_labels.csv")));

        //Second: the RecordReaderDataSetIterator handles conversion to DataSet objects, ready for use in neural network
        int labelIndex = 15;     //5 values in each row of the iris.txt CSV: 4 input features followed by an integer label (class) index. Labels are the 5th value (index 4) in each row
        int numClasses = 5;     //
        //int batchSize = 1253;    //
        int batchSize = 1253;

        DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader,batchSize,labelIndex,numClasses);
        DataSet allData = iterator.next();
        allData.shuffle();
        //SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(0.65);  //Use 65% of data for training

        //for loop for cross validation to run k=10 times
        int h = 0;
        int i = batchSize - batchSize/10 - 1;
        int j = batchSize - 1;
        for(int k = 0; k < 10; k++) {
            DataSet trainingData1 = new DataSet();
            DataSet trainingData2 = new DataSet();
            if (i > 0) {
                trainingData1 = (DataSet) allData.getRange(0, i);
            }
            DataSet testData = (DataSet) allData.getRange(i + 1, j);
            if (j < batchSize - 1) {
                trainingData2 = (DataSet) allData.getRange(j + 1, batchSize - 1);
            }

            //creating trainingData
            DataSet trainingData = new DataSet();
            if (j == batchSize - 1){
                trainingData = trainingData1;
            }
            else if (j == batchSize/10 - 1){
                trainingData = trainingData2;
            }
            else{
                ArrayList<DataSet> L = new ArrayList<DataSet>();
                L.add(trainingData1);
                L.add(trainingData2);
                trainingData = DataSet.merge(L);
            }

            i -= batchSize/10;
            j -= batchSize/10;

            //We need to normalize our data. We'll use NormalizeStandardize (which gives us mean 0, unit variance):
            DataNormalization normalizer = new NormalizerStandardize();
            normalizer.fit(trainingData);           //Collect the statistics (mean/stdev) from the training data. This does not modify the input data
            normalizer.transform(trainingData);     //Apply normalization to the training data
            normalizer.transform(testData);         //Apply normalization to the test data. This is using statistics calculated from the *training* set


            final int numInputs = 17;
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
            eval.eval(testData.getLabels(), output);
            log.info(eval.stats());
        }
    }



}
