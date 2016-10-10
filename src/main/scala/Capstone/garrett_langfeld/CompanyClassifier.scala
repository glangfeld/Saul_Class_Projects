package Capstone.garrett_langfeld

/**
  * Created by glang on 10/9/2016.
  */

import edu.illinois.cs.cogcomp.lbjava.learn.SupportVectorMachine
import edu.illinois.cs.cogcomp.saul.classifier.Learnable
import weka.classifiers.bayes.NaiveBayes
import Readers.garrett_langfeld.companyData


object CompanyClassifier {
  import CompanyDataModel._
  object firstCompanyClassifier extends Learnable[companyData](comp) {
    def label = rating
    override lazy val classifier = new SupportVectorMachine()
  }

}
