package Capstone.garrett_langfeld

/**
  * Created by glang on 10/10/2016.
  */

import edu.illinois.cs.cogcomp.lbjava.learn.SupportVectorMachine
import edu.illinois.cs.cogcomp.saul.classifier.Learnable
import weka.classifiers.bayes.NaiveBayes
import Readers.garrett_langfeld.companyData
import Readers.garrett_langfeld.CompanyDataReader
import edu.illinois.cs.cogcomp.saul.util.Logging

object CompanyApp extends Logging {

  val reader = new CompanyDataReader
  val trainData = reader.compData
  val testData = reader.compData

  object CompanyExperimentType extends Enumeration {
    val SGD = Value
  }


  def main(args: Array[String]): Unit = {
    /** Choose the experiment you're interested in by changing the following line */
    val testType = CompanyExperimentType.TrainAndTest

    testType match {
      case CompanyExperimentType.SGD => SGD()
    }
  }

  /** A standard method for testing the Spam Classification problem. Simply training and testing the resulting model.*/
  def SGD(): Unit = {
    /** Defining the data and specifying it's location  */
    CompanyDataModel populate trainData
    //SpamClassifierWeka.learn(30)
    //SpamClassifierWeka.test(testData)
  }

}
