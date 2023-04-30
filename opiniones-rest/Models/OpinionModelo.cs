using System;
using System.Collections.Generic;
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

    }

    public class Valoracion
    {
        public string Correo {get; set; }

        public DateTime Fecha {get; set; }

        public float Calificacion {get; set; }

        public string Comentario {get; set; }

    }


}