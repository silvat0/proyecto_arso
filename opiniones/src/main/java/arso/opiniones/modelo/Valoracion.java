package arso.opiniones.modelo;

import java.time.LocalDate;

public class Valoracion {

	private String correo;
	private LocalDate fecha;
	private int calificacion;
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
	
	// Metodo toString

	@Override
	public String toString() {
		return "Valoracion [correo=" + correo + ", fecha=" + fecha + ", calificacion=" + calificacion + ", comentario="
				+ comentario + "]";
	}
	
	
}
