package mysqlPkg;

public class Management_Aplication {

	public Management_Aplication(String usuario, String password){
		
		Sesion inicio = new Sesion();
		inicio.conectarse(usuario, password);
	}
}
