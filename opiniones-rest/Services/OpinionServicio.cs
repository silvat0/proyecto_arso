using Opiniones.Modelo;
using Repositorio;
using Opiniones.Evento;

namespace Opiniones.Servicio
{
    
    public class OpinionResumen
    {
        public int NumeroValoraciones {get; set; }

        public float MediaValoraciones {get; set;}
    }

    public interface IServicioOpinion
    {
        string Create(Opinion opinion);

        void AddValoracion(string idOpinion, Valoracion valoracion);

        Opinion GetOpinion(string idOpinion);

        void RemoveOpinion(string idOpinion);

        List<OpinionResumen> GetListadoOpiniones();
    }

    public class ServicioOpinion : IServicioOpinion
    {
        private Repositorio<Opinion, String> repositorio;

        public ServicioOpinion(Repositorio<Opinion, String> repositorio) 
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

        public string Create(Opinion opinion)
        {
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


        protected void notificarValoracion(EventoNuevaValoracion evento)
        {

        }
    }
}