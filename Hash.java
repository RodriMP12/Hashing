import java.util.Arrays;

/**
 * 
 * @author Rodrigo Muñoz 22062417
 *	Practica 1: Hashing
 * 	Fecha de creacion: 15/05/2023
 * 	Ultima modificacion: 28/05/2023
 * 
 * 
 */

public class Hash<Valor> {
	private Celda<Valor>[] contenedor;
	private int numElementos;
	private float alfaMaximo;


	/**
	 * Constructor principal de clase Hash
	 * @return nulo
	 */
	public Hash() {
		this(7, 0.8f);

	}

	/**
	 * Constructor con capacidad inicial de la clase Hash
	 * @param capacidad capacidad inicial de la clase
	 */
	public Hash(int capacidad) {
		this(capacidad, 0.8f);

	};

	/**
	 * Constructor con capacidad y factor de carga inicial (alfa) de la clase Hash
	 * @param capacidad capacidad inicial de la clase
	 * @param alfaMax factor de carga
	 */
	public Hash(int capacidad, float alfaMax) {
		contenedor = new Celda[capacidad];
		this.numElementos = 0;
		this.alfaMaximo = alfaMax;

	};

	/**
	 * Función para insertar un valor en la tabla
	 * @param clave clave del elemento a insertar
	 * @param v valor a insertar
	 * Primero comprueba si hay que redimensionar, en el caso de que no sea necesario, se inserta el elemento en la celda utilizando la funcion hash
	 */
	public void insertar(int clave, Valor v){
		int colisiones = 0;
		float capacidad = contenedor.length;
		// Compruebo cual seria el factor de carga tras insertar el elemento
		float factor = factorCarga() + (1f/capacidad);
		System.out.println("Factor de carga: " + factor);
		if (factor >= alfaMaximo ) {
			System.out.println("El factor de carga superara el umbral permitido al insertar el elemento");
			System.out.println("Redimensionando.");
			redimensionar();
		}
		int posicion = funcionHash(clave, 0);
		
		boolean duplicado = false;
		boolean especial = false;

		// Compruebo si se produce colision en celda 0 (dara siempre 0 y entrara en bucle) y la resuelvo linealmente
		if(posicion==0 && contenedor[posicion].getEstado() == 1) {
			System.out.println("Manejando colision especial en celda 0");
			especial=true;
		}
		
		while (hayColision(posicion) && duplicado==false && especial==false) {
			if(contenedor[posicion].getClave() == clave) {
				duplicado=true;
			}
			colisiones++;

			posicion = funcionHash(clave, colisiones);
		}

		// Resuelvo la colision en celda 0 linealmente
		if(especial==true) {
			while (posicion < contenedor.length && contenedor[posicion] != null && contenedor[posicion].getEstado() == 1) {
				posicion++;
			}
			System.out.println("Insertado: " + "("+clave+","+v+") " +"en celda: " + posicion);
			contenedor[posicion] = new Celda<>(clave, v);
			contenedor[posicion].setEstado(1);
			numElementos++;
		}

		// Si la clave del elemento no esta ya presente en la tabla, lo inserto
		if(duplicado==false && especial==false) {
			System.out.println("Insertado: " + "("+clave+","+v+") " +"en celda: " + posicion);
			contenedor[posicion] = new Celda<>(clave, v);
			contenedor[posicion].setEstado(1);
			numElementos++;
		}
	}


	/**
	 * 
	 * Función para borrar un valor en la tabla segun su clave 
	 * @param clave clave del valor a eliminar
	 * @return false si no ha sido eliminado, true si lo ha sido
	 */
	public boolean borrar(int clave) {
		int colisiones = 0;
		int posicion = funcionHash(clave, colisiones);
		while (hayColision(posicion)) {
			if (contenedor[posicion].getClave() == clave && contenedor[posicion].getEstado() == 1) {
				contenedor[posicion].setEstado(-1);
				numElementos--;
				return true;
			}
			colisiones++;
			posicion = funcionHash(clave, colisiones);
		}
		return false; // La clave no se encontró en la tabla hash
	}
	
	

/**
 * Funcion que devuelve el valor del elemento segun su clave
 * @param clave clave del valor a devolver
 * @return null si la clave no se encontro, valor si se encontro
 */
	public Valor get(int clave) {
		int colisiones = 0;
		int posicion = funcionHash(clave, colisiones);
		while (hayColision(posicion)) {
			if (contenedor[posicion].getClave() == clave && contenedor[posicion].getEstado() == 1) {
				return contenedor[posicion].getValor();
			}
			colisiones++;
			posicion = funcionHash(clave, colisiones);
		}
		return null; // La clave no se encontró en la tabla hash
	}

	/**
	 * Funcion que comprueba si la celda está vacía
	 * @return true si es vacía, false si no lo es
	 */
	public boolean esvacia(int indice) {

		if(contenedor[indice] == null || contenedor[indice].getEstado() == 0) {
			return true;
		}
		return false;



	}

	/**
	 * Función para obtener el número de elementos
	 * @return número de elementos
	 */
	public int getNumElementos() {
		return numElementos;
	}

	/**
	 * Función para establecer el número de elementos
	 * @param numElementos número de elementos
	 */
	public void setNumElementos(int numElementos) {
		this.numElementos = numElementos;
	}

	/**
	 * Función para obtener el factor de carga máximo
	 * @return alfaMaximo
	 */
	public float getAlfaMaximo() {
		return alfaMaximo;
	}

	/**
	 * Función para establecer el factor de carga máximo
	 * @param alfaMaximo factor de carga máximo
	 */
	public void setAlfaMaximo(float alfaMaximo) {
		if(alfaMaximo < 1.0 && alfaMaximo > 0.0 && alfaMaximo>this.alfaMaximo) {
			this.alfaMaximo = alfaMaximo;
		}

	}


/**
 * Funcion para calcular el factor de carga
 * @return factor de carga
 */
	public float factorCarga() {
		if (contenedor == null || contenedor.length == 0) {
			return 0.0f;
		}
		return (float) (numElementos) / (float) contenedor.length;
	}


	/**
	 * Función que comprueba si se ha producido una colisión
	 * @param index índice de colisión
	 * @return true si se ha producido colisión, false si no
	 */
	private boolean hayColision(int index) {

		//Esta condicion comprueba si la celda esta ocupada o no cogiendo el estado de la celda para ver si hay una colision
		if (contenedor[index]!=null && contenedor[index].getEstado()==1) {
			System.out.println("Colision en celda: " + index);
			System.out.println("Resolviendo...");
			return true;
		}

		return false;
	}

	/**
	 * Función hash
	 * @param clave
	 * @param colisiones
	 * @return
	 */
	private int funcionHash(int clave, int colisiones) {
		int h2 = 0;
		int h1 = hash1(clave);


		h2 = hash2(clave, colisiones);

		int hash = h1 + h2;

		hash = Math.abs(hash);


		return hash % contenedor.length ;

	}

	/**
	 * Función hash
	 * @param clave
	 * @return
	 */
	private int hash1(int clave) {

		int H1 = clave % contenedor.length;
		return H1;

	}

	/**
	 * Función hash
	 * @param clave
	 * @param colisiones
	 * @return
	 */
	private int hash2(int clave, int colisiones) {

		int H2 = colisiones * (7 - (clave % 7));
		return H2;
	}




	/**
	 * Funcion que redimensiona la tabla, busca el siguiente primo a 2N e inserta los valores originales en la nueva tabla
	 */
	private void redimensionar() {
		int antiguaCapacidad = contenedor.length;
		int nuevaCapacidad = 2 * antiguaCapacidad;

		// Busco el siguiente número primo por encima de la nueva capacidad
		nuevaCapacidad = siguientePrimo(nuevaCapacidad);

		// Creo una nueva tabla con la nueva capacidad
		Celda<Valor>[] nuevaTabla = new Celda[nuevaCapacidad];

		// Recorro la tabla original e inserto los elementos en la nueva tabla hash
		for (int i = 0; i < antiguaCapacidad; i++) {
			if (contenedor[i] != null && contenedor[i].getEstado() == 1) {
				int clave = contenedor[i].getClave();
				Valor valor = contenedor[i].getValor();
				int colisiones = 0;
				int H1 = clave % nuevaCapacidad;
				int H2 = colisiones * (7 - (clave % 7));
				int hash = H1 + H2;
				int posicion = hash % nuevaCapacidad;
				int colisiones2 = 0;
				while (nuevaTabla[posicion] != null && nuevaTabla[posicion].getEstado() == 1) {
					colisiones2++;
					H1 = clave % nuevaCapacidad;
					H2 = colisiones2 * (7 - (clave % 7));
					hash = H1 + H2;
					posicion = hash % nuevaCapacidad;
				}
				nuevaTabla[posicion] = new Celda<>(clave, valor);
				nuevaTabla[posicion].setEstado(1);
			}
		}

		// Sustituyo la tabla original por la nueva tabla ampliada
		contenedor = nuevaTabla;
	}



	/**
	 * Función que comprueba si un número es primo
	 * @param num número
	 * @return true si es primo, false si no
	 */
	private boolean esPrimo(int num) {

		boolean resultado = true;

		for(int i = 2; i < num; i++)
		{
			if(num % i == 0)
			{
				resultado = false;
			}
		}		
		return resultado;
	}

	/**
	 * Función que obtiene el siguiente número primo para redimesionar la tabla
	 * @return siguiente número primo
	 */
	private int siguientePrimo(int num) {
		while (!esPrimo(num)) {
			num++;
		}
		return num;
	}
	public String toString() {
		return Arrays.toString(contenedor);

	}
}

