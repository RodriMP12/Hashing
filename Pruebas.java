
public class Pruebas {
	public static void main(String[] args) {
		
		// Creo un nuevo objeto Hash con capacidad 7 y alfaMaximo 0.8
		Hash<String> hash = new Hash<>(7, 0.8f);

		// Inserto algunos elementos en el hash
		hash.insertar(1, "Elemento 1");
		hash.insertar(18, "Elemento 2");
		hash.insertar(4, "Elemento 3");
		// Elemento duplicado, no se debe insertar
		hash.insertar(1, "Elemento 4");

		hash.insertar(5, "Elemento 5");
		
		System.out.println(hash);
		
		hash.borrar(5);
		// Compruebo que la celda donde se encuentra el elemento con clave 5 esta borrada
		System.out.println(hash);
		
		// Inserto el 19 que ocupara la celda donde se borro el 5
		hash.insertar(19, "Elemento 5");
		System.out.println(hash);
		
		// Insertamos hasta superar el alfaMaximo
		hash.insertar(6, "Elemento 6");
		hash.insertar(23, "Elemento 7");
		System.out.println(hash);
		
		// Devuelve elemento 2
		System.out.println("Clave 18: " + hash.get(18));
		// Devuelve null ya que no existe en nuestra tabla
		System.out.println("Clave 25: " + hash.get(25));
		
		Hash<String> hash2 = new Hash<>(7, 0.8f);
		
		// Modificamos el alfaMaximo de nuestra nueva tabla hash y comprobamos que funciona	
		hash2.setAlfaMaximo(0.85f);
		System.out.println("Alfa maximo de hash2: " + hash2.getAlfaMaximo());
		
		// Comprobamos funcionamiento de la funcion get
		System.out.println("Numero de elementos en hash: " + hash.getNumElementos());
		System.out.println("Numero de elementos en hash2: " + hash2.getNumElementos());
		
		
		
	
		
	
		

		
	






	}


}

