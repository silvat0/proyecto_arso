package arso.eventos.modelo;

import java.util.LinkedList;



public class Opinion {
	private String id;
	private String recurso;
	private LinkedList<Valoracion> valoraciones = new LinkedList<>();
	
	private int numValoraciones;
	private float mediaValoraciones;
	

	
	public int getNumValoraciones() {
		return numValoraciones;
	}
	public void setNumValoraciones(int numValoraciones) {
		this.numValoraciones = numValoraciones;
	}
	public float getMediaValoraciones() {
		return mediaValoraciones;
	}
	public void setMediaValoraciones(float mediaValoraciones) {
		this.mediaValoraciones = mediaValoraciones;
	}
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
	
	@Override
	public String toString() {
		return "Opinion [id=" + id + ", recurso=" + recurso + ", valoraciones=" + valoraciones + ", numValoraciones="
				+ numValoraciones + ", mediaValoraciones=" + mediaValoraciones + "]";
	}
	
	
	
	
}