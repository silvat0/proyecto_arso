package arso.opiniones.modelo;

import java.util.LinkedList;

public class Opinion {
	
	private String id;
	private String recurso;
	private LinkedList<Valoracion> valoraciones = new LinkedList<>();
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getRecurso() {
		return recurso;
	}
	
	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}
	
	public LinkedList<Valoracion> getValoraciones() {
		return valoraciones;
	}
	
	public void setValoraciones(LinkedList<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}
	
	public int getNumValoraciones() {
		return valoraciones.size();
	}
	
	public float getMediaValoraciones() {
		
		float suma = 0;
		
		for(Valoracion v : valoraciones) {
			suma = suma + v.getCalificacion();
		}
		
		return suma/getNumValoraciones();
	}
	
	
}
