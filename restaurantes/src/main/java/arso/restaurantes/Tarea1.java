package arso.restaurantes;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Tarea1 {
	public static void main(String[] args) throws Exception {

		// Parametro del código postal
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

		// System.out.println(lugares.getLength());

		// Bucle para iterar los elementos con el tag name "entry"
		for (int i = 0; i < lugares.getLength(); i++) {
			// Obtenemos el item de la itaración
			Element lugar = (Element) lugares.item(i);
			// Obtenemos el valor del tag name "title"
			Element nombre = (Element) lugar.getElementsByTagName("title").item(0);

			// Obtengo el ultimo nombre de la URL de la wikipedia debido a que no se si
			// puede cambiar respecto al "title"
			// En el caso de que no cambie nunca sería pillar el elemento del "title" y
			// sustituir los espacios por "_"
			Element wikipedia = (Element) lugar.getElementsByTagName("wikipediaUrl").item(0);

			String nombreWiki = wikipedia.getTextContent();

			// System.out.println(nombreLugar);

			String[] urlWikipedia = nombreWiki.split("/");
			// System.out.println(urlWikipedia);
			String nombreToDBPedia = urlWikipedia[urlWikipedia.length - 1];
			// System.out.println(nombreToDBPedia);
			// Aqui termino la obtención del nombre de la URL de wikipedia

			// Usar reader de json --> Json.createReader(url).readObject
			String url = "https://es.dbpedia.org/data/" + nombreToDBPedia + ".json";
			BufferedReader lector = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
			JsonReader jsonReader = Json.createReader(lector);
			JsonObject obj = jsonReader.readObject();

			String result = java.net.URLDecoder.decode(nombreToDBPedia, StandardCharsets.UTF_8.name());
			System.out.println(result);

			JsonObject resume = obj.getJsonObject("http://es.dbpedia.org/resource/" + result);

			JsonArray resumen = resume.getJsonArray("http://dbpedia.org/ontology/abstract");

			System.out.println("Resumen : ");
			for (JsonObject d : resumen.getValuesAs(JsonObject.class)) {
				System.out.println(d.getString("value"));
			}

			System.out.println();

			JsonArray categorias = resume.getJsonArray("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");

			System.out.println("Categorías : ");
			for (JsonObject d : categorias.getValuesAs(JsonObject.class)) {
				System.out.println(d.getString("value"));
			}

			System.out.println();

			JsonArray enlacesExternos = resume.getJsonArray("http://dbpedia.org/ontology/wikiPageExternalLink");

			System.out.println("Enlaces externos : ");
			for (JsonObject d : enlacesExternos.getValuesAs(JsonObject.class)) {
				System.out.println(d.getString("value"));
			}

			System.out.println();

			JsonArray imagenWikimedia = resume.getJsonArray("http://es.dbpedia.org/property/imagen");

			System.out.println("Imagen en Wikimedia : ");
			for (JsonObject d : imagenWikimedia.getValuesAs(JsonObject.class)) {
				System.out.println(d.getString("value"));
			}

			System.out.println("------------------------------\n");

		}
	}

}
