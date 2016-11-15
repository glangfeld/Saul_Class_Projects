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


    /** Defining the data and specifying it's location  */
    CompanyDataModel.comp populate(trainData)
  print(eq_tot_assets(trainData.get(3)))
    //ClassifierUtils.TestClassifiers(CompanyClassifier.firstCompanyClassifier)

    /*
    CompanyClassifier.firstCompanyClassifier.learn(3)
    CompanyClassifier.firstCompanyClassifier.test(testData)
    println(CompanyClassifier.firstCompanyClassifier.classifier.discreteValue(testData.get(86)))
    */


    /*
    CompanyClassifier.secondCompanyClassifier.learn(3)
    CompanyClassifier.secondCompanyClassifier.test(testData)
    */
    //println(CompanyClassifier.secondCompanyClassifier.classifier.discreteValue(testData.get(86)))


    /*
    CompanyClassifier.CompanyClassifierWeka.learn(3)
    CompanyClassifier.CompanyClassifierWeka.test(testData)
    */

    /*
    CompanyClassifier.CompanyClassifierBayesNetwork.learn(10)
    CompanyClassifier.CompanyClassifierBayesNetwork.test(testData)
    */

    /*
    CompanyClassifier.CompanyClassifierRandomForest.learn(3)
    CompanyClassifier.CompanyClassifierRandomForest.test(testData)
    */

    /*
    CompanyClassifier.CompanyClassifierRandomForest2.learn(3)
    CompanyClassifier.CompanyClassifierRandomForest2.test(testData)
    */


    CompanyClassifier.CompanyClassifierRandomForest3.learn(10)
    CompanyClassifier.CompanyClassifierRandomForest3.test(testData)


    //CompanyClassifier.CompanyClassifierRandomForest3.crossValidation(3)

    /*
    CompanyClassifier.CompanyClassifierMLPerceptron.learn(3)
    CompanyClassifier.CompanyClassifierMLPerceptron.test(testData)
    */

    /*
    CompanyClassifier.CompanyClassifierAdaBoost.learn(10)
    CompanyClassifier.CompanyClassifierAdaBoost.test(testData)
    */

    //seeing which values that are actually safe are classified as safe
    val safe_class = testData.filter(x => risk(x) == "safe")
    val not_safe = safe_class.filter(x => prediction(x) != "safe")
    var i = 0;

    for( i <- 0 to (not_safe.size - 1)){
      println(not_safe.get(i).name + ": " + not_safe.get(i).quarter);
    }


}
