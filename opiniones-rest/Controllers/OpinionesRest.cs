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

        

    }





}

