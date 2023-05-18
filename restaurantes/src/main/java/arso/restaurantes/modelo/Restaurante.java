package arso.restaurantes.modelo;

import java.util.LinkedList;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.bson.codecs.pojo.annotations.BsonId;
import arso.repositorio.memoria.Identificable;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Restaurante implements Identificable{

	@BsonId
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
	
/*public LinkedList<SitioTuristico> getSitio() throws Exception {
		
		LinkedList<SitioTuristico> sitios = new LinkedList<>();
		
		String codigoPostal = "30007";
		
		// 1. Obtener una factoría
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		// 2. Pedir a la factoría la construcción del analizador
		DocumentBuilder analizador = factoria.newDocumentBuilder();
		
		// 3. Analizar el documento
		// documento es la raiz
		Document documento = analizador.parse("http://api.geonames.org/findNearbyWikipedia?postalcode=" + codigoPostal
				+ "&country=ES&lang=es&username=arso");
		
		// Obtenemos todos los elementos del fichero .xml con el tag name "entry"
		NodeList lugares = documento.getElementsByTagName("entry");
		
		for (int i = 0; i < lugares.getLength(); i++) {
			// Obtenemos el item de la itaración
			Element lugar = (Element) lugares.item(i);
			// Obtenemos el valor del tag name "title"
			Element nombre = (Element) lugar.getElementsByTagName("title").item(0);
			
			Element wikipedia = (Element) lugar.getElementsByTagName("wikipediaUrl").item(0);

			String nombreWiki = wikipedia.getTextContent();
			
			String[] urlWikipedia = nombreWiki.split("/");
			String nombreToDBPedia = urlWikipedia[urlWikipedia.length - 1];
			
			String url = "https://es.dbpedia.org/data/" + nombreToDBPedia + ".json";
			BufferedReader lector = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
			JsonReader jsonReader = Json.createReader(lector);
			JsonObject obj = jsonReader.readObject();

			String result = java.net.URLDecoder.decode(nombreToDBPedia, StandardCharsets.UTF_8.name());

			JsonObject resume = obj.getJsonObject("http://es.dbpedia.org/resource/" + result);

			SitioTuristico sitio = new SitioTuristico();
			
			// Resumen
			
			JsonArray resumen = resume.getJsonArray("http://dbpedia.org/ontology/abstract");
			
			if (resumen == null) {
				continue;
			} else {
				for (JsonObject d : resumen.getValuesAs(JsonObject.class)) {
					sitio.setResumen(d.getString("value"));
				}
			}
			
			// Categorias

			JsonArray categorias = resume.getJsonArray("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");

			if (categorias == null) {
				continue;
			} else {
				LinkedList<String> cat = new LinkedList<>();
				for (JsonObject d : categorias.getValuesAs(JsonObject.class)) {
					cat.add(d.getString("value"));
				}

				sitio.setCategorias(cat);
			}
			
			// Enlaces
			
			JsonArray enlacesExternos = resume.getJsonArray("http://dbpedia.org/ontology/wikiPageExternalLink");

			if (enlacesExternos == null) {
				continue;
			} else {
				LinkedList<String> enlaces = new LinkedList<>();
				for (JsonObject d : enlacesExternos.getValuesAs(JsonObject.class)) {
					enlaces.add(d.getString("value"));
				}

				sitio.setEnlaces(enlaces);
			}
			
			// Imagenes
			
			JsonArray imagenWikimedia = resume.getJsonArray("http://es.dbpedia.org/property/imagen");

			if (imagenWikimedia == null) {
				continue;
			} else {
				LinkedList<String> imagenes = new LinkedList<>();
				for (JsonObject d : imagenWikimedia.getValuesAs(JsonObject.class)) {
					imagenes.add(d.getString("value"));
				}

				sitio.setImagen(imagenes);
			}
			
			sitios.add(sitio);
		}
		
		return sitios;
	}*/

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
