package org.cooper.system.resource;


import org.cooper.system.service.CotacaoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/micro-servico-A")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CotacaoResource {

    @Inject
    CotacaoService cotacaoService;

    @GET
    @Path("/cotacao-dolar-dia/{dataCotacao}")
    public Response getCotacaoDolarDia(@PathParam("dataCotacao") String dataCotacao){
        return cotacaoService.getCotacaoDolarDia(dataCotacao);
    }
}
