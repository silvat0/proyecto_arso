using Opiniones.Modelo;
using Repositorio;
using Opiniones.Evento;
using RabbitMQ.Client;
using System.Text.Json;
using System.Text;

namespace Opiniones.Servicio
{
    
    public class OpinionResumen
    {
        public int NumeroValoraciones {get; set; }

        public float MediaValoraciones {get; set;}
    }

    public interface IServicioOpinion
    {
        string Create(string recurso);

        void AddValoracion(string idOpinion, Valoracion valoracion);

        Opinion GetOpinion(string idOpinion);

        void RemoveOpinion(string idOpinion);

        List<OpinionResumen> GetListadoOpiniones();

        void notificarValoracion(EventoNuevaValoracion evento);
    }

    public class ServicioOpinion : IServicioOpinion
    {
        private Repositorio<Opinion, string> repositorio;

        public ServicioOpinion(Repositorio<Opinion, string> repositorio) 
        {
            this.repositorio = repositorio;
        }

        public void AddValoracion(string idOpinion, Valoracion valoracion)
        {
            Opinion opinion = repositorio.GetById(idOpinion);

            List<Valoracion> valoraciones = opinion.Valoraciones;

            valoraciones.RemoveAll(v => v.Correo == valoracion.Correo);

            opinion.Valoraciones.Add(valoracion);

            repositorio.Update(opinion);
        }

        public string Create(string recurso)
        {
            Opinion opinion = new Opinion();
            opinion.Recurso = recurso;

            return repositorio.Add(opinion);
        }

        public List<OpinionResumen> GetListadoOpiniones()
        {
            var resultado = new List<OpinionResumen>();

           foreach (String id in repositorio.GetIds())
           {
                var opinion = GetOpinion(id);
                var resumen = new OpinionResumen
                {
                    NumeroValoraciones = opinion.NumValoraciones,
                    MediaValoraciones = opinion.MediaValoraciones
                };
                resultado.Add(resumen);
           }

           return resultado;
        }

        public Opinion GetOpinion(string idOpinion)
        {
            return repositorio.GetById(idOpinion);
        }

        public void RemoveOpinion(string idOpinion)
        {
            Opinion opinion = repositorio.GetById(idOpinion);

            repositorio.Delete(opinion);
        }


        public void notificarValoracion(EventoNuevaValoracion evento)
        {
            var factory = new ConnectionFactory { Uri = new Uri("amqps://xsiunxnj:LHgwaWUo5_Lqv6IlGfGo-GBFYU7vIBuW@whale.rmq.cloudamqp.com/xsiunxnj") };
            using var connection = factory.CreateConnection();
            using var channel = connection.CreateModel();


            string exchangeName = "Nueva-Valoracion";
            bool durable = true;

            channel.ExchangeDeclare(exchangeName, ExchangeType.Fanout, durable: durable);

            /** Envío del mensaje **/



           string mensaje = JsonSerializer.Serialize(evento);

           var properties = channel.CreateBasicProperties();
           properties.Persistent = true;
            properties.ContentType = "application/json";

           var body = Encoding.UTF8.GetBytes(mensaje);

           channel.BasicPublish(exchange: exchangeName,
                     routingKey: "arso",
                     basicProperties: properties,
                     body: body);

        }
    }
}