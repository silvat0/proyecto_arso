package arso.restaurantes;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;

public class Tarea1 {
	public static void main(String[] args) throws Exception{
		
		// Parametro del código postal
		String codigoPostal = "30007";
		
		// 1. Obtener una factoría
		DocumentBuilderFactory factoria =
				DocumentBuilderFactory.newInstance();
		// 2. Pedir a la factoría la construcción del analizador
		DocumentBuilder analizador = factoria.newDocumentBuilder();


		// 3. Analizar el documento
		//documento es la raiz
		Document documento = analizador.parse("http://api.geonames.org/findNearbyWikipedia?postalcode=" + codigoPostal + "&country=ES&username=arso"); 

		// Obtenemos todos los elementos del fichero .xml con el tag name "entry"
		NodeList lugares = documento.getElementsByTagName("entry");

		// Println para mostrar el número de elementos que contiene el fichero .xml con el tag name "entry"
		//System.out.println(lugares.getLength());

		// Bucle para iterar los elementos con el tag name "entry"
		for (int i = 0; i < lugares.getLength() ; i++) {
			// Obtenemos el item de la itaración
			Element lugar = (Element) lugares.item(i);
			// Obtenemos el valor del tag name "title"
			Element nombre = (Element) lugar.getElementsByTagName("title").item(0);

			String nombreLugar = nombre.getTextContent();

			System.out.println(nombreLugar);
		}
	}

}
