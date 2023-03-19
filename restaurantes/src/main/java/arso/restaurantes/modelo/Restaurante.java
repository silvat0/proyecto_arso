package arso.restaurantes.modelo;

import java.util.LinkedList;

public class Restaurante {
	
	private int id;
	private String nombre;
	private String coordenadas;
	private LinkedList<SitioTuristico> sitiosTuristicos = new LinkedList<>();
		
	//Getters y setters.
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}
	public LinkedList<SitioTuristico> getSitiosTuristicos() {
		return sitiosTuristicos;
	}
	public void setSitiosTuristicos(LinkedList<SitioTuristico> sitiosTuristicos) {
		this.sitiosTuristicos = sitiosTuristicos;
	}
	
	//Metodo toString
	
	@Override
	public String toString() {
		return "Restaurante [ Identificador = " + id + ", Nombre = " + nombre + ", Coordenadas = " + coordenadas + ", Sitios turisticos = "
				+ sitiosTuristicos + "]";
	}
	
}
