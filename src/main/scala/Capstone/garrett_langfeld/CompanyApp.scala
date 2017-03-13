package Capstone.garrett_langfeld

/**
  * Created by glang on 10/10/2016.
  */

import edu.illinois.cs.cogcomp.lbjava.learn.SupportVectorMachine
import edu.illinois.cs.cogcomp.saul.classifier.Learnable
import weka.classifiers.bayes.NaiveBayes
import Readers.garrett_langfeld.companyData
import edu.illinois.cs.cogcomp.saul.classifier.ClassifierUtils
import Readers.garrett_langfeld.CompanyDataReader
import edu.illinois.cs.cogcomp.saul.util.Logging
import CompanyDataModel._
import scala.collection.JavaConversions._

import scala.collection.JavaConversions._

object CompanyApp extends App{

  val reader = new CompanyDataReader()

  /*
  val trainData = reader.compData
  val testData = reader.compData
*/



  val allData = reader.compData
  //setting 70% of data for training
  val trainSplit = math.ceil(allData.size()*0.7).toInt
  //val trainSplit = math.ceil(allData.size()*0.5).toInt
  val trainData = allData.subList(0, trainSplit)
  val testData = allData.subList(trainSplit, allData.size() - 1)
  //CompanyDataModel.comp populate(trainData)

  /*
  CompanyClassifier.CompanyClassifierAdaBoost.learn(10)
  CompanyClassifier.CompanyClassifierAdaBoost.test(testData)
  */


  /*
  val allData = reader.compData
  val techData = allData.filter(x => sector(x) == "Tech")
  //setting 70% of data for training
  val trainSplit = math.ceil(techData.length*0.7).toInt
  //val trainSplit = math.ceil(allData.size()*0.5).toInt
  val trainData = techData.subList(0, trainSplit)
  val testData = techData.subList(trainSplit, techData.length - 1)
*/



  //filtering list to only include smaller stocks
  val SCData = allData.filter(x => market_cap(x) < 100000)
  //setting 70% of data for training
  val STrainSplit = math.ceil(SCData.length*0.7).toInt
  val SValSplit = STrainSplit + math.ceil(SCData.length*0.2).toInt
  //val trainSplit = math.ceil(allData.size()*0.5).toInt
  val STrainData = SCData.subList(0, STrainSplit)
  val STestData = SCData.subList(STrainSplit, SCData.length - 1)
  //val STestData = SCData.subList(STrainSplit, SValSplit)

    /** Defining the data and specifying it's location  */
    CompanyDataModel.comp populate(STrainData)

  //CompanyClassifier.CompanyClassifierAdaBoost.forget()
  CompanyClassifier.CompanyClassifierAdaBoost.learn(10)
  CompanyClassifier.CompanyClassifierAdaBoost.test(STestData)



  //seeing which values that are actually safe are classified as safe
  val high_spec = STestData.filter(x => risk(x) == "speculative")
  val not_high_spec = high_spec.filter(x => SPrediction(x) != "speculative")
  var i = 0;

  for( i <- 0 to (not_high_spec.size - 1)){
    //println(not_safe.get(i).name + ": " + not_safe.get(i).quarter);
    //println(not_high_spec.get(i).name + ": " + SPrediction(not_high_spec.get(i)));
  }

  println()

  //print(eq_tot_assets(STrainData.get(3)))
    //ClassifierUtils.TestClassifiers(CompanyClassifier.firstCompanyClassifier)


  //filtering list to only include large cap stocks
  val LCData = allData.filter(x => market_cap(x) > 100000)
  //setting 70% of data for training
  val LTrainSplit = math.ceil(LCData.length*0.7).toInt
  //val trainSplit = math.ceil(allData.size()*0.5).toInt
  val LTrainData = LCData.subList(0, LTrainSplit)
  val LTestData = LCData.subList(LTrainSplit, LCData.length - 1)

  /** Defining the data and specifying it's location  */
  CompanyDataModel.comp populate(LTrainData)
  /*
  println ("large")
  println(LCData.length)
  println("small")
  println(SCData.length)
  */

    /*
    CompanyClassifier.firstCompanyClassifier.learn(3)
    CompanyClassifier.firstCompanyClassifier.test(testData)
    println(CompanyClassifier.firstCompanyClassifier.classifier.discreteValue(testData.get(86)))
    */

    //CompanyClassifier.firstCompanyClassifier.crossValidation(10)

    /*
    CompanyClassifier.secondCompanyClassifier.learn(3)
    CompanyClassifier.secondCompanyClassifier.test(testData)
    */
    //println(CompanyClassifier.secondCompanyClassifier.classifier.discreteValue(testData.get(86)))


  //CompanyClassifier.secondCompanyClassifier.learn(10)
  //CompanyClassifier.secondCompanyClassifier.test(LTestData)


    /*
    CompanyClassifier.CompanyClassifierWeka.learn(3)
    CompanyClassifier.CompanyClassifierWeka.test(testData)
    */


    //CompanyClassifier.CompanyClassifierBayesNetwork.learn(10)
    //CompanyClassifier.CompanyClassifierBayesNetwork.test(testData)


  
    /*
    CompanyClassifier.CompanyClassifierRandomForest.learn(3)
    CompanyClassifier.CompanyClassifierRandomForest.test(testData)
    */

    /*
    CompanyClassifier.CompanyClassifierRandomForest2.learn(3)
    CompanyClassifier.CompanyClassifierRandomForest2.test(testData)
    */



    CompanyClassifier.CompanyClassifierRandomForest3.learn(5)
    CompanyClassifier.CompanyClassifierRandomForest3.test(LTestData)


    /*
    CompanyClassifier.CompanyClassifierSparseNetwork.learn(10)
    CompanyClassifier.CompanyClassifierSparseNetwork.test(testData)
    */

    //CompanyClassifier.CompanyClassifierRandomForest3.crossValidation(10)

    /*
    CompanyClassifier.CompanyClassifierMLPerceptron.learn(3)
    CompanyClassifier.CompanyClassifierMLPerceptron.test(testData)
    */

    /*
    CompanyClassifier.CompanyClassifierAdaBoost.forget()
    CompanyClassifier.CompanyClassifierAdaBoost.learn(10)
    CompanyClassifier.CompanyClassifierAdaBoost.test(LTestData)
    */

    /*
    //CompanyClassifier.CompanyClassifierAdaBoost.forget()
    CompanyClassifier.CompanyClassifierAdaBoost.learn(10)
    CompanyClassifier.CompanyClassifierAdaBoost.test(testData)
    */


  /*
    //seeing which values that are actually safe are classified as safe
    val safe_class = testData.filter(x => risk(x) == "safe")
    val not_safe = safe_class.filter(x => prediction(x) != "safe")
    var i = 0;

    for( i <- 0 to (not_safe.size - 1)){
      //println(not_safe.get(i).name + ": " + not_safe.get(i).quarter);
      println(not_safe.get(i).name + ": " + prediction(not_safe.get(i)));
    }

    println()

    //seeing which values predicted as safe are actually not safe
    val predicted_safe = testData.filter(x => prediction(x) == "safe")
    val false_safe = predicted_safe.filter(x => risk(x) != "safe")

    for( i <- 0 to (false_safe.size - 1)){
      //println(not_safe.get(i).name + ": " + not_safe.get(i).quarter);
      println(false_safe.get(i).name + ": " + false_safe.get(i).quarter + ": " + risk(false_safe.get(i)))
    }
*/



}
