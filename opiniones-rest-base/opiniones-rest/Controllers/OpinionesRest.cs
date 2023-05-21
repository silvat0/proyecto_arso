using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using Opiniones.Modelo;
using Opiniones.Servicio;
using Opiniones.Evento;

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
        public ActionResult<string> Create([FromBody] string recurso)
        {
            string identificador = _servicio.Create(recurso);

            return CreatedAtRoute("GetOpinion", new { id = identificador }, identificador);
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
        public IActionResult AddValoracion(string id, [FromBody] Valoracion valoracion)
        {
            var opinion1 = _servicio.GetOpinion(id);

            if (opinion1 == null)
            {
                return NotFound();
            }

            _servicio.AddValoracion(id, valoracion);

            var opinion2 = _servicio.GetOpinion(id);

            OpinionResumen opR = new OpinionResumen();
            opR.MediaValoraciones = opinion2.MediaValoraciones;
            opR.NumeroValoraciones = opinion2.NumValoraciones;

            EventoNuevaValoracion evento = new EventoNuevaValoracion();
            evento.idOpinion = id;
            evento.opinionR = opR;
            evento.valoracion = valoracion;


            _servicio.notificarValoracion(evento);

            return NoContent();
        }
    }



}

