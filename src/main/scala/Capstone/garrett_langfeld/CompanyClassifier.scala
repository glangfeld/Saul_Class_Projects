package Capstone.garrett_langfeld

/**
  * Created by glang on 10/9/2016.
  */

import edu.illinois.cs.cogcomp.lbjava.learn.SupportVectorMachine
import edu.illinois.cs.cogcomp.saul.classifier.Learnable
import edu.illinois.cs.cogcomp.lbjava.learn.StochasticGradientDescent
import weka.classifiers.bayes.NaiveBayes
import weka.classifiers.bayes.BayesNet
import weka.classifiers.trees.RandomForest
import weka.classifiers.functions.MultilayerPerceptron
import edu.illinois.cs.cogcomp.saul.learn.SaulWekaWrapper
import edu.illinois.cs.cogcomp.saul
import Readers.garrett_langfeld.companyData


object CompanyClassifier {
  import CompanyDataModel._
  object firstCompanyClassifier extends Learnable[companyData](comp) {
    def label = rating
    //override def feature = using(netDebtEBITDA, total_debt_ebit, net_debt_ebit, ebitda_int_exp, ebitda_capex_int, ebit_int_exp, int_exp, eq_tot_assets, LTD_eq, LTD_cap, LTD_tot_assets, tot_DE, tot_DC, tot_DA)
    override def feature = using(netDebtEBITDA, total_debt_ebit, tot_DA, LTD_eq)
    override lazy val classifier = new SupportVectorMachine()

  }

  object secondCompanyClassifier extends Learnable[companyData](comp) {
    def label = rating
    override def feature = using(eq_tot_assets, LTD_eq, LTD_cap, LTD_tot_assets, tot_DE, tot_DC, tot_DA)
    //override def feature = using(netDebtEBITDA, total_debt_ebit, tot_DA, LTD_eq)
    override lazy val classifier = new SupportVectorMachine()

  }

  object CompanyClassifierWeka extends Learnable(comp) {
    def label = rating
    override lazy val classifier = new SaulWekaWrapper(new NaiveBayes())
    override def feature = using(eq_tot_assets, LTD_eq, LTD_cap, LTD_tot_assets, tot_DE, tot_DC, tot_DA)
  }

  object CompanyClassifierBayesNetwork extends Learnable(comp) {
    def label = rating
    override lazy val classifier = new SaulWekaWrapper(new BayesNet())
    override def feature = using(eq_tot_assets, LTD_eq, LTD_cap, LTD_tot_assets, tot_DE, tot_DC, tot_DA)
  }

  object CompanyClassifierRandomForest extends Learnable(comp) {
    def label = rating
    override lazy val classifier = new SaulWekaWrapper(new RandomForest())
    override def feature = using(eq_tot_assets, LTD_eq, LTD_cap, LTD_tot_assets, tot_DE, tot_DC, tot_DA)
  }


  object CompanyClassifierMLPerceptron extends Learnable(comp) {
    def label = rating
    override lazy val classifier = new SaulWekaWrapper(new MultilayerPerceptron())
    override def feature = using(eq_tot_assets, LTD_eq, LTD_cap, LTD_tot_assets, tot_DE, tot_DC, tot_DA)
  }


/*
  object CompanyClassifierWeka extends Learnable(comp) {
    def label = rating
    //override lazy val classifier = new SaulWekaWrapper(new NaiveBayes())
    //override def feature = using(wordFeature)
  }
*/

}
