package arso.restaurantes.modelo;

import java.util.LinkedList;
import java.util.Objects;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import arso.repositorio.Identificable;

import arso.restaurantes.servicios.ObtenerSitiosTuristicos;

public class Restaurante implements Identificable{

	@BsonId
	@BsonRepresentation(BsonType.OBJECT_ID)
	private String id;
	
	private String nombre;
	private ResumenValoracion valoraciones;
	private String coordenadas;
	private LinkedList<SitioTuristico> sitiosTuristicos = new LinkedList<>();
	private LinkedList<Plato> platos = new LinkedList<>();
	private String idGestor;
	
	// Getters y setters.

	public String getIdGestor() {
		return idGestor;
	}

	public void setIdGestor(String idGestor) {
		this.idGestor = idGestor;
	}

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
	
	public ResumenValoracion getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(ResumenValoracion valoraciones) {
		this.valoraciones = valoraciones;
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
		
		return platos.removeIf(p -> p.getNombre().equals(plato));
	}
	
	public void updatePlato(Plato plato) {
		for(Plato p : platos) {
			if(p.getNombre().equals(plato.getNombre())) {
				p.setDescripcion(plato.getDescripcion());
				p.setPrecio(plato.getPrecio());
			}
		}
	}
	
	public LinkedList<SitioTuristico> obtenerSitiosTuristicosCerca() throws Exception{
		return ObtenerSitiosTuristicos.getSitiosTuristicosCerca();
	}

	// Metodo toString

	@Override
	public String toString() {
		return "Restaurante [ Identificador = " + id + ", Nombre = " + nombre + ", Coordenadas = " + coordenadas
				+ ", Sitios turisticos = " + sitiosTuristicos + ", Platos = " + platos + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(coordenadas, id, idGestor, nombre, platos, sitiosTuristicos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurante other = (Restaurante) obj;
		return Objects.equals(coordenadas, other.coordenadas) && Objects.equals(id, other.id)
				&& Objects.equals(idGestor, other.idGestor) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(platos, other.platos) && Objects.equals(sitiosTuristicos, other.sitiosTuristicos);
	}


	
	

}
