package opiniones.rest;


import javax.xml.bind.annotation.XmlRootElement;

import arso.opiniones.servicios.OpinionResumen;

@XmlRootElement
public class ResumenExtendido {

	
		
		private String url;
		private OpinionResumen resumen;
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public OpinionResumen getResumen() {
			return resumen;
		}
		public void setResumen(OpinionResumen resumen) {
			this.resumen = resumen;
		}
		
	
	
	
}
