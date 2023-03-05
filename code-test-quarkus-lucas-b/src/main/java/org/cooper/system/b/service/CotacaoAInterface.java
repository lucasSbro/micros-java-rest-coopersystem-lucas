package org.cooper.system.b.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(value = MediaType.APPLICATION_JSON)
public interface CotacaoAInterface {

    @GET
    @Path("cotacao-dolar-dia/{dataCotacao}")
    Response getCotacaoDolarDia(@PathParam("dataCotacao") String dataCotacao);
}
