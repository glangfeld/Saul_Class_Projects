package Readers.garrett_langfeld;

/**
 * Created by glang on 3/30/2017.
 */

import org.apache.avro.generic.GenericData;
import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.Record;
import org.datavec.api.records.metadata.RecordMetaData;
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

import org.datavec.api.records.Record;
import org.datavec.api.records.metadata.RecordMetaData;
import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.util.ClassPathResource;
import org.datavec.api.writable.Writable;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.eval.meta.Prediction;
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
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class dl4J_NN_1Q_Ahead_Compustat_Industries_1 {

    private static Logger log = LoggerFactory.getLogger(dl4J_NN_1Q_Ahead_Compustat_Industries_1.class);

    public static void main(String[] args) throws  Exception {

        //variables to keep track of accuracy, precision, recall, f1
        double tot_accuracy = 0;
        double tot_precision = 0;
        double tot_recall = 0;
        double tot_f1 = 0;
        double tot_precision_0 = 0;
        double tot_recall_0 = 0;
        double tot_f1_0 = 0;
        double tot_precision_1 = 0;
        double tot_recall_1 = 0;
        double tot_f1_1 = 0;
        double tot_precision_2 = 0;
        double tot_recall_2 = 0;
        double tot_f1_2 = 0;
        double tot_precision_3 = 0;
        double tot_recall_3 = 0;
        double tot_f1_3 = 0;
        double tot_precision_4 = 0;
        double tot_recall_4 = 0;
        double tot_f1_4 = 0;


        //iterating through different industry CSV files
        String[] files = new String[8];
        files[0] = "/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Compustat/By_Industry/Compustat_Data_NN_2_Consumer_Discretionary.csv";
        files[1] = "/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Compustat/By_Industry/Compustat_Data_NN_2_Consumer_Staples.csv";
        files[2] = "/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Compustat/By_Industry/Compustat_Data_NN_2_Energy.csv";
        files[3] = "/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Compustat/By_Industry/Compustat_Data_NN_2_Health_Care.csv";
        files[4] = "/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Compustat/By_Industry/Compustat_Data_NN_2_Industrials.csv";
        files[5] = "/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Compustat/By_Industry/Compustat_Data_NN_2_IT.csv";
        files[6] = "/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Compustat/By_Industry/Compustat_Data_NN_2_Materials.csv";
        files[7] = "/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Compustat/By_Industry/Compustat_Data_NN_2_Telecom.csv";

        int[] num_examples = new int[8];
        num_examples[0] = 827;
        num_examples[1] = 1355;
        num_examples[2] = 1298;
        num_examples[3] = 1304;
        num_examples[4] = 1322;
        num_examples[5] = 1126;
        num_examples[6] = 1046;
        num_examples[7] = 504;

        for (int it = 0; it < 8; it++) {

            //First: get the dataset using the record reader. CSVRecordReader handles loading/parsing
            int numLinesToSkip = 1;
            String delimiter = ",";
            RecordReader recordReader = new CSVRecordReader(numLinesToSkip, delimiter);
            //recordReader.initialize(new FileSplit(new File("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/More_Data_for_NN_1Q_Ahead_wo_rating_5_labels4.csv")));
            recordReader.initialize(new FileSplit(new File(files[it])));

            //Second: the RecordReaderDataSetIterator handles conversion to DataSet objects, ready for use in neural network
            int labelIndex = 17;     //5 values in each row of the iris.txt CSV: 4 input features followed by an integer label (class) index. Labels are the 5th value (index 4) in each row
            int numClasses = 5;     //
            //int batchSize = 1253;    //
            int batchSize = num_examples[it];

            //DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader, batchSize, labelIndex, numClasses);
            RecordReaderDataSetIterator iterator = new RecordReaderDataSetIterator(recordReader,batchSize,labelIndex,numClasses);
            iterator.setCollectMetaData(true);
            DataSet allData = iterator.next();
            allData.shuffle();
            //SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(0.65);  //Use 65% of data for training

            //arrays to keep track of accuracy, recall, precision, f1
            //double[] accuracy = new double[10];
            //double[] precision = new double[10];
            double sum_accuracy = 0;
            double sum_precision = 0;
            double sum_recall = 0;
            double sum_f1 = 0;
            double precision_0 = 0;
            double recall_0 = 0;
            double f1_0 = 0;
            double precision_1 = 0;
            double recall_1 = 0;
            double f1_1 = 0;
            double precision_2 = 0;
            double recall_2 = 0;
            double f1_2 = 0;
            double precision_3 = 0;
            double recall_3 = 0;
            double f1_3 = 0;
            double precision_4 = 0;
            double recall_4 = 0;
            double f1_4 = 0;

            int act_0 = 0;
            int act_1 = 0;
            int act_2 = 0;
            int act_3 = 0;
            int act_4 = 0;


            //list to keep track of wrong predictions
            //ArrayList<Prediction> wrong = new ArrayList<Prediction>();
            List<Prediction> wrong = new ArrayList<Prediction>();

            //for loop for cross validation to run k=10 times
            int i = batchSize - batchSize / 10 - 1;
            int j = batchSize - 1;
            //int k = 0;
            for (int k = 0; k < 10; k++) {
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
                if (j == batchSize - 1) {
                    trainingData = trainingData1;
                } else if (j == batchSize / 10 - 1) {
                    trainingData = trainingData2;
                } else {
                    ArrayList<DataSet> L = new ArrayList<DataSet>();
                    L.add(trainingData1);
                    L.add(trainingData2);
                    //System.out.println(L.isEmpty());
                    trainingData = DataSet.merge(L);
                }

                i -= batchSize / 10;
                j -= batchSize / 10;

                //We need to normalize our data. We'll use NormalizeStandardize (which gives us mean 0, unit variance):
                DataNormalization normalizer = new NormalizerStandardize();
                normalizer.fit(trainingData);           //Collect the statistics (mean/stdev) from the training data. This does not modify the input data
                normalizer.transform(trainingData);     //Apply normalization to the training data
                normalizer.transform(testData);         //Apply normalization to the test data. This is using statistics calculated from the *training* set


                final int numInputs = 17;
                int outputNum = 5;
                int iterations = 3000;
                long seed = 6;


                //log.info("Build model....");
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
                java.util.List<? extends java.io.Serializable> recordMetaData = new ArrayList<>(batchSize / 10);
                //List<RecordMetaData> testMetaData = testData.getExampleMetaData(RecordMetaData.class);
                //List<? extends java.io.Serializable> testMetaData = testData.getExampleMetaData();
                //trainingData.getExampleMetaData();
                //eval.eval(testData.getLabels(), output, recordMetaData);
                //log.info(eval.stats());
                ArrayList<Prediction> errors = new ArrayList<Prediction>();
                //List<Prediction> errors2 = eval.eval(testData.getLabels(), output).getPredictionErrors();
                List<Prediction> errors2 = eval.getPredictionErrors();

            /*
            if(!output.equals(iterator.next().getLabels())){
                System.out.println(output);
//do something;
            }
            */
                //System.out.println(testData.get(2));
                //errors.addAll(errors2);
                //System.out.println(wrong.size());
                //System.out.println(errors2.size());
            /*
            if (wrong.size() == 0){
                wrong = errors2;
            }
            else {
                wrong.addAll(errors2);
            }
            */
                //wrong.addAll(errors2);

                //accuracy[k] = eval.accuracy();
                //precision[k] = eval.precision();
                sum_accuracy += eval.accuracy();
                sum_precision += eval.precision();
                sum_recall += eval.recall();
                sum_f1 += eval.f1();

                precision_0 += eval.precision(0);
                recall_0 += eval.recall(0);
                f1_0 += eval.f1(0);
                act_0 += eval.classCount(0);
                //List<Prediction> list1 = eval.getPredictionByPredictedClass(0);
                //pred_0 = eval.truePositives().size();
                //pred_0 = eval.falsePositiveRate(0) * batchSize;

                precision_1 += eval.precision(1);
                recall_1 += eval.recall(1);
                f1_1 += eval.f1(1);
                act_0 += eval.classCount(1);

                precision_2 += eval.precision(2);
                recall_2 += eval.recall(2);
                f1_2 += eval.f1(2);
                act_0 += eval.classCount(2);

                precision_3 += eval.precision(3);
                recall_3 += eval.recall(3);
                f1_3 += eval.f1(3);
                act_0 += eval.classCount(3);

                precision_4 += eval.precision(4);
                recall_4 += eval.recall(4);
                f1_4 += eval.f1(4);
                act_0 += eval.classCount(4);


            }


            System.out.println("Results for Industry " + it);
            sum_accuracy = sum_accuracy / (10);
            sum_precision = sum_precision / 10;
            sum_recall = sum_recall / 10;
            sum_f1 = sum_f1 / 10;
            System.out.println("Accuracy: " + sum_accuracy);
            System.out.println("Precision: " + sum_precision);
            System.out.println("Recall: " + sum_recall);
            System.out.println("F1: " + sum_f1);
            System.out.println();
            System.out.println("Label 0 Results");
            precision_0 = precision_0 / 10;
            System.out.println("Precision: " + precision_0);
            recall_0 = recall_0/10;
            System.out.println("Recall: " + recall_0/10);
            f1_0 = f1_0 / 10;
            System.out.println("F1: " + f1_0/10);
            System.out.println("Actual examples in class 0: " + act_0);
            precision_1 = precision_1 / 10;
            recall_1 = recall_1 / 10;
            f1_1 = f1_1 / 10;
            precision_2 = precision_2 / 10;
            recall_2 = recall_2 / 10;
            f1_2 = f1_2 / 10;
            precision_3 = precision_3 / 10;
            recall_3 = recall_3 / 10;
            f1_3 = f1_3 / 10;
            precision_4 = precision_4 / 10;
            recall_4 = recall_4 / 10;
            f1_4 = f1_4 / 10;

            //fraction to scale each accuracy measure for weighted total average
            double m = (double)batchSize/8782.0;
            tot_accuracy += (m * sum_accuracy);
            tot_precision += (m * sum_precision);
            tot_recall += (m * sum_recall);
            tot_f1 += (m * sum_f1);
            tot_precision_0 += (m * precision_0);
            tot_recall_0 += (m * recall_0);
            tot_f1_0 += (m * f1_0);
            tot_precision_1 += (m * precision_1);
            tot_recall_1 += (m * recall_1);
            tot_f1_1 += (m * f1_1);
            tot_precision_2 += (m * precision_2);
            tot_recall_2 += (m * recall_2);
            tot_f1_2 += (m * f1_2);
            tot_precision_3 += (m * precision_3);
            tot_recall_3 += (m * recall_3);
            tot_f1_3 += (m * f1_3);
            tot_precision_4 += (m * precision_4);
            tot_recall_4 += (m * recall_4);
            tot_f1_4 += (m * f1_4);

            System.out.println();
            System.out.println("Label 1 Results");
            System.out.println("Precision: " + precision_1);
            System.out.println("Recall: " + recall_1);
            System.out.println("F1: " + f1_1);
            System.out.println("Actual examples in class 1: " + act_1);
            System.out.println();
            System.out.println("Label 2 Results");
            System.out.println("Precision: " + precision_2);
            System.out.println("Recall: " + recall_2);
            System.out.println("F1: " + f1_2);
            System.out.println("Actual examples in class 2: " + act_2);
            System.out.println();
            System.out.println("Label 3 Results");
            System.out.println("Precision: " + precision_3);
            System.out.println("Recall: " + recall_3);
            System.out.println("F1: " + f1_3);
            System.out.println("Actual examples in class 3: " + act_3);
            System.out.println();
            System.out.println("Label 4 Results");
            System.out.println("Precision: " + precision_4);
            System.out.println("Recall: " + recall_4);
            System.out.println("F1: " + f1_4);
            System.out.println("Actual examples in class 4: " + act_4);
            /*
            System.out.println();
            System.out.println("m: " + m);
            System.out.println();
            */

        }

        System.out.println("Overall Results");
        System.out.println("Accuracy: " + tot_accuracy);
        System.out.println("Precision: " + tot_precision);
        System.out.println("Recall: " + tot_recall);
        System.out.println("F1: " + tot_f1);
        System.out.println();
        System.out.println("Label 0 Results");
        System.out.println("Precision: " + tot_precision_0);
        System.out.println("Recall: " + tot_recall_0/10);
        System.out.println("F1: " + tot_f1_0/10);
        System.out.println();
        System.out.println("Label 1 Results");
        System.out.println("Precision: " + tot_precision_1);
        System.out.println("Recall: " + tot_recall_1);
        System.out.println("F1: " + tot_f1_1);
        System.out.println();
        System.out.println("Label 2 Results");
        System.out.println("Precision: " + tot_precision_2);
        System.out.println("Recall: " + tot_recall_2);
        System.out.println("F1: " + tot_f1_2);
        System.out.println();
        System.out.println("Label 3 Results");
        System.out.println("Precision: " + tot_precision_3);
        System.out.println("Recall: " + tot_recall_3);
        System.out.println("F1: " + tot_f1_3);
        System.out.println();
        System.out.println("Label 4 Results");
        System.out.println("Precision: " + tot_precision_4);
        System.out.println("Recall: " + tot_recall_4);
        System.out.println("F1: " + tot_f1_4);
        System.out.println();



    }


}
