package mainPkg;

import pruebaPkg.*;

public class Application 
{

	/**
	 * @param args
	 * Comentario absurdo e innecesario 
	 */
	public static void main(String[] args) 
	{
		
		System.out.print("presione 1 2 3 o 4 o 5 para llamar al metodo run de la clase respectiva: \n");
		
		char claseElegida = U.leerChar();
	
		switch(claseElegida)
		{
		case '1': (new Clase1()).run(); break;
		case '2': (new Clase2()).run(); break;
		case '3': (new Clase3()).run(); break;
		case '4': (new Clase4()).run(); break;
		case '5': System.out.print("Opcion valida"); break;
		default:  System.out.print("Opcion no valida, saliedo"); break;
		}
		
	}

}
