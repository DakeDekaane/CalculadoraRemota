import java.io.Serializable;

//Clase a utilizar, ocupamos la interfaz Serializable
public class Complejo implements Serializable{
	
	double real;
	double imaginario;

	public Complejo(double real, double imaginario) {
		this.real = real;
		this.imaginario = imaginario;
	}

	public Complejo suma(Complejo c) {
		double r_real = real + c.real;
		double r_img = imaginario + c.imaginario;
		return new Complejo(r_real,r_img);
	}

	public void mostrar() {
		System.out.println(real + "+" + imaginario + "i");
	}
}