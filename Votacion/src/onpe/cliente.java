package onpe;

import java.net.*;
import java.io.*;

public class cliente {

	public static void main(String[] args) {
		
		ServerSocket ss = null;
		
		try {
			
			ss = new ServerSocket(5052);
			System.out.println("Ingrese su número de DNI");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String codigo;
			
			codigo = br.readLine();
			Socket socket = new Socket("localhost", 5051);
			OutputStream salida = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(salida);
			dos.writeUTF(codigo);
			
			Socket socket2 = ss.accept();
			InputStream entrada = socket2.getInputStream();
			DataInputStream dis = new DataInputStream(entrada);
			System.out.println(dis.readUTF());
			
		} catch (Exception e) {

		}

	}

}
