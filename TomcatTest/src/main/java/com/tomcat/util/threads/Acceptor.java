package com.tomcat.util.threads;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Acceptor implements Runnable{

	public void run() {
		ServerSocketChannel serverSocketChannel = null;
		try {
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(8080));
			serverSocketChannel.configureBlocking(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true){
			try {
				SocketChannel socketChannel = serverSocketChannel.accept();
				socketChannel.configureBlocking(false);
				@SuppressWarnings("unused")
				Socket socket = socketChannel.socket();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}