package com.loc.analyst.predict.offline;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.classification.SVMModel;
import org.apache.spark.mllib.classification.SVMWithSGD;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.rdd.RDD;

import com.loc.analyst.util.Constant;

import org.apache.spark.mllib.linalg.*;
import org.netlib.util.doubleW;

public class DisPre {// extends Thread {

	private static SparkConf sparkConf = null;
	private static JavaSparkContext sc = null;
	private static SVMModel model = null;

	public static void train(String path) {
		sparkConf = new SparkConf().setAppName("Classification");
		sc = new JavaSparkContext(sparkConf);
		// SVMModel model = null;
		JavaRDD<String> data = sc.textFile(path);
		JavaRDD<LabeledPoint> parsedData = data.map(
				line -> {
					String[] parts = line.split(",");
					double[] ds = Arrays.stream(parts[1].split(" "))
							.mapToDouble(Double::parseDouble).toArray();
					return new LabeledPoint(Double.parseDouble(parts[0]),
							Vectors.dense(ds));
				}).cache();

		SVMWithSGD svm = new SVMWithSGD();
		int numIterations = 200;
		// double stepsize = 1.0;
		// double regParam = 0.01;
		// double miniBatchFraction = 1.0;
		model = svm.train(parsedData.rdd(), numIterations);
		model = model.clearThreshold();
	}

	// @Override
	public void run() {
		train(Constant.HEARTMAP_PATH);

	}

	public static void predict(double[] dims) {
		System.out.println("结果是：" + model.predict(Vectors.dense(dims)));
	}

	public static void main(String[] args) {
		train(Constant.HEARTMAP_PATH);
		double[] p = { 60, 166, 98, 39.8, 1.94, 2.23, 62 };
		predict(p);
		double[] q = { 72, 144, 82, 36.0, 1.56, 2.41, 48 };
		predict(q);
	}
}
