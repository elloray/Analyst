package com.loc.analyst.service;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;

import com.loc.analyst.predict.offline.DisPre;
import com.loc.analyst.util.Constant;

public class Server {
	public static void main(String[] args) {
		try {
			DisPre.main(args);
			TProcessor tprocessor = new baymax.Processor(new baymaxImpl());
			TServerSocket serverTransport = new TServerSocket(
					Constant.SERVER_PORT);
			org.apache.thrift.server.TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(
					serverTransport);
			tArgs.processor(tprocessor);
			tArgs.protocolFactory(new TBinaryProtocol.Factory());
			TServer server = new TThreadPoolServer(tArgs);
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
