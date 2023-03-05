package org.cooper.system.service;


import io.quarkus.logging.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

@ApplicationScoped
public class CotacaoService {

    @ConfigProperty(name = "url.api.cotacao")
    String urlCotacao;
    public Response getCotacaoDolarDia(String dataCotacao) {
        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
        ResteasyWebTarget target = client.target(urlCotacao);
        CotacaoInterface proxy = target.proxy(CotacaoInterface.class);
        Response response = proxy.getCotacaoDolarDia(dataCotacao);
        client.close();
        if(response.getStatus() == 200) {
            return response;
        }
        Log.info("Algo deu errado! Response Status: "+response.getStatus());
        return response;
    }
}
