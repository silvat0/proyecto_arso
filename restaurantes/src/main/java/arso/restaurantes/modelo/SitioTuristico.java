package arso.restaurantes.modelo;

import java.util.LinkedList;

public class SitioTuristico {

		private String resumen;
		private LinkedList<String> categorias;
		private LinkedList<String> enlaces;
		private LinkedList<String> imagen;
		
		//Getters y setters.
		
		public String getResumen() {
			return resumen;
		}
		public void setResumen(String resumen) {
			this.resumen = resumen;
		}
		public LinkedList<String> getCategorias() {
			return categorias;
		}
		public void setCategorias(LinkedList<String> categorias) {
			this.categorias = categorias;
		}
		public LinkedList<String> getEnlaces() {
			return enlaces;
		}
		public void setEnlaces(LinkedList<String> enlaces) {
			this.enlaces = enlaces;
		}
		public LinkedList<String> getImagen() {
			return imagen;
		}
		public void setImagen(LinkedList<String> imagen) {
			this.imagen = imagen;
		}
		
		// Metodo toString
		
		@Override
		public String toString() {
			return "SitioTuristico [Propiedad = " + resumen + ", Categorias = " + categorias + ", Enlaces = " + enlaces
					+ ", Imagen = " + imagen + "]";
		}
		
		
		
}
