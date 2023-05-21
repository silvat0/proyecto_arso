using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace Opiniones.Modelo 
{
    public class Opinion
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id {get; set; }

        public string Recurso {get; set;}

        public List<Valoracion> Valoraciones {get; set; } = new List<Valoracion>();


        public int NumValoraciones
        {
            get { return Valoraciones.Count; }
        }

        public float MediaValoraciones
        {
            get
            {
                float suma = 0;

                foreach (Valoracion v in Valoraciones)
                {
                    suma += v.Calificacion;
                }

                if (NumValoraciones == 0)
                    return 0;

                return suma / (float) NumValoraciones;
            }
        }

    }

    public class Valoracion
    {
        public string Correo {get; set; }

        public DateTime Fecha {get; set; }

        public int Calificacion {get; set; }

        public string Comentario {get; set; }

    }


}