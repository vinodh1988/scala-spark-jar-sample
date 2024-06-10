import org.apache.spark.sql.SparkSession

object cpSparkRDDReference {
  def main(args: Array[String]): Unit = {
    // Initialize SparkSession
    val spark = SparkSession.builder()
      .appName("Simple RDD Example")
      .getOrCreate()

    // Create a SparkContext
    val sc = spark.sparkContext

    val numbers = Array(1, 2, 3, 4, 5)
    val numbersRDD = sc.parallelize(numbers)


    val squaredNumbersRDD = numbersRDD.map(number => number * number)


    val results = squaredNumbersRDD.collect()
    println("Squared numbers: " + results.mkString(", "))


    spark.stop()
  }
}
