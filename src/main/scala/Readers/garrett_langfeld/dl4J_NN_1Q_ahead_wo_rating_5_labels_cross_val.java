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
//importing Kfold cross-validation
import org.nd4j.linalg.dataset.api.iterator.KFoldIterator;
import java.util.Collections;
import org.deeplearning4j.optimize.api.IterationListener;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;


/**
 * Created by glang on 3/11/2017.
 */
public class dl4J_NN_1Q_ahead_wo_rating_5_labels_cross_val {

    private static Logger log = LoggerFactory.getLogger(dl4J_NN_1Q_ahead_wo_rating_5_labels_cross_val.class);

    public static void main(String[] args) throws  Exception {


        /*
        //First: get the dataset using the record reader. CSVRecordReader handles loading/parsing
        int numLinesToSkip = 1;
        String delimiter = ",";
        RecordReader recordReader = new CSVRecordReader(numLinesToSkip,delimiter);
        recordReader.initialize(new FileSplit(new File("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Data_for_NN_1Q_Ahead_wo_rating_5_labels.csv")));

        //Second: the RecordReaderDataSetIterator handles conversion to DataSet objects, ready for use in neural network
        int labelIndex = 15;
        int numClasses = 5;     //
        int batchSize = 1253;    //

        DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader,batchSize,labelIndex,numClasses);

        DataSet allData = iterator.next();

        //DataSet allData = new DataSet(RecordReader.getLabels());
        //KFoldIterator KFIterator = new KFoldIterator(5, allData);

        allData.shuffle();
        SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(0.65);  //Use 65% of data for training

        DataSet trainingData = testAndTrain.getTrain();
        DataSet testData = testAndTrain.getTest();

        */

        /*
            DataSet trainingData = iterator.next();
            DataSet testData = iterator.testFold();
        //System.out.println(testData.numExamples());
        */

        //First: get the dataset using the record reader. CSVRecordReader handles loading/parsing
        int numLinesToSkip = 1;
        String delimiter = ",";
        RecordReader recordReader1 = new CSVRecordReader(numLinesToSkip,delimiter);
        recordReader1.initialize(new FileSplit(new File("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/1Q_Ahead_CV/fold2_train.csv")));

        //Second: the RecordReaderDataSetIterator handles conversion to DataSet objects, ready for use in neural network
        int labelIndex = 15;
        int numClasses = 5;     //
        //batch size for training fold
        //int batchSize1 = 1253;
        int batchSize1 = 1128;
        //batch size for testing fold
        int batchSize2 = 125;

        DataSetIterator iterator1 = new RecordReaderDataSetIterator(recordReader1,batchSize1,labelIndex,numClasses);
        DataSet trainingData = iterator1.next();

        RecordReader recordReader2 = new CSVRecordReader(numLinesToSkip,delimiter);
        recordReader2.initialize(new FileSplit(new File("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/1Q_Ahead_CV/fold2_test.csv")));

        DataSetIterator iterator2 = new RecordReaderDataSetIterator(recordReader2,batchSize2,labelIndex,numClasses);
        DataSet testData = iterator2.next();

        //DataSet allData = iterator.next();

        //DataSet allData = new DataSet(RecordReader.getLabels());
        //KFoldIterator KFIterator = new KFoldIterator(5, allData);

        //allData.shuffle();
        //SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(0.65);  //Use 65% of data for training

        //DataSet trainingData = testAndTrain.getTrain();
        //DataSet testData = testAndTrain.getTest();


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
            //model.setListeners(new ScoreIterationListener(100));
            model.setListeners(Collections.singletonList((IterationListener) new ScoreIterationListener(100)));


            model.fit(trainingData);

            //evaluate the model on the test set
            Evaluation eval = new Evaluation(5);
            INDArray output = model.output(testData.getFeatureMatrix());
            eval.eval(testData.getLabels(), output);
            log.info(eval.stats());


        /*
        // Training
        while(iterator.hasNext()){
            System.out.println(iterator.hasNext());
            DataSet next = iterator.next();
            model.fit(next);
        }

        iterator.reset();
        Evaluation eval = new Evaluation();
        while(iterator.hasNext()){
            DataSet next = iterator.next();
            INDArray predict2 = model.output(next.getFeatureMatrix());
            eval.eval(next.getLabels(), predict2);
        }


        //Training
        /*
        Evaluation eval = new Evaluation(5);
        while(KFIterator.hasNext()){
            //System.out.println(KFIterator.hasNext());
            DataSet next = KFIterator.next();
            model.fit(next);
            DataSet test = KFIterator.testFold();
            INDArray predict2 = model.output(test.getFeatureMatrix());
            eval.eval(test.getLabels(), predict2);
        }


        KFIterator.reset();
        //Evaluation eval = new Evaluation(5);
        while(KFIterator.hasNext()){
            DataSet next = KFIterator.next();
            DataSet test = KFIterator.testFold();
            INDArray predict2 = model.output(test.getFeatureMatrix());
            eval.eval(test.getLabels(), predict2);
        }

        System.out.println(eval.stats());
        */

    }


}
