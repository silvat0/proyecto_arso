package arso.eventos.modelo;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Valoracion {
	
	@JsonProperty("Correo")
	private String correo;
	@JsonProperty("Fecha")
	private String fecha;
	@JsonProperty("Calificacion")
	private int calificacion;
	@JsonProperty("Comentario")
	private String comentario;
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getCalificacion() {
		return calificacion;
	}
	
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}
