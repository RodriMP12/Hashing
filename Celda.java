/*Practica 1 TPA*/
/*Grupo de TPA M21*/
//
//Rodrigo Munoz Pedraza(22062417)
//Fecha de Creacion: 31/03/2023
//Ultima Modificacion:31/03/2023 
//Version:1.8


public class Celda<Valor> {
	private int clave;
	private Valor valor;
	private int estado;
	
	public Celda(int clave, Valor valor) {
		super();
		this.clave = clave;
		this.valor = valor;
	}

	public Celda() {
		super();
	}


	/**
	 * Función que obtiene la clave de la celda
	 * @return clave 
	 */
	public int getClave() {
		return clave;
	}


	/**
	 * Función que establece la clave de la celda
	 * @param clave clave de la celda
	 */
	public void setClave(int clave) {
		this.clave = clave;
	}

	/**
	 * Función que obtiene el valor en la celda
	 * @return valor valor 
	 */
	public Valor getValor() {
		return valor;
	}

	/**
	 * Función que establece el valor del elemento 
	 * @param valor valor a establecer
	 */
	public void setValor(Valor valor) {
		this.valor = valor;
	}

	/**
	 * Función que obtiene el estado de la celda
	 * @return estado de la celda
	 */
	public int getEstado() {
		
		return estado;
	}

	/**
	 * Función que establece el estado de la celda
	 * @param estado estado a establecer
	 */
	public boolean setEstado(int estado) {
		//Para ver si la celda esta vacia, ocupado o borrado. Para ello utilizamos una setencia if que dira en que estado esta
		 if (estado == -1 || estado == 0 || estado == 1) {
	            this.estado = estado;
	            return true;
	        } else {
	            return false;
	        }
	}
	
	
	
	public boolean equals(Object obj) {
		return false;
		
	}
	  public String toString() {
	        String estadoStr;
	        switch (estado) {
	            case -1:
	                estadoStr = "BORRADO";
	                break;
	            case 0:
	                estadoStr = "VACIO";
	                break;
	            case 1:
	                estadoStr = "OCUPADO";
	                break;
	            default:
	                estadoStr = "DESCONOCIDO";
	                break;
	        }
	        return "Celda [clave=" + clave + ", valor=" + valor + ", estado=" + estadoStr + "]";
	    }
}

