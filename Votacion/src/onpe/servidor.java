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
					dos.writeUTF("La información de su lugar de votación es la siguiente: \n Lugar: " + cliente.getLugar() +
																						  "\n Es miembro?: " + cliente.getMiembro() +
																						  "\n Dirección: " + cliente.getDireccion() +
																						  "\n Ubicación: " + cliente.getUbicacion() +
																						  "\n Nro. de Mesa: " + cliente.getNroMesa());
				}else {
					dos.writeUTF("Ha excedido el límite de consultas, vuelva a intentarlo mañana");
				}
				
				nroConsultas++;
			}
			
		}catch (IOException e) {
			System.out.println(e.getMessage().toString());
		}
		
		
	}
	
	private static void llenarDatos() {
		listaUsuarios = new ArrayList<UsuarioONPE>();
		
		objUsuario = new UsuarioONPE("47641883", "Colegio Trilce", "SI", "Av. Cuba 334", "Salón 5A", "135");
		listaUsuarios.add(objUsuario);
		
		objUsuario = new UsuarioONPE("07836659", "Colegio Buen Pastor", "SI", "Jr. Huamachuco 1228", "Salón 3C", "400");
		listaUsuarios.add(objUsuario);
		
		objUsuario = new UsuarioONPE("06749066", "Colegio Ramon Castilla", "NO", "Av. Nova Rio 444", "Salón 2D", "321");
		listaUsuarios.add(objUsuario);
		
		objUsuario = new UsuarioONPE("19921111", "Colegio Shingeki", "SI", "Av. Shina 2466", "Salón 7B", "204");
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
