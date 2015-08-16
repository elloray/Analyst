package com.loc.analyst.service;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.apache.thrift.TException;
import org.netlib.util.doubleW;
import org.netlib.util.intW;

import com.loc.analyst.predict.offline.DisPre;
import com.loc.analyst.recommand.offline.HosRecom;

public class baymaxImpl implements baymax.Iface {

	@Override
	public String isSick(List<String> dims) throws TException {
		// TODO Auto-generated method stub
		double[] value = new double[dims.size()];
		int i = 0;
		for (String string : dims) {
			value[i] = Double.parseDouble(string);
			i++;
		}
		if (DisPre.predict(value) < 0) {
			return "Sick";
		} else {
			return "OK";
		}

	}

	@Override
	public List<String> recommandHospital(String city) throws TException {
		// TODO Auto-generated method stub
		return Arrays.asList(HosRecom.Recom(city).split("_"));
	}

}
