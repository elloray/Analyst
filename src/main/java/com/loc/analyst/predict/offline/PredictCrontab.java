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

public class PredictCrontab {
	
	private static JavaSparkContext sc = null;
	
	private static SVMModel model = null;

	public static void train(String path) {
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

	public void run() {
		train(Constant.HEARTMAP_PATH);

	}

	public static double predict(double[] dims) {
		System.out.println("结果是：" + model.predict(Vectors.dense(dims)));
		return model.predict(Vectors.dense(dims));
	}

	public static void main(String[] args, JavaSparkContext sc) {
		PredictCrontab.sc = sc;
		train(Constant.HEARTMAP_PATH);
	}
}
