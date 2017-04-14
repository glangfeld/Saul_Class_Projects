package Capstone.garrett_langfeld

/**
  * Created by glang on 4/12/2017.
  */

import edu.illinois.cs.cogcomp.lbjava.learn.SupportVectorMachine
import edu.illinois.cs.cogcomp.saul.classifier.Learnable
import weka.classifiers.bayes.NaiveBayes
import Readers.garrett_langfeld.companyDataCompustat
import edu.illinois.cs.cogcomp.saul.classifier.ClassifierUtils
import Readers.garrett_langfeld.CompanyDataReaderCompustat
import edu.illinois.cs.cogcomp.saul.util.Logging
import CompanyDataModel2._
import scala.collection.JavaConversions._
import java.util.ArrayList
import java.util.Iterator;
import edu.illinois.cs.cogcomp.lbjava.classify.{ Classifier, FeatureVector, TestDiscrete }
import scala.collection.JavaConversions._
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;


import scala.collection.mutable.ListBuffer

object CompanyAppRandomForest3 extends App{

  val reader = new CompanyDataReaderCompustat()

  var allData = reader.compData

  val split = allData.size() * 0.7

  val trainData = allData.subList(0, split.toInt)
  val testData = allData.subList(split.toInt + 1, allData.size() - 1)

  CompanyDataModel2.comp populate (trainData)

  CompanyClassifier2.CompanyClassifierRandomForest.learn(10)
  CompanyClassifier2.CompanyClassifierRandomForest.test(testData)


}
