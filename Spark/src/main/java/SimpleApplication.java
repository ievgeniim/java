import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;


/**
 * Created by lagger on 12.01.2017.
 */

public class SimpleApplication {
    public static void main (String args[]) {

        SparkConf conf = new SparkConf().setAppName("SimpleApplication").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> data = sc.textFile("D:\\Git\\Coursera\\Capstone\\resources\\airports.csv");

    }
}
