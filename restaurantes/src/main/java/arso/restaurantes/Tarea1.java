package arso.restaurantes;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;

public class Tarea1 {
	public static void main(String[] args) throws Exception{
		// 1. Obtener una factoría
		DocumentBuilderFactory factoria =
				DocumentBuilderFactory.newInstance();
		// 2. Pedir a la factoría la construcción del analizador
		DocumentBuilder analizador = factoria.newDocumentBuilder();


		// 3. Analizar el documento
		//documento es la raiz
		Document documento = analizador.parse("http://api.geonames.org/findNearbyWikipedia?postalcode=30007&country=ES&username=arso"); 

		NodeList lugares = documento.getElementsByTagName("entry");

		//System.out.println(lugares.getLength());

		for (int i = 0; i < lugares.getLength() ; i++) {
			Element lugar = (Element) lugares.item(i);
			Element nombre = (Element) lugar.getElementsByTagName("title").item(0);

			String nombreLugar = nombre.getTextContent();

			System.out.println(nombreLugar);
		}
	}

}
