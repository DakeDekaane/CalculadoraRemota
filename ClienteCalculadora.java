import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ClienteCalculadora {
	public static void main(String args[]){

		//Declaramos el socket y el puerto a utilizar, así como la dirección IP a la que nos vamos a conectar
		Socket socket = null;
		int puerto = 5500;
		String ip = "127.0.0.1";


		//Objetos que nos ayudarán a la serialización de nuestros objetos
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;

		//Variables del programa para el manejo del mismo
		Scanner leer = new Scanner(System.in);
		Complejo c,c1,c2;
		double a,b;
		
		try {
			//Inicializamos el socket con la dirección IP y el puerto
			socket = new Socket(ip, puerto);

			//Inicializamos los objetos ObjectOutputStream y ObjectInputStream con los sockets de salida y entrada, respectivamente
    		ois = new ObjectInputStream(socket.getInputStream());
		    oos = new ObjectOutputStream(socket.getOutputStream());

		    //Ingreso de datos)
		    System.out.println("Ingrese la parte real del primer numero complejo");
		    a = leer.nextDouble();
		    System.out.println("Ingrese la parte imaginaria del primer numero complejo");
		    b = leer.nextDouble();
		    
		    //Creamos un objeto
		    c1 = new Complejo(a,b);

		    //Y lo enviamos por el flujo de salida
		    oos.writeObject(c1);
		    
		    //Ingreso de datos)
		    System.out.println("Ingrese la parte real del segundo numero complejo");
		    a = leer.nextDouble();
		    System.out.println("Ingrese la parte imaginaria del segundo numero complejo");
		    b = leer.nextDouble();

		    //Creamos otro objeto
		    c2 = new Complejo(a,b);

		    //Y lo enviamos por el flujo de salida
		    oos.writeObject(c2);

		    //Nos mantenemos en espera de la respuesta de el servidor, 
		    //haciendo un casting del flujo de bytes recibido
			while((c = (Complejo)ois.readObject()) != null) {
				c.mostrar();
			}
		}

		//Manejo de excepciones genérico (no el mejor)
		catch(IOException e ){

		}
		catch(Exception e){
			System.err.println(e.getMessage());
			System.exit(1);
		}

		finally {
			//Cerramos los flujos y sockets
			try {
				ois.close();
				oos.close();
				socket.close();
			}
			catch(IOException e) {}
		}
	}
}
