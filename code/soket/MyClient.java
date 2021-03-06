package socket.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.URL;

public class MyClient {
	
	public static void main(String[] args) throws Exception{
		
		Socket socket = null;
		int countByte = 0;

		String serverIp = "127.0.0.1";
		URL url = new URL("http://postfiles4.naver.net/20140310_211/0404tkh_1394443104382MJH5t_JPEG/116.JPG?type=w1");
		InputStream urlIn = url.openStream();	
		
		// 서버타입 지정
		byte[] type = new byte[10];
		String serverType = "1";
		byte[] serverTypeArr = serverType.getBytes();
		System.arraycopy(serverTypeArr, 0, type, 0, serverTypeArr.length);
	
		// 파일이름 지정
		byte[] name = new byte[50];
		String filename = "소정환1023.jpg";
		byte[] filenameArr = filename.getBytes();
		System.arraycopy(filenameArr, 0, name, 0, filenameArr.length);
		
		
		try{
			//서버 연결
			System.out.println("client : 서버에 연결을 시도합니다......... 서버 IP : " + serverIp);
			socket = new Socket(serverIp, 8888);	
			System.out.println("client : 서버에 연결되었습니다");
			System.out.println(socket);
		
			OutputStream out = socket.getOutputStream();	
			InputStream in = socket.getInputStream();
			
			out.write(type);	// 서버에 서버타입 전송
			out.write(name);	// 서버에 파일이름 전송
			
			System.out.println("서버에 파일을 전송합니다.");
			
			while(true){	// 서버에 전송하기 위한 파일을 읽어서 서버로 보낸다.
				int data = urlIn.read();
				countByte++;
				out.write(data);
			
				if(data == -1){
					break;
				}
			}
			out.flush();
			
			System.out.println("파일 전송을 완료했습니다.");
			
			System.out.println("서버로부터 응답을 기다립니다.");
			
			
			///
			/// 이부분때문에 5시간을............결국.................죄송합니다....
			///
	///////////////////////////////////////////////////////////////////////
//			
//			DataInputStream dis = new DataInputStream(in);
//			
//			String msg = dis.readUTF();
//			System.out.println("메세지 : " + msg);
//			
	//////////////////////////////////////////////////////////////////////////		
			System.out.println("client : 서버와의 연결을 종료합니다....");
			
			urlIn.close();
			out.close();
			in.close();
			socket.close();
			
		}
		catch(ConnectException ce){
			ce.printStackTrace();
		}catch(IOException ie){
			ie.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}

}