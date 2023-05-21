package arso.eventos.modelo;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Valoracion {
	
	@JsonProperty("correo")
	private String correo;
	@JsonProperty("fecha")
	private String fecha;
	@JsonProperty("calificacion")
	private int calificacion;
	@JsonProperty("comentario")
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
