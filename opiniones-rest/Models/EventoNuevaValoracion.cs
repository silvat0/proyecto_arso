using Opiniones.Modelo;
using Opiniones.Servicio;

namespace Opiniones.Evento 
{
    public class EventoNuevaValoracion
    {
        public string  idOpinion {get; set; }
        public Valoracion valoracion {get; set; }
        public OpinionResumen opinionR {get; set; }
    }

} 