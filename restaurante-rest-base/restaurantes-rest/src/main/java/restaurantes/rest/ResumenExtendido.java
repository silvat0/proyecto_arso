package restaurantes.rest;


import javax.xml.bind.annotation.XmlRootElement;

import arso.restaurantes.servicios.RestauranteResumen;

@XmlRootElement
public class ResumenExtendido {

	
		
		private String url;
		private RestauranteResumen resumen;
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public RestauranteResumen getResumen() {
			return resumen;
		}
		public void setResumen(RestauranteResumen resumen) {
			this.resumen = resumen;
		}
		
	
	
	
}
