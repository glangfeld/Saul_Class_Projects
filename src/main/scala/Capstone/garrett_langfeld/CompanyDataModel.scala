package Capstone.garrett_langfeld
import edu.illinois.cs.cogcomp.saul.datamodel.DataModel
//import edu.illinois.cs.cogcomp.saulexamples.data.Document

import scala.collection.JavaConversions._
import Readers.garrett_langfeld.companyData

object CompanyDataModel extends DataModel{
  val comp = node[companyData]

  val name = property(comp){
    x: companyData =>
      val a = x.name
      a
  }

  val rating = property(comp){
    x: companyData =>
      val b = x.rating
      b
  }

  val endDate = property(comp){
    x: companyData =>
      val c = x.endDate
      c
  }

  val netDebtEBITDA = property(comp){
    x: companyData =>
      val d = x.ND_EBITDA.toDouble
      d
  }

  var total_debt_ebit = property(comp){
    x: companyData =>
      val e = x.TD_EBIT.toDouble
      e
  }

  var net_debt_ebit = property(comp){
    x: companyData =>
      val f = x.ND_EBIT.toDouble
      f
  }
  var ebitda_int_exp = property(comp){
    x: companyData =>
      val g = x.EBITDA_Interest.toDouble
      g
  }
  var ebitda_capex_int = property(comp){
    x: companyData =>
      val h = x.EBITDA_Cap_Int.toDouble
      h
  }
  var ebit_int_exp = property(comp){
    x: companyData =>
      val i = x.EBIT_Int.toDouble
      i
  }
  var int_exp = property(comp){
    x: companyData =>
      val j = x.int_exp.toDouble
      j
  }
  var eq_tot_assets = property(comp){
    x: companyData =>
      val k = x.CE_TA.toDouble
      k
  }
  var LTD_eq = property(comp){
    x: companyData =>
      val l = x.LT_DE.toDouble
      l
  }
  var LTD_cap = property(comp){
    x: companyData =>
      val m = x.LT_DC.toDouble
      m
  }
  var LTD_tot_assets = property(comp){
    x: companyData =>
      val n = x.LT_DTA.toDouble
      n
  }
  var tot_DE = property(comp){
    x: companyData =>
      val o = x.TDE.toDouble
      o
  }
  var tot_DC = property(comp){
    x: companyData =>
      val p = x.TDC.toDouble
      p
  }
  var tot_DA = property(comp){
    x: companyData =>
      val q = x.TDA.toDouble
      q
  }


}

