package arso.eventos.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpinionResumen {
	
	
	@JsonProperty("NumeroValoraciones")
	private int NumeroValoraciones;
	@JsonProperty("MediaValoraciones")
	private float MediaValoraciones;
	
	public int getNumeroValoraciones() {
		return NumeroValoraciones;
	}
	public void setNumeroValoraciones(int NumeroValoraciones) {
		this.NumeroValoraciones = NumeroValoraciones;
	}
	public float getMediaValoraciones() {
		return MediaValoraciones;
	}
	public void setMediaValoraciones(float MediaValoraciones) {
		this.MediaValoraciones = MediaValoraciones;
	}
	
	



}
