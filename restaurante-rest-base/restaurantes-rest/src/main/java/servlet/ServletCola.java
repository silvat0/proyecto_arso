package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import arso.restaurantes.servicios.FactoriaServicios;
import arso.restaurantes.servicios.IServicioRestaurante;


@SuppressWarnings("serial")
public class ServletCola extends HttpServlet {
	
	@Override
    public void init() throws ServletException {
		FactoriaServicios.getServicio(IServicioRestaurante.class);

    }

}
