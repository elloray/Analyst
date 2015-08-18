//package com.loc.analyst.api;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.apache.thrift.TException;
//import org.apache.thrift.protocol.TBinaryProtocol;
//import org.apache.thrift.protocol.TProtocol;
//import org.apache.thrift.transport.TSocket;
//import org.apache.thrift.transport.TTransport;
//import org.apache.thrift.transport.TTransportException;
//
//import com.fasterxml.jackson.databind.Module.SetupContext;
//
//public class Client {
//
//	private baymax.Client client = null;
//
//	private void setup(String ipString, int port) throws TTransportException {
//		// 设置调用的服务地址为本地，端口为 7911
//		TTransport transport = new TSocket(ipString, port);
//		transport.open();
//		// 设置传输协议为 TBinaryProtocol
//		TProtocol protocol = new TBinaryProtocol(transport);
//		baymax.Client client = new baymax.Client(protocol);
//	}
//
//	public void main(String ipString, int port) {
//		try {
//			setup(ipString, port);
//			// 调用服务的 helloVoid 方法
//			String[] p = { "72", "144", "82", "36.0", "1.56", "2.41", "48" };
//			System.out.println(client.isSick(Arrays.asList(p)));
//			System.out.println(client.recommandHospital("1"));
//		} catch (TException e) {
//			e.printStackTrace(
//	public String isSick(List<String> p){
////		return client.isSick(Arrays.asList(p));
//	}
//}
