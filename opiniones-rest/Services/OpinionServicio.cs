using Opiniones.Modelo;
using Repositorio;

namespace Opiniones.Servicio
{
    
    public class OpinionResumen
    {
        public string Id {get; set; }

        public string Recurso {get; set; }
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

            opinion.Valoraciones.Add(valoracion);
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
                    Id = opinion.Id,
                    Recurso = opinion.Recurso
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
    }
}