package arso.opiniones.servicios;

public class OpinionResumen {
	
	private String id;
	private String recurso;
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
	
	@Override
	public String toString() {
		return "RestauranteResumen [id=" + id + ", recurso=" + recurso + "]";
	}
	
	
}
