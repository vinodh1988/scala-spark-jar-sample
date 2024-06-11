import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object GoodbooksJob {
  def main(args: Array[String]): Unit = {
    // Create Spark session
    val spark = SparkSession.builder()
      .appName("Goodbooks Data Processing")
      .getOrCreate()

    // Ensure the path to your CSV file is correct
    val filePath = "hdfs:///user/vinodh/ratings.csv" // Modify as needed

    // Read data from HDFS
    val data = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(filePath)

    // Process data: Calculate average rating for each book
    val avgRatings = data
      .groupBy("book_id")
      .agg(avg("rating").alias("average_rating"))

    // Define the path to save the JSON output
    val outputPath = "hdfs:///user/vinodh/output/avgRatings.json" // Modify as needed

    // Save the result back to HDFS in JSON format
    avgRatings
      .write
      .mode("overwrite")
      .json(outputPath)

    // Stop the Spark session
    spark.stop()
  }
}
