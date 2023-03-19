package arso.restaurantes.modelo;

import java.util.LinkedList;

public class SitioTuristico {

		private String propiedad;
		private LinkedList<String> categorias;
		private LinkedList<String> enlaces;
		private String imagen;
		
		//Getters y setters.
		
		public String getPropiedad() {
			return propiedad;
		}
		public void setPropiedad(String propiedad) {
			this.propiedad = propiedad;
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
		public String getImagen() {
			return imagen;
		}
		public void setImagen(String imagen) {
			this.imagen = imagen;
		}
		
		// Metodo toString
		
		@Override
		public String toString() {
			return "SitioTuristico [Propiedad = " + propiedad + ", Categorias = " + categorias + ", Enlaces = " + enlaces
					+ ", Imagen = " + imagen + "]";
		}
		
		
		
}
