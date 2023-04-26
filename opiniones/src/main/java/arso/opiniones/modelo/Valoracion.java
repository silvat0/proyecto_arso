package arso.opiniones.modelo;

import java.time.LocalDate;

public class Valoracion {

	private String correo;
	private LocalDate fecha;
	private float calificacion;
	private String comentario;
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public float getCalificacion() {
		return calificacion;
	}
	
	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
}
