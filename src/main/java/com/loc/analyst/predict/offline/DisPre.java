package com.loc.analyst.predict.offline;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.classification.SVMModel;
import org.apache.spark.mllib.classification.SVMWithSGD;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.rdd.RDD;

public class DisPre {
	public SVMModel predit(String path) {
		SparkConf sparkConf = new SparkConf().setAppName("Classification").setMaster("local[2]");
		JavaSparkContext sc = new JavaSparkContext(sparkConf);
		JavaRDD<String> data = sc.textFile(path);
		JavaRDD<LabeledPoint> parsedData = data.map(line -> {
			String[] parts = line.split(",");
			double[] ds = Arrays.stream(parts[1].split(" ")).mapToDouble(Double::parseDouble).toArray();
			return new LabeledPoint(Double.parseDouble(parts[0]), Vectors.dense(ds));
		}).cache();

		SVMWithSGD svm = new SVMWithSGD();
		int numIterations = 200;
		// double stepsize = 1.0;
		// double regParam = 0.01;
		// double miniBatchFraction = 1.0;
		SVMModel smodel = svm.train(parsedData.rdd(), numIterations);
		return smodel;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
