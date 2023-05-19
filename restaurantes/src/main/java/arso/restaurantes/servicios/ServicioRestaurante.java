package arso.restaurantes.servicios;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import arso.eventos.EventoNuevaValoracion;
import arso.opiniones.modelo.Opinion;
import arso.opiniones.modelo.Valoracion;
import arso.repositorio.memoria.EntidadEncontrada;
import arso.repositorio.memoria.EntidadNoEncontrada;
import arso.repositorio.memoria.FactoriaRepositorios;
import arso.repositorio.memoria.IRepositorio;
import arso.repositorio.memoria.RepositorioException;
import arso.restaurantes.modelo.Plato;
import arso.restaurantes.modelo.Restaurante;
import arso.restaurantes.modelo.SitioTuristico;
import arso.restaurantes.retrofit.OpinionesRest;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ServicioRestaurante implements IServicioRestaurante {

	private IRepositorio<Restaurante, String> repositorio = FactoriaRepositorios.getRepositorio(Restaurante.class);

	private OpinionesRest opinionesRest;

	public ServicioRestaurante() {
//		Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8090")
//				.addConverterFactory(JacksonConverterFactory.create()).build();
//
//		opinionesRest = retrofit.create(OpinionesRest.class);

		this.suscribirse();

	}

	@Override
	public String create(Restaurante restaurente) throws RepositorioException {
		return repositorio.add(restaurente);
	}

	@Override
	public void update(String idRestaurante, String nombre, String coordenadas)
			throws RepositorioException, EntidadNoEncontrada {
		Restaurante restaurante = repositorio.getById(idRestaurante);
		restaurante.setNombre(nombre);
		restaurante.setCoordenadas(coordenadas);

		repositorio.update(restaurante);
	}

	@Override
	public boolean addPlato(String idRestaurante, Plato plato)
			throws RepositorioException, EntidadNoEncontrada, EntidadEncontrada {
		Restaurante restaurante = repositorio.getById(idRestaurante);
		boolean bool = restaurante.addPlato(plato);
		repositorio.update(restaurante);
		return bool;
	}

	@Override
	public boolean removePlato(String idRestaurante, String plato) throws RepositorioException, EntidadNoEncontrada {
		Restaurante restaurante = repositorio.getById(idRestaurante);
		boolean bool = restaurante.removePlato(plato);
		repositorio.update(restaurante);
		return bool;
	}

	@Override
	public void updatePlato(String idRestaurante, Plato plato) throws RepositorioException, EntidadNoEncontrada {
		Restaurante restaurante = repositorio.getById(idRestaurante);

		restaurante.updatePlato(plato);
		repositorio.update(restaurante);
	}

	@Override
	public Restaurante getRestaurante(String idRestaurante) throws RepositorioException, EntidadNoEncontrada {
		return repositorio.getById(idRestaurante);
	}

	@Override
	public void removeRestaurante(String idRestaurante) throws RepositorioException, EntidadNoEncontrada {
		Restaurante restaurante = repositorio.getById(idRestaurante);

		repositorio.delete(restaurante);
	}

	@Override
	public List<SitioTuristico> getSitiosTuristicos(String idRestaurante) throws Exception {
		Restaurante restaurante = repositorio.getById(idRestaurante);

		return restaurante.getSitiosTuristicos();
	}

	@Override
	public void establecerSitiosTuristicos(String idRestaurante, LinkedList<SitioTuristico> sitiosTuristicos)
			throws RepositorioException, EntidadNoEncontrada {
		Restaurante restaurante = repositorio.getById(idRestaurante);

		restaurante.setSitiosTuristicos((LinkedList<SitioTuristico>) sitiosTuristicos);
		repositorio.update(restaurante);
	}

	@Override
	public List<RestauranteResumen> getListadoRestaurantes() throws RepositorioException {

		LinkedList<RestauranteResumen> resultado = new LinkedList<>();

		for (String id : repositorio.getIds()) {
			try {
				Restaurante restaurante = getRestaurante(id);
				RestauranteResumen resumen = new RestauranteResumen();
				resumen.setId(restaurante.getId());
				resumen.setCoordenadas(restaurante.getCoordenadas());
				resumen.setNombre(restaurante.getNombre());
				resultado.add(resumen);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return resultado;
	}

	protected void suscribirse() {

		try {
			ConnectionFactory factory = new ConnectionFactory();

			factory.setUri("amqps://xsiunxnj:LHgwaWUo5_Lqv6IlGfGo-GBFYU7vIBuW@whale.rmq.cloudamqp.com/xsiunxnj");

			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			final String exchangeName = "Nueva-Valoracion";
			final String queueName = "Nueva-Valoracion";
			final String bindingKey = "arso";

			boolean durable = true;
			boolean exclusive = false;
			boolean autodelete = false;
			Map<String, Object> properties = null; // sin propiedades
			channel.queueDeclare(queueName, durable, exclusive, autodelete, properties);

			channel.queueBind(queueName, exchangeName, bindingKey);

			boolean autoAck = false;
			String cola = "Nueva-Valoracion";
			String etiquetaConsumidor = "arso-consumidor";

			channel.basicConsume(cola, autoAck, etiquetaConsumidor,

					new DefaultConsumer(channel) {
						@Override
						public void handleDelivery(String consumerTag, Envelope envelope,
								AMQP.BasicProperties properties, byte[] body) throws IOException {

							long deliveryTag = envelope.getDeliveryTag();

							String contenido = new String(body);
							// System.out.println(contenido);

							ObjectMapper mapper = new ObjectMapper();

							EventoNuevaValoracion evento = mapper.readValue(contenido, EventoNuevaValoracion.class);

							try {
								for (Restaurante r : repositorio.getAll()) {
									if (r.getValoraciones() != null) {
										if (r.getValoraciones().getIdOpinion().equals(evento.getIdOpinion())) {
											r.getValoraciones()
													.setCalificacionMedia(evento.getOpinionR().getMediaValoraciones());
											r.getValoraciones()
													.setNumValoraciones(evento.getOpinionR().getNumeroValoraciones());
										}

										System.out.println(r.getValoraciones().getIdOpinion());
										System.out.println(r.getValoraciones().getCalificacionMedia());
										System.out.println(r.getValoraciones().getNumValoraciones());
									}

								}

							} catch (RepositorioException e) {
								e.printStackTrace();
							}

							// Confirma el procesamiento
							channel.basicAck(deliveryTag, false);
						}
					});

			System.out.println("consumidor esperando ...");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*public void crearOpinion(String idRestaurante) throws RepositorioException, EntidadNoEncontrada, IOException{
		Restaurante restaurante = repositorio.getById(idRestaurante);
		
		Opinion opinion = new Opinion();
		opinion.setRecurso(restaurante.getNombre());
		
		opinionesRest.create(opinion).execute();
	}

	public List<Valoracion> getValoraciones(String idRestaurante) throws RepositorioException, EntidadNoEncontrada, IOException{
		Restaurante restaurante = repositorio.getById(idRestaurante);
		Opinion o = opinionesRest.getOpinion(restaurante.getValoraciones().getIdOpinion()).execute().body();
		return o.getValoraciones();
	}*/

}