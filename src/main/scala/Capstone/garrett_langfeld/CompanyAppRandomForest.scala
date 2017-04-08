package Capstone.garrett_langfeld

/**
  * Created by glang on 4/7/2017.
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
import edu.illinois.cs.cogcomp.lbjava.classify.{ Classifier, FeatureVector, TestDiscrete }
import scala.collection.JavaConversions._


import scala.collection.mutable.ListBuffer

object CompanyAppRandomForest extends App{


  //keeping track of tp, fp, fn for each label and overall
  var tot_tp = 0.toDouble
  var tot_tp0 = 0.toDouble
  var tot_tp1 = 0.toDouble
  var tot_tp2 = 0.toDouble
  var tot_tp3 = 0.toDouble
  var tot_tp4 = 0.toDouble

  var tot_fp = 0.toDouble
  var tot_fp0 = 0.toDouble
  var tot_fp1 = 0.toDouble
  var tot_fp2 = 0.toDouble
  var tot_fp3 = 0.toDouble
  var tot_fp4 = 0.toDouble

  var tot_fn = 0.toDouble
  var tot_fn0 = 0.toDouble
  var tot_fn1 = 0.toDouble
  var tot_fn2 = 0.toDouble
  var tot_fn3 = 0.toDouble
  var tot_fn4 = 0.toDouble

  val reader = new CompanyDataReaderCompustat()

  /*
  val trainData = reader.compData
  val testData = reader.compData
*/


  def crossValidate(indust:Int=10) : Unit = {
    var allData = reader.compData
    //adding functionality to filter by industry if function has input indust
    if (indust > -1 && indust < 8){
      var sectorData = new ArrayList[companyDataCompustat]()
      for (e <- 0 until allData.size()) {
        if (allData.get(e).sector == indust) {
          sectorData.add(allData.get(e))
        }
      }
      allData = sectorData
      //println("sector data index 1: " + allData.get(1).date)
    }
    val s = allData.size()
    println("sector data size: " + s)
    var i = allData.size() - allData.size() / 10 - 1
    var j = allData.size() - 1

    //keeping track of tp, fp, fn, tn overall and for each label class

    var tp0 = 0.toDouble
    var tn0 = 0.toDouble
    var fp0 = 0.toDouble
    var fn0 = 0.toDouble

    var tp1 = 0.toDouble
    var tn1 = 0.toDouble
    var fp1 = 0.toDouble
    var fn1 = 0.toDouble

    var tp2 = 0.toDouble
    var tn2 = 0.toDouble
    var fp2 = 0.toDouble
    var fn2 = 0.toDouble

    var tp3 = 0.toDouble
    var tn3 = 0.toDouble
    var fp3 = 0.toDouble
    var fn3 = 0.toDouble

    var tp4 = 0.toDouble
    var tn4 = 0.toDouble
    var fp4 = 0.toDouble
    var fn4 = 0.toDouble

    for (k <- 0 until 10) {

      /*
      var allDataCopy = new ArrayList[companyDataCompustat]()
      val readerCopy = new CompanyDataReaderCompustat()
      allDataCopy = readerCopy.compData
      var trainData1 = allDataCopy.subList(0, 0)
      var trainData2 = allDataCopy.subList(0, 0)
      if (i > 1) {
        trainData1 = allDataCopy.subList(0, i)
      }
      println("i: " + i, " j: " + j)
      //var testData = new ListBuffer[companyDataCompustat]()
      //testData: java.util.List[companyDataCompustat] = ListBuffer(List(allDataCopy.subList(i + 1, j)):_*)
      //testData =  allDataCopy.subList(i + 1, j)
      if (j < allDataCopy.size() - 1) {
        trainData2 = allDataCopy.subList(j + 1, s - 1)
      }
      var trainData = allDataCopy.subList(0, 0)
      if (j == s - 1) {
        trainData = trainData1
      }
      //else if (j == s/10){
      else if (i == 1) {
        trainData = trainData2
      }
      else {
        trainData.addAll(trainData1)
        //trainData.addAll(trainData2)
      }

      var testData = new ArrayList[companyDataCompustat]()
      for (e <- 0 until allDataCopy.size()) {
        if (!trainData.contains(allDataCopy.get(e))) {
          testData.add(allDataCopy.get(e))
        }
      }
      */

      var trainData1 = allData.subList(0, 0)
      var trainData2 = allData.subList(0, 0)
      if (i > 1) {
        trainData1 = allData.subList(0, i)
      }
      println("i: " + i, " j: " + j)
      //var testData = new ListBuffer[companyDataCompustat]()
      //testData: java.util.List[companyDataCompustat] = ListBuffer(List(allDataCopy.subList(i + 1, j)):_*)
      //testData =  allDataCopy.subList(i + 1, j)
      if (j < allData.size() - 1) {
        trainData2 = allData.subList(j + 1, s - 1)
      }
      var trainData = allData.subList(0, 0)
      if (j == s - 1) {
        trainData = trainData1
      }
      //else if (j == s/10){
      else if (i == 1) {
        trainData = trainData2
      }
      else {
        trainData.addAll(trainData1)
        //trainData.addAll(trainData2)
      }

      var testData = new ArrayList[companyDataCompustat]()
      for (e <- 0 until allData.size()) {
        if (!trainData.contains(allData.get(e))) {
          testData.add(allData.get(e))
        }
      }

      println("i: " + i + ", j:" + j)
      i -= s / 10
      j -= s / 10
      //println(trainData.get(0).Int_Cov)

      CompanyDataModel2.comp populate (trainData)


      CompanyClassifier2.CompanyClassifierRandomForest.learn(10)
      CompanyClassifier2.CompanyClassifierRandomForest.test(testData)
      //CompanyClassifier2.CompanyClassifierRandomForestBoost.test(allDataCopy)


      val predicted_0 = testData.filter(x => RFPrediction(x) == "0")
      val actual_0 = testData.filter(x => rating(x) == "0")
      val not_0 = testData.filter(x => rating(x) != "0")
      tp0 += actual_0.filter(x => RFPrediction(x) == "0").size
      tn0 += not_0.filter(x => RFPrediction(x) != "0").size
      fp0 += predicted_0.filter(x => rating(x) != "0").size
      fn0 += actual_0.filter(x => RFPrediction(x) != "0").size

      val predicted_1 = testData.filter(x => RFPrediction(x) == "1")
      val actual_1 = testData.filter(x => rating(x) == "1")
      val not_1 = testData.filter(x => rating(x) != "1")
      tp1 += actual_1.filter(x => RFPrediction(x) == "1").size
      tn1 += not_1.filter(x => RFPrediction(x) != "1").size
      fp1 += predicted_1.filter(x => rating(x) != "1").size
      fn1 += actual_1.filter(x => RFPrediction(x) != "1").size

      val predicted_2 = testData.filter(x => RFPrediction(x) == "2")
      val actual_2 = testData.filter(x => rating(x) == "2")
      val not_2 = testData.filter(x => rating(x) != "2")
      tp2 += actual_1.filter(x => RFPrediction(x) == "2").size
      tn2 += not_2.filter(x => RFPrediction(x) != "2").size
      fp2 += predicted_2.filter(x => rating(x) != "2").size
      fn2 += actual_2.filter(x => RFPrediction(x) != "2").size

      val predicted_3 = testData.filter(x => RFPrediction(x) == "3")
      val actual_3 = testData.filter(x => rating(x) == "3")
      val not_3 = testData.filter(x => rating(x) != "3")
      tp3 += actual_3.filter(x => RFPrediction(x) == "3").size
      tn3 += not_3.filter(x => RFPrediction(x) != "3").size
      fp3 += predicted_3.filter(x => rating(x) != "3").size
      fn3 += actual_3.filter(x => RFPrediction(x) != "3").size

      val predicted_4 = testData.filter(x => RFPrediction(x) == "4")
      val actual_4 = testData.filter(x => rating(x) == "4")
      val not_4 = testData.filter(x => rating(x) != "4")
      tp4 += actual_4.filter(x => RFPrediction(x) == "4").size
      tn4 += not_4.filter(x => RFPrediction(x) != "4").size
      fp4 += predicted_4.filter(x => rating(x) != "4").size
      fn4 += actual_4.filter(x => RFPrediction(x) != "4").size

      CompanyClassifier2.CompanyClassifierRandomForest.forget()
    }



    //setting 70% of data for training
    val trainSplit = math.ceil(allData.size() * 0.7).toInt
    //val trainSplit = math.ceil(allData.size()*0.5).toInt
    val trainData = allData.subList(0, trainSplit)
    val testData = allData.subList(trainSplit, allData.size() - 1)

    val precision_0 = tp0 / (tp0 + fp0)
    val recall_0 = tp0 / (tp0 + fn0)
    val f1_0 = 2 * (precision_0 * recall_0) / (precision_0 + recall_0)
    val acc_0 = (tp0 + tn0) / (tp0 + tn0 + fp0 + fn0)

    val precision_1 = tp1 / (tp1 + fp1)
    val recall_1 = tp1 / (tp1 + fn1)
    val f1_1 = 2 * (precision_1 * recall_1) / (precision_1 + recall_1)
    val acc_1 = (tp1 + tn1) / (tp1 + tn1 + fp1 + fn1)

    val precision_2 = tp2 / (tp2 + fp2)
    val recall_2 = tp2 / (tp2 + fn2)
    val f1_2 = 2 * (precision_2 * recall_2) / (precision_2 + recall_2)
    val acc_2 = (tp2 + tn2) / (tp2 + tn2 + fp2 + fn2)

    val precision_3 = tp3 / (tp3 + fp3)
    val recall_3 = tp3 / (tp3 + fn3)
    val f1_3 = 2 * (precision_3 * recall_3) / (precision_3 + recall_3)
    val acc_3 = (tp3 + tn3) / (tp3 + tn3 + fp3 + fn3)

    val precision_4 = tp4 / (tp4 + fp4)
    val recall_4 = tp4 / (tp4 + fn4)
    val f1_4 = 2 * (precision_4 * recall_4) / (precision_4 + recall_4)
    val acc_4 = (tp4 + tn4) / (tp4 + tn4 + fp4 + fn4)

    val tp = 0.toDouble + tp0 + tp1 + tp2 + tp3 + tp4
    val tn = 0.toDouble + tn0 + tn1 + tn2 + tp3 + tp4
    val fp = 0.toDouble + fp0 + fp1 + fp2 + fp3 + fp4
    val fn = 0.toDouble + fn0 + fn1 + fn2 + fn3 + fn4

    val precision = tp / (tp + fp)
    val recall = tp / (tp + fn)
    val f1 = 2 * (precision * recall) / (precision + recall)
    val acc = (tp + tn) / (tp + tn + fp + fn)

    println()
    println()
    println("Sector " + indust)
    println("Label  Precision Recall  F1  Accuracy")
    println("0:     " + precision_0 + " " + recall_0 + " " + f1_0 + " " + acc_0)
    println("1:     " + precision_1 + " " + recall_1 + " " + f1_1 + " " + acc_1)
    println("2:     " + precision_2 + " " + recall_2 + " " + f1_2 + " " + acc_2)
    println("3:     " + precision_3 + " " + recall_3 + " " + f1_3 + " " + acc_3)
    println("4:     " + precision_4 + " " + recall_4 + " " + f1_4 + " " + acc_4)
    println("       " + precision + " " + recall + " " + f1 + " " + acc)

    tot_tp += tp
    tot_tp0 += tp0
    tot_tp1 += tp1
    tot_tp2 += tp2
    tot_tp3 += tp3
    tot_tp4 += tp4

    tot_fp += fp
    tot_fp0 += fp0
    tot_fp1 += fp1
    tot_fp2 += fp2
    tot_fp3 += fp3
    tot_fp4 += fp4

    tot_fn += fn
    tot_fn0 += fn0
    tot_fn1 += fn1
    tot_fn2 += fn2
    tot_fn3 += fn3
    tot_fn4 += fn4

  }

  //crossValidate()

  var k :Int = 1
  for (k <- 1 until 9){
    crossValidate(k)
  }


  val precision_0 = tot_tp0 / (tot_tp0 + tot_fp0)
  val recall_0 = tot_tp0 / (tot_tp0 + tot_fn0)
  val f1_0 = 2 * (precision_0 * recall_0) / (precision_0 + recall_0)

  val precision_1 = tot_tp1 / (tot_tp1 + tot_fp1)
  val recall_1 = tot_tp1 / (tot_tp1 + tot_fn1)
  val f1_1 = 2 * (precision_1 * recall_1) / (precision_1 + recall_1)

  val precision_2 = tot_tp2 / (tot_tp2 + tot_fp2)
  val recall_2 = tot_tp2 / (tot_tp2 + tot_fn2)
  val f1_2 = 2 * (precision_2 * recall_2) / (precision_2 + recall_2)

  val precision_3 = tot_tp3 / (tot_tp3 + tot_fp3)
  val recall_3 = tot_tp3 / (tot_tp3 + tot_fn3)
  val f1_3 = 2 * (precision_3 * recall_3) / (precision_3 + recall_3)

  val precision_4 = tot_tp4 / (tot_tp4 + tot_fp4)
  val recall_4 = tot_tp4 / (tot_tp4 + tot_fn4)
  val f1_4 = 2 * (precision_4 * recall_4) / (precision_4 + recall_4)

  //tot_tp = 0.toDouble + tot_tp0 + tot_tp1 + tot_tp2 + tot_tp3 + tot_tp4
  //tot_fp = 0.toDouble + tot_fp0 + tot_fp1 + tot_fp2 + tot_fp3 + tot_fp4
  //tot_fn = 0.toDouble + tot_fn0 + tot_fn1 + tot_fn2 + tot_fn3 + tot_fn4

  val precision = tot_tp / (tot_tp + tot_fp)
  val recall = tot_tp / (tot_tp + tot_fn)
  val f1 = 2 * (precision * recall) / (precision + recall)
  println()
  println("Overall")
  println("Label  Precision   Recall    F1  ")
  println("0:     " + precision_0 + " " + recall_0 + " " + f1_0 + " ")
  println("1:     " + precision_1 + " " + recall_1 + " " + f1_1 + " ")
  println("2:     " + precision_2 + " " + recall_2 + " " + f1_2 + " ")
  println("3:     " + precision_3 + " " + recall_3 + " " + f1_3 + " ")
  println("4:     " + precision_4 + " " + recall_4 + " " + f1_4 + " ")
  println("       " + precision + " " + recall + " " + f1 + " ")

  /*
  println("precision: " + precision_1)
  println("recall: " + recall_1)
  println("F1: " + f1_1)
  println("Accuracy: " + acc_1)
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


  /*
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
    CompanyDataModel2.comp populate(STrainData)
    */

  //CompanyClassifier.CompanyClassifierAdaBoost.forget()
  // CompanyClassifier2.CompanyClassifierAdaBoost.learn(10)
  //CompanyClassifier2.CompanyClassifierAdaBoost.test(STestData)



  //seeing which values that are actually safe are classified as safe
  /*
  val high_spec = STestData.filter(x => risk(x) == "speculative")
  val not_high_spec = high_spec.filter(x => SPrediction(x) != "speculative")
  var i = 0;

  for( i <- 0 to (not_high_spec.size - 1)){
    //println(not_safe.get(i).name + ": " + not_safe.get(i).quarter);
    //println(not_high_spec.get(i).name + ": " + SPrediction(not_high_spec.get(i)));
  }

  println()
*/

  //print(eq_tot_assets(STrainData.get(3)))
  //ClassifierUtils.TestClassifiers(CompanyClassifier.firstCompanyClassifier)


  //filtering list to only include large cap stocks
  /*
  val LCData = allData.filter(x => market_cap(x) > 100000)
  //setting 70% of data for training
  val LTrainSplit = math.ceil(LCData.length*0.7).toInt
  //val trainSplit = math.ceil(allData.size()*0.5).toInt
  val LTrainData = LCData.subList(0, LTrainSplit)
  val LTestData = LCData.subList(LTrainSplit, LCData.length - 1)
  */

  /** Defining the data and specifying it's location  */
  //CompanyDataModel2.comp populate(LTrainData)
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



  //CompanyClassifier2.CompanyClassifierRandomForest3.learn(5)
  //CompanyClassifier2.CompanyClassifierRandomForest3.test(testData)


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

  val safe_class = LTestData.filter(x => risk(x) == "safe")
  val not_safe = safe_class.filter(x => prediction(x) != "safe")
  var n = 0;

  for( n <- 0 to (not_safe.size - 1)){
    //println(not_safe.get(i).name + ": " + not_safe.get(i).quarter);
    println(not_safe.get(n).name + ": " + not_safe.get(n).quarter + ": " + prediction(not_safe.get(n)));
  }

  println()
  System.out.println("Predicted Safe but not safe");

  //seeing which values predicted as safe are actually not safe
  val predicted_safe = LTestData.filter(x => prediction(x) == "safe")
  val false_safe = predicted_safe.filter(x => risk(x) != "safe")

  for( n <- 0 to (false_safe.size - 1)){
    //println(not_safe.get(i).name + ": " + not_safe.get(i).quarter);
    println(false_safe.get(n).name + ": " + false_safe.get(n).quarter + ": " + risk(false_safe.get(n)))
  }
  */


  /*
  //seeing which values that are actually safe are classified as safe
  val safe_class = testData.filter(x => risk(x) == "safe")
  val not_safe = safe_class.filter(x => prediction(x) != "safe")
  var n = 0;

  for( n <- 0 to (not_safe.size - 1)){
    //println(not_safe.get(i).name + ": " + not_safe.get(i).quarter);
    println(not_safe.get(i).name + ": " + prediction(not_safe.get(i)));
  }

  println()

  //seeing which values predicted as safe are actually not safe
  val predicted_safe = testData.filter(x => prediction(x) == "safe")
  val false_safe = predicted_safe.filter(x => risk(x) != "safe")

  for( n <- 0 to (false_safe.size - 1)){
    //println(not_safe.get(i).name + ": " + not_safe.get(i).quarter);
    println(false_safe.get(i).name + ": " + false_safe.get(i).quarter + ": " + risk(false_safe.get(i)))
  }

  */


}
