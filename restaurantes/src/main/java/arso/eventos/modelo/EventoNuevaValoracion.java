package arso.eventos.modelo;

public class EventoNuevaValoracion {

	public String idOpinion;
	
	public Valoracion valoracion;
	
	public OpinionResumen opinionR;

	public String getIdOpinion() {
		return idOpinion;
	}

	public void setIdOpinion(String idOpinion) {
		this.idOpinion = idOpinion;
	}

	public Valoracion getValoracion() {
		return valoracion;
	}

	public void setValoracion(Valoracion valoracion) {
		this.valoracion = valoracion;
	}

	public OpinionResumen getOpinionR() {
		return opinionR;
	}

	public void setOpinionR(OpinionResumen opinionR) {
		this.opinionR = opinionR;
	}

	@Override
	public String toString() {
		return "EventoNuevaValoracion [idOpinion=" + idOpinion + ", valoracion=" + valoracion + ", opinionR=" + opinionR
				+ "]";
	}
	
	
	
	
}
