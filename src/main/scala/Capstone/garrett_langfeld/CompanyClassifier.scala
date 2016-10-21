package Capstone.garrett_langfeld

/**
  * Created by glang on 10/9/2016.
  */

import edu.illinois.cs.cogcomp.lbjava.learn.SupportVectorMachine
import edu.illinois.cs.cogcomp.saul.classifier.Learnable
import edu.illinois.cs.cogcomp.lbjava.learn.StochasticGradientDescent
import weka.classifiers.bayes.NaiveBayes
import edu.illinois.cs.cogcomp.saul
import Readers.garrett_langfeld.companyData


object CompanyClassifier {
  import CompanyDataModel._
  object firstCompanyClassifier extends Learnable[companyData](comp) {
    def label = rating
    //override def feature = using(endDate)//(netDebtEBITDA)//, total_debt_ebit, net_debt_ebit, ebit_int_exp, ebitda_capex_int, ebit_int_exp, int_exp, eq_tot_assets, LTD_eq, LTD_cap, LTD_tot_assets, tot_DE, tot_DC, tot_DA)
    override def feature = using(netDebtEBITDA, total_debt_ebit, net_debt_ebit, ebit_int_exp, ebitda_capex_int, ebit_int_exp, int_exp, eq_tot_assets, LTD_eq, LTD_cap, LTD_tot_assets, tot_DE, tot_DC, tot_DA)
    override lazy val classifier = new SupportVectorMachine()

  }



  /*
  object CompanyClassifierWeka extends Learnable[companyData](comp) {
    def label = rating
    //override lazy val classifier = new SaulWekaWrapper(new NaiveBayes())
    //override def feature = using(wordFeature)
  }
  */

}
