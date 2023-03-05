package org.cooper.system.b.resource;

import org.cooper.system.b.service.CotacaoBService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;

@Path("/micro-servico-B")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CotacaoBResource {

    @Inject
    CotacaoBService cotacaoBService;
    @GET
    @Path("/cotacao-dolar-dia/{dataCotacao}")
    public Response getCotacaoDolarDia(@PathParam("dataCotacao") String dataCotacao) throws ParseException {
        return cotacaoBService.getCotacaoDolarDia(dataCotacao);
    }
}
