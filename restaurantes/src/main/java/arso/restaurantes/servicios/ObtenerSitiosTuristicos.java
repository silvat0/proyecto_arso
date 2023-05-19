package arso.restaurantes.servicios;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import arso.restaurantes.modelo.SitioTuristico;

public class ObtenerSitiosTuristicos {

	public static LinkedList<SitioTuristico> getSitiosTuristicosCerca() throws Exception {

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
	}

}
