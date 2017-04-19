package Capstone.garrett_langfeld

/**
  * Created by glang on 4/18/2017.
  */
import java.util

import Readers.garrett_langfeld.companyDataCompustat
import edu.illinois.cs.cogcomp.saul.datamodel.DataModel

import scala.collection.JavaConversions._
import java.util.ArrayList

object RF_Client extends App{




  override def main(args: Array[String]) {


   //array to keep track of raw input
   //var input = new Array[String](15)
   //arrays to keep track of means and stdevs for each feature for standardization
   //avg array
   var A = Array(2.585318948, 2.905430881, 0.388831587, 0.289575723, 2.45374721, 0.381368481, 0.191814849, 1.110881804, 0.620895354, 0.466149852, 5.043976885, 15.82534593, 1.168884764, 1.604439422, 78.96759463)
   //stdev array
   var S = Array(205.0543782, 226.4380435, 0.196822444, 0.157886966, 19.28583448, 0.186872507, 0.152264185, 5.298728381, 0.173668066, 1.992195815, 278.7601367, 102.7157477, 0.831022955, 0.99169628, 182.0177933)
   var feature = Array("Long-Term Debt/Invested Capital", "Total Debt/Invested Capital", "Receivables/Current Assets", "Total Debt/Total Assets", "Total Debt/EBITDA", "Long-term Debt/Total Liabilities", "Cash Flow/Total Debt", "Long-term Debt/Book Equity", "Total Debt/Total Assets", "Total Debt/Capital", "Total Debt/Equity", "Interest Coverage Ratio", "Quick Ratio", "Current Ratio", "Cash Conversion Cycle (Days)", "Sector")
   var in = new ArrayList[Float]

   var i = 0
   for (i <- 0 until 15) {
    if (i != 8) {
     //print("Please enter Long-Term Debt/Invested Capital and hit Enter: " )
     print("Please enter " + feature(i) + " and hit Enter: ")
     //val LTDIC = (Console.readLine).toFloat
     in.add(((Console.readLine.toFloat - A(i)) / S(i)).toFloat)
    }
   }
   println("Please enter the number corresponding to the company sector and hit Enter: ")
   println("Consumer Discretionary: 1")
   println("Consumer Staples: 2")
   println("Energy: 3")
   println("Health Care: 4")
   println("Industrials: 5")
   println("Information Technology: 6")
   println("Materials: 7")
   println("Telecom: 8")
   in.add(Console.readLine.toFloat)
   //lazy val temp = standardize(i1.toDouble, 0)
   /*
    print("Please enter Total Debt/Invested Capital and hit Enter: " )
    val TDIC = (Console.readLine).toFloat
   print("Please enter Receivables/Current Assets and hit Enter: " )
   val RCA = (Console.readLine).toFloat
   print("Please enter Total Debt/Total Assets and hit Enter: " )
   val TDTA = (Console.readLine).toFloat
   print("Please enter Total Debt/EBITDA and hit Enter: " )
   val TDEbitda = (Console.readLine).toFloat
   print("Please enter Total Debt/Invested Capital and hit Enter: " )
   */

   //data(0) = ((in(0) - A(0))/S(0)).toFloat


    CompanyClassifier2.CompanyClassifierRandomForest.load()
    //val input = new companyDataCompustat(20071231, 92, -0.01092061.toFloat, -0.011280926.toFloat, -0.466570709.toFloat, -0.092317456.toFloat, 0.017020409.toFloat, 0.945197997.toFloat, -0.16953986.toFloat, -0.091886537.toFloat, -0.770984312.toFloat, -0.048263254.toFloat, -0.014668442.toFloat, -0.084440275.toFloat, 2.032573499.toFloat, 2.248229245.toFloat, 0.334793672.toFloat, 2.toString, 3)
    val input = new companyDataCompustat(20071231, 92, in.get(0), in.get(1), in.get(2), in.get(3), in.get(4), in.get(5), in.get(6), in.get(7), 0.toFloat, in.get(8), in.get(9), in.get(10), in.get(11), in.get(12), in.get(13), 2.toString, in.get(14))
    //val input = new companyDataCompustat(20071231, 92, temp, TDIC, RCA, TDTA, TDEbitda, 0.945197997.toFloat, -0.16953986.toFloat, -0.091886537.toFloat, 0.toFloat, -0.048263254.toFloat, -0.014668442.toFloat, -0.084440275.toFloat, 2.032573499.toFloat, 2.248229245.toFloat, 0.334793672.toFloat, 2.toString, 3)
    val prediction = CompanyClassifier2.CompanyClassifierRandomForest.classifier.discreteValue(input)
    println(prediction)


  }

 /*
 //arrays to keep track of means and stdevs for each feature for standardization
 //avg array
 var A = Array(2.585318948, 2.905430881, 0.388831587, 0.289575723, 2.45374721, 0.381368481, 0.191814849, 1.110881804, 0.620895354, 0.466149852, 5.043976885, 15.82534593, 1.168884764, 1.604439422, 78.96759463, 1.825210658, 4.254953314)
 //stdev array
 var S = Array(205.0543782, 226.4380435, 0.196822444, 0.157886966, 19.28583448, 0.186872507, 0.152264185, 5.298728381, 0.173668066, 1.992195815, 278.7601367, 102.7157477, 0.831022955, 0.99169628, 182.0177933, 1.16494651, 2.049777974)
 var in = new Array[Float](15)
 var data = new Array[Float](15)


 def standardize (data:Double, i:Int) : Float = {
  val sh:Float = ((data - A(i))/S(i)).toFloat
  return sh
 }
 */


}
