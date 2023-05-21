using MongoDB.Driver;
using Repositorio;
using Opiniones.Modelo;

namespace Opiniones.Repositorio
{
    public class RepositorioOpinionesMongoDB : Repositorio<Opinion, string>
    {

        private readonly IMongoCollection<Opinion> opiniones;

        public RepositorioOpinionesMongoDB() 
        {
            var client = new MongoClient("mongodb://localhost:27017/ProyectoArso");
            var database = client.GetDatabase("ProyectoArso");

            opiniones = database.GetCollection<Opinion>("opinion.net");
        }

        public string Add(Opinion entity)
        {
            opiniones.InsertOne(entity);

            return entity.Id;
        }

        public void Update(Opinion entity)
        {
            opiniones.ReplaceOne(opinion => opinion.Id == entity.Id, entity);
        }

        public void Delete(Opinion entity)
        {
            opiniones.DeleteOne(opinion => opinion.Id == entity.Id);
        }

        public List<Opinion> GetAll()
        {
            return opiniones.Find(_ => true).ToList();    
        }

        public Opinion GetById(String id)
        {
            return opiniones.Find(opinion => opinion.Id == id).FirstOrDefault();
        }

        public List<string> GetIds()
        {
            var todas = opiniones.Find(_ => true).ToList();

            return todas.Select(o => o.Id).ToList();
        }



    }
}