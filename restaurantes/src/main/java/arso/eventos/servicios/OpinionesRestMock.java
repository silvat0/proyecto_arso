package arso.eventos.servicios;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import arso.eventos.modelo.Opinion;
import arso.eventos.modelo.Valoracion;

public class OpinionesRestMock implements IOpinionesRestMock {

	private Map<String, Opinion> opiniones;
	private int idCounter;

	public OpinionesRestMock() {
		this.opiniones = new HashMap<>();
		this.idCounter = 0;
	}

	@Override
    public String create(String recurso) {
		String id = generateId();
        Opinion opinion = new Opinion();
        opinion.setId(id);
        opinion.setRecurso(recurso);
        opinion.setValoraciones(new LinkedList<Valoracion>());
        this.opiniones.put(recurso, opinion);
        return id;
    }

	@Override
    public Opinion getOpinion(String id) {
        return this.opiniones.get(id);
    }

	private String generateId() {
		return Integer.toString(this.idCounter++);
	}

}
