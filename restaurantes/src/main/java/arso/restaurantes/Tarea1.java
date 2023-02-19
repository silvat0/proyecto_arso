package arso.restaurantes;

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
		Document documento = analizador.parse(
				"http://api.geonames.org/findNearbyWikipedia?postalcode=" + codigoPostal + "&country=ES&username=arso");

		// Obtenemos todos los elementos del fichero .xml con el tag name "entry"
		NodeList lugares = documento.getElementsByTagName("entry");

		// Println para mostrar el número de elementos que contiene el fichero .xml con
		// el tag name "entry"
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

			String nombreLugar = nombre.getTextContent();
			String nombreWiki = wikipedia.getTextContent();

			System.out.println(nombreLugar);

			String[] urlWikipedia = nombreWiki.split("/");
			String nombreToDBPedia = urlWikipedia[urlWikipedia.length - 1];
			// Aqui termino la obtención del nombre de la URL de wikipedia

			// Object obj = parser.parse(new FileReader("https://es.dbpedia.org/data/" +
			// nombreToDBPedia) + ".json");
			String url = "https://es.dbpedia.org/data/" + nombreToDBPedia + ".json";
			BufferedReader lector = new BufferedReader(new InputStreamReader(new URL(url).openStream()));

			String respuesta = "";
			String linea;
			while ((linea = lector.readLine()) != null) {
				respuesta += linea;
			}
			lector.close();

			// Convertir el contenido en formato JSON a un objeto Java
			JSONParser parser = new JSONParser();
			Object objeto = parser.parse(respuesta);
			JSONObject objetoJson = (JSONObject) objeto;

//			JSONArray array = (JSONArray) objetoJson.get("http://dbpedia.org/ontology/abstract");
//
//			if (array != null) {
//
//				for (int j = 0; j < array.size(); j++) {
//					JSONObject prueba = (JSONObject) array.get(j);
//
//					String nombrePlato = (String) prueba.get("type");
//					System.out.println("Type : " + nombrePlato);
//				}
//			}
			
			System.out.println(objetoJson.toJSONString());

		}
	}

}
