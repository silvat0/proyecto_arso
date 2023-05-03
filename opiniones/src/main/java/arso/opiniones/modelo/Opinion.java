package arso.opiniones.modelo;

import java.util.LinkedList;

import arso.repositorio.memoria.Identificable;

public class Opinion implements Identificable{
	
	private String id;
	private String recurso;
	private LinkedList<Valoracion> valoraciones = new LinkedList<>();
	
	// Getters y setters.
	
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
	
	public boolean addValoracion(Valoracion valoracion) {
		
		valoraciones.removeIf(v -> v.getCorreo().equals(valoracion.getCorreo()));
		
		return valoraciones.add(valoracion);
		
		/*for(Valoracion v : valoraciones) {
			if(v.getCorreo().equals(valoracion.getCorreo())) {
				band = true;
				valoraciones.remove(v);
				valoraciones.add(valoracion);
			}
		}
		
		if(band == false) {
			valoraciones.add(valoracion);
		}*/
	}

	// Metodo toString
	
	@Override
	public String toString() {
		return "Opinion [id=" + id + ", recurso=" + recurso + ", valoraciones=" + valoraciones + "]";
	}
	
	
	
	
}
