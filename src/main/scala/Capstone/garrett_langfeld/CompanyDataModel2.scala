package Capstone.garrett_langfeld

/**
  * Created by glang on 3/31/2017.
  */

import edu.illinois.cs.cogcomp.saul.datamodel.DataModel
import scala.collection.JavaConversions._
import Readers.garrett_langfeld.companyDataCompustat
import CompanyClassifier2._


object CompanyDataModel2 extends DataModel{
  val comp = node[companyDataCompustat]

  val date = property(comp){
    x: companyDataCompustat =>
      val a = x.date.toDouble
      a
  }

  val company = property(comp){
    x: companyDataCompustat =>
      val a = x.company.toDouble
      a
  }
  val LTD_IC = property(comp){
    x: companyDataCompustat =>
      val a = x.LTD_IC.toDouble
      a
  }

  val TD_IC = property(comp){
    x: companyDataCompustat =>
      val c = x.TD_IC.toDouble
      c
  }

  val R_CA = property(comp){
    x: companyDataCompustat =>
      val d = x.R_CA.toDouble
      d
  }

  var TD_TA = property(comp){
    x: companyDataCompustat =>
      val e = x.TD_TA.toDouble
      e
  }
  //write properties of ratings as a string
  //use svm to predict rating

  var TD_EBITDA = property(comp){
    x: companyDataCompustat =>
      val f = x.TD_EBITDA.toDouble
      f
  }
  var LTD_TL = property(comp){
    x: companyDataCompustat =>
      val g = x.LTD_TL.toDouble
      g
  }
  var CF_TD = property(comp){
    x: companyDataCompustat =>
      val h = x.CF_TD.toDouble
      h
  }
  var LTD_BE = property(comp){
    x: companyDataCompustat =>
      val i = x.LTD_BE.toDouble
      i
  }
  var TD_A = property(comp){
    x: companyDataCompustat =>
      val j = x.TD_A.toDouble
      j
  }
  var TD_C = property(comp){
    x: companyDataCompustat =>
      val k = x.TD_C.toDouble
      k
  }
  var TD_E = property(comp){
    x: companyDataCompustat =>
      val l = x.TD_E.toDouble
      l
  }
  var Int_Cov = property(comp){
    x: companyDataCompustat =>
      val m = x.Int_Cov.toDouble
      m
  }
  var quick_ratio = property(comp){
    x: companyDataCompustat =>
      val n = x.quick_ratio.toDouble
      n
  }
  var current_ratio = property(comp){
    x: companyDataCompustat =>
      val o = x.current_ratio.toDouble
      o
  }
  var cash_conv = property(comp){
    x: companyDataCompustat =>
      val p = x.cash_conv.toDouble
      p
  }
  var rating = property(comp){
    x: companyDataCompustat =>
      val q = x.rating
      q
  }


  val sector = property(comp){
    x: companyDataCompustat =>
      val b = x.sector.toDouble
      b
  }

  //creating property that gives the rating classification
/*
  val prediction = property(comp){
    x: companyDataCompustat => CompanyClassifierRandomForest3.classifier.discreteValue(x)
  }
*/
  val AdaPrediction = property(comp){
    x: companyDataCompustat => CompanyClassifierAdaBoost.classifier.discreteValue(x)
  }

  val SVMPrediction = property(comp){
    x: companyDataCompustat => CompanyClassifierSVM.classifier.discreteValue(x)
  }


}

