package arso.restaurantes.modelo;

import java.util.LinkedList;

import arso.repositorio.Identificable;

public class Restaurante implements Identificable{

	private String id;
	private String nombre;
	private String coordenadas;
	private LinkedList<SitioTuristico> sitiosTuristicos = new LinkedList<>();
	private LinkedList<Plato> platos = new LinkedList<>();

	// Getters y setters.

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public LinkedList<Plato> getPlatos() {
		return platos;
	}

	public void setPlatos(LinkedList<Plato> platos) {
		this.platos = platos;
	}
	
	// Metodos 
	
	public boolean addPlato(Plato plato) {
		if(platos.contains(plato)) {
			return false;
		}
		else {
			for(Plato p : platos) {
				if(p.getNombre().equals(plato.getNombre())) {
					return false;
				}
			}
		}
		
		platos.add(plato);
		return true;
	}
	
	public boolean removePlato(String plato) {
		for(Plato p : platos) {
			if(p.getNombre().equals(plato)) {
				platos.remove(p);
				return true;
			}
		}
		
		return false;
	}
	
	public void updatePlato(Plato plato) {
		for(Plato p : platos) {
			if(p.getNombre().equals(plato.getNombre())) {
				p.setDescripcion(plato.getDescripcion());
				p.setPrecio(plato.getPrecio());
			}
		}
	}

	// Metodo toString

	@Override
	public String toString() {
		return "Restaurante [ Identificador = " + id + ", Nombre = " + nombre + ", Coordenadas = " + coordenadas
				+ ", Sitios turisticos = " + sitiosTuristicos + ", Platos = " + platos + "]";
	}

}
