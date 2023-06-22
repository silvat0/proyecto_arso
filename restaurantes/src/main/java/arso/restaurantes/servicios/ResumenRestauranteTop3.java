package arso.restaurantes.servicios;

import arso.restaurantes.modelo.ResumenValoracion;

public class ResumenRestauranteTop3 {
	

	private String id;
	private String nombre;
	private ResumenValoracion valoraciones;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ResumenValoracion getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(ResumenValoracion valoraciones) {
		this.valoraciones = valoraciones;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	

}