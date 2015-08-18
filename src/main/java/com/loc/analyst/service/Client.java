package com.loc.analyst.service;

import java.util.Arrays;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class Client {
	public static void main(String[] args) {
		try {
			TTransport transport = new TSocket("127.0.0.1", 9001);
			transport.open();
			TProtocol protocol = new TBinaryProtocol(transport);
			baymax.Client client = new baymax.Client(protocol);
			String[] p = { "72", "144", "82", "36.0", "1.56", "2.41", "48" };
			System.out.println(client.isSick(Arrays.asList(p)));
			System.out.println(client.recommandHospital("1"));
			transport.close();
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
	}

}
