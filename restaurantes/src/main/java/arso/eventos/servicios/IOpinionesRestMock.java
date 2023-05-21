package arso.eventos.servicios;

import arso.eventos.modelo.Opinion;

public interface IOpinionesRestMock {

	String create(String recurso);

	Opinion getOpinion(String id);

}