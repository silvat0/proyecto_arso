using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using Opiniones.Modelo;
using Opiniones.Servicio;

namespace Opiniones.Controllers
{

    [Route("api/opiniones")]
    [ApiController]
    public class OpinionesController : ControllerBase
    {
        private readonly IServicioOpinion _servicio;

        public OpinionesController(IServicioOpinion servicio)
        {
            _servicio = servicio;
        }

        [HttpGet]
        public ActionResult<List<OpinionResumen>> GetOpiniones() =>
            _servicio.GetListadoOpiniones();

        [HttpGet("{id}", Name = "GetOpinion")]
        public ActionResult<Opinion> GetOpinion(string id)
        {
            var entidad = _servicio.GetOpinion(id);

            if (entidad == null) 
            {
                return NotFound();
            }

            return entidad;
        }

        [HttpPost]
        public ActionResult<Opinion> Create(Opinion opinion)
        {
            _servicio.Create(opinion);

            return CreatedAtRoute("GetOpinion", new { id = opinion.Id }, opinion);
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(string id)
        {
            var opinion = _servicio.GetOpinion(id);

            if (opinion == null)
            {
                return NotFound();
            }

            _servicio.RemoveOpinion(id);

            return NoContent();
        }

        [HttpPost("{id}")]
        public IActionResult AddValoracion(string id, Valoracion valoracion)
        {
            var opinion = _servicio.GetOpinion(id);

            if (opinion == null)
            {
                return NotFound();
            }

            _servicio.AddValoracion(id, valoracion);

            return NoContent();
        }
    }



}

