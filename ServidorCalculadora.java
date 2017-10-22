import java.net.Socket;
import java.net.ServerSocket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;


public class ServidorCalculadora {
	public static void main(String args[]) {
		//Declaramos los sockets y el puerto a utilizar
		ServerSocket socket_servidor = null;
		Socket socket_cliente = null;
		int puerto = 5500;
		
		//Objetos que nos ayudarán a la serialización de nuestro objeto
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		//Variables del programa para almacenar datos y realizar calculos
		Complejo arr_comp[] =  new Complejo[3];
		Complejo c;
		int i = 0;

		try {
			//Inicializamos nuestro socket servidor con el puerto definido
			socket_servidor = new ServerSocket(puerto);
			//Inicializamos nuestro socket cliente 
			socket_cliente = socket_servidor.accept();

			//Inicializamos los objetos ObjectOutputStream y ObjectInputStream con los sockets de salida y entrada, respectivamente
			oos = new ObjectOutputStream(socket_cliente.getOutputStream());
		    ois = new ObjectInputStream(socket_cliente.getInputStream());

		    //Hasta 2 datos
			while(i < 2) {
				//Nos mantenemos en espera y hacemos un casting del flujo de bytes recibido a la clase deseada
				c = (Complejo)ois.readObject();	
				arr_comp[i] = c;
				i++;
			}
			//Operaciones del programa
			arr_comp[2] = arr_comp[0].suma(arr_comp[1]);

			//Mandamos el objeto a traves del flujo de salida (ObjectOutputStream)
			oos.writeObject(arr_comp[2]);
		}
		//Manejo de excepciones genérico (no el mejor)
		catch(Exception e){
			System.err.println(e.getMessage());
			System.exit(1);
		}
		finally {
			//Cerramos los flujos y sockets
			try {
				oos.close();
				socket_cliente.close();
			}
			catch(IOException e) {}
		}
	}
}