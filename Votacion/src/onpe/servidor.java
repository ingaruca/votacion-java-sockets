package onpe;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class servidor {
	
	private static UsuarioONPE objUsuario;
	private static ArrayList<UsuarioONPE> listaUsuarios;

	public static void main(String[] args) {
		
		UsuarioONPE cliente;
		int nroConsultas = 0;
		String _dni;
		ServerSocket ss = null;
		
		try {
			
			ss = new ServerSocket(5051);
			System.out.println(".:: SERVIDOR CORRIENDO ::.");
			
			while(true) {
			
				Socket socket = ss.accept();
				InputStream entrada = socket.getInputStream();
				DataInputStream dis = new DataInputStream(entrada);
				_dni = dis.readUTF();
				
				cliente = consultaONPE(_dni);
				
				Socket socket2 = new Socket("localhost", 5052);
				OutputStream salida = socket2.getOutputStream();
				DataOutputStream dos = new DataOutputStream(salida);
				
				if (nroConsultas < 3) {
					dos.writeUTF("La informaci�n de su lugar de votaci�n es la siguiente: \n Lugar: " + cliente.getLugar() +
																						  "\n Es miembro?: " + cliente.getMiembro() +
																						  "\n Direcci�n: " + cliente.getDireccion() +
																						  "\n Ubicaci�n: " + cliente.getUbicacion() +
																						  "\n Nro. de Mesa: " + cliente.getNroMesa());
				}else {
					dos.writeUTF("Ha excedido el l�mite de consultas, vuelva a intentarlo ma�ana");
				}
				
				nroConsultas++;
			}
			
		}catch (IOException e) {
			System.out.println(e.getMessage().toString());
		}
		
		
	}
	
	private static void llenarDatos() {
		listaUsuarios = new ArrayList<UsuarioONPE>();
		
		objUsuario = new UsuarioONPE("47641883", "Colegio Trilce", "SI", "Av. Cuba 334", "Sal�n 5A", "135");
		listaUsuarios.add(objUsuario);
		
		objUsuario = new UsuarioONPE("07836659", "Colegio Buen Pastor", "SI", "Jr. Huamachuco 1228", "Sal�n 3C", "400");
		listaUsuarios.add(objUsuario);
		
		objUsuario = new UsuarioONPE("06749066", "Colegio Ramon Castilla", "NO", "Av. Nova Rio 444", "Sal�n 2D", "321");
		listaUsuarios.add(objUsuario);
		
		objUsuario = new UsuarioONPE("19921111", "Colegio Shingeki", "SI", "Av. Shina 2466", "Sal�n 7B", "204");
		listaUsuarios.add(objUsuario);
	}
	
	private static UsuarioONPE consultaONPE(String dni) {
		
		UsuarioONPE objConsulta = null;
		
		llenarDatos();
		for (UsuarioONPE usu : listaUsuarios) {
			if (usu.getDni().equals(dni)) {
				objConsulta = new UsuarioONPE(usu.getDni(),
											  usu.getLugar(),
											  usu.getMiembro(),
											  usu.getDireccion(),
											  usu.getUbicacion(),
											  usu.getNroMesa());
				break;
			}
		}
		
		
		return objConsulta;
		
	}

}
