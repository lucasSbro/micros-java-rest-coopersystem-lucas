package org.cooper.system.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(value = MediaType.APPLICATION_JSON)
public interface CotacaoInterface {

    @GET
    @Path("v1/odata/CotacaoDolarDia(dataCotacao=@dataCotacao)")
    Response getCotacaoDolarDia(@QueryParam("@dataCotacao") String dataCotacao);
    @GET
    @Path("v1/odata/CotacaoDolarDia/{dataInicial}/{dataFinalCotacao}")
    Response getCotacaoDolarPeriodo(@PathParam("dataInicial") String dataInicial, @PathParam("dataFinalCotacao") String dataFinalCotacao);
}
