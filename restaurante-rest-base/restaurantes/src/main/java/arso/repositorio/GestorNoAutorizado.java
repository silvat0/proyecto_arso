package arso.repositorio;

/*
 *  Excepción notificada si no existe el identificador de la entidad.
 */

@SuppressWarnings("serial")
public class GestorNoAutorizado extends Exception {

	public GestorNoAutorizado(String msg, Throwable causa) {
		super(msg, causa);
	}

	public GestorNoAutorizado(String msg) {
		super(msg);
	}

}
