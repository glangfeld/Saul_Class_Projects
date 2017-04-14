package Readers.garrett_langfeld;



/**
 * Created by glang on 4/8/2017.
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
import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.api.complex.IComplexNDArray;
import org.nd4j.linalg.api.complex.IComplexNumber;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.indexing.INDArrayIndex;
import org.nd4j.linalg.indexing.ShapeOffsetResolution;
import org.nd4j.linalg.indexing.conditions.Condition;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.Serializable;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import org.deeplearning4j.eval.meta.Prediction;
import org.deeplearning4j.util.ModelSerializer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileNotFoundException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import java.io.FileWriter;
import java.util.Arrays;

import java.applet.*;
import java.awt.*;


public class dl4J_Java_Applet extends Applet {
    TextField text1, text2;
    MultiLayerNetwork model;

    public DataSet getFile() throws Exception{
        /*
        System.out.println("in getFile method");
        FileWriter writer = new FileWriter(new File("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/UI_Data/Test_UI_Data.csv"), true);
        System.out.println("modifying CSV");
        writer.append("20060930");
        writer.append(",");
        writer.append("121");
        writer.append(",");
        writer.append("-.01065");
        writer.append(",");
        writer.flush();
        writer.close();
        */
        //getting test data from csv file
        RecordReader recordReader = new CSVRecordReader(0, ",");
        //recordReader.initialize(new FileSplit(new File("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/More_Data_for_NN_1Q_Ahead_wo_rating_5_labels4.csv")));
        recordReader.initialize(new FileSplit(new File("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/UI_Data/Test_UI_Data.csv")));
        DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader, 1);
        DataSet testData = iterator.next();
        return testData;

    }

    public void init(){
        /*
        text1 = new TextField(8);
        text2 = new TextField(8);
        add(text1);
        add(text2);
        text1.setText("0");
        text2.setText("0");
        */
        File locationToSave = new File("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/dl4J_All_Indust/configuration.json");
        //MultiLayerNetwork vgg16 = ModelSerializer.restoreMultiLayerNetwork(locationToSave);
        //String contents = new String(Files.readAllBytes(Paths.get("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/dl4J_All_Indust/configuration.json")));
        //File file = new File(fileLocation);

        JSONParser parser = new JSONParser();
        JSONObject jsO = new JSONObject();

        try {

            Object obj = parser.parse(new FileReader("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/dl4J_All_Indust/configuration.json"));

            JSONObject jsonObject = (JSONObject) obj;
            jsO = jsonObject;
            System.out.println(jsonObject);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        MultiLayerConfiguration config = new MultiLayerConfiguration();
        config = MultiLayerConfiguration.fromJson(jsO.toJSONString());

        MultiLayerNetwork net = new MultiLayerNetwork(config);
        model = net;



    }

    public void paint(Graphics g) {
        int x = 0, y = 0, z = 0;

        String s1, s2, s;
        /*
        g.drawString("Input a number in each box  ", 10, 50);
        try {
            s1 = text1.getText();
            x = Integer.parseInt(s1);
            s2 = text2.getText();
            y = Integer.parseInt(s2);
        } catch (Exception e) {
        }


        z = x + y;

        s =  String.valueOf(z);
        g.drawString("The Sum is : ",10,75);
        g.drawString(s,100,75);
        */

        try {
            System.out.println("trying");
            DataSet testData = getFile();
            System.out.println("got file");
            //testData.addFeatureVector();
            INDArray test = testData.getFeatureMatrix();
            System.out.println(test);
            //INDArray test2 = Nd4j.create(new double[]{});
            //test2.add(20060930.00);
            //INDArray test2 = test.slice(0);
            //test2.getRow(0);
            int[] prediction = model.predict(test);
            System.out.println("Prediction");
            System.out.println(prediction[0]);
            z = prediction[0];
            //System.out.println("test2");
            //System.out.println(test2);
            System.out.println("testData");
            System.out.println(testData);
            /*
            DataSet testData = getFile();
            INDArray test = testData.getFeatureMatrix();
            int[] prediction = model.predict(test);
            System.out.println("Prediction");
            System.out.println(prediction[0]);
            System.out.println(prediction.length);
            */
        }
        catch (Exception e){

        }

        s =  String.valueOf(z);
        g.drawString("The Prediction is Quintile ",10,75);
        g.drawString(s,150,75);
    }

    public boolean action(Event event, Object obj)
    {
        repaint();
        return true;
    }
}


