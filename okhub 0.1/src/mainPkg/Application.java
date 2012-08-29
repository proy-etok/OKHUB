package mainPkg;

import pruebaPkg.*;

public class Application 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		
		System.out.print("presione 1 2 o 3 para llamar al metodo run de la clase respectiva: \n");
		
		char claseElegida = U.leerChar();
	
		switch(claseElegida)
		{
		case '1': (new Clase1()).run(); break;
		case '2': (new Clase2()).run(); break;
		case '3': (new Clase3()).run(); break;
		default:  System.out.print("Opcion no valida, saliendo, chau"); break;
		}
		
	}

}
