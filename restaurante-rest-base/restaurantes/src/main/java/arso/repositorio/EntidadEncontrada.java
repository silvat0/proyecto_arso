package arso.repositorio;



/*
 *  Excepción notificada si existe el identificador de la entidad.
 */

@SuppressWarnings("serial")
public class EntidadEncontrada extends Exception {
	
	public EntidadEncontrada(String msg, Throwable causa) {
		super(msg, causa);
	}

	public EntidadEncontrada(String msg) {
		super(msg);
	}

}
