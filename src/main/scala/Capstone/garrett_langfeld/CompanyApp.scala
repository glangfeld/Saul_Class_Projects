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

import scala.collection.JavaConversions._

object CompanyApp extends App{

  val reader = new CompanyDataReader()
  val trainData = reader.compData
  val testData = reader.compData
    /** Defining the data and specifying it's location  */
    CompanyDataModel.comp populate(trainData)

    //ClassifierUtils.TestClassifiers(CompanyClassifier.firstCompanyClassifier)

    CompanyClassifier.firstCompanyClassifier.learn(3)
    CompanyClassifier.firstCompanyClassifier.test(testData)
    //CompanyClassifier.firstCompanyClassifier.foreach(_testContinuous(trainData))
    //SpamClassifierWeka.learn(30)
    //SpamClassifierWeka.test(testData)


}
