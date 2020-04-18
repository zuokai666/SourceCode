package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class HttpTest {

	public static void main(String[] args) {
		Socket client = null;
		try {
			client = new Socket();
			InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8080);
			client.connect(inetSocketAddress, 10_000);
			int count = 0;
			while(true){
				PrintWriter pWriter = new PrintWriter(client.getOutputStream(), true);
				pWriter.println("GET /myapp/userServlet HTTP/1.1");
				pWriter.println("Host: localhost:8080");
				pWriter.println("Connection: keep-alive");
				pWriter.println("Cache-Control: max-age=0");
				pWriter.println("User-Agent: Socket");
				pWriter.println();// 注意这里要换行结束请求头
				
				String line = null;
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream(), "utf-8"));
				while(!"".equals(line = bufferedReader.readLine())) {
					if(line == null){
						System.err.println(line);
						break;
					}else {
						System.out.println(line);
					}
				}
//				while((line = bufferedReader.readLine()) != null) {
//				    System.out.println(line);
//				}
				
//				Thread.sleep(1000);
				count++;
				if(count == 99){
					System.out.println(99);
				}
				
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}