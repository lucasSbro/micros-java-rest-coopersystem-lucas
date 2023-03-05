package org.cooper.system.b.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.quarkus.logging.Log;
import org.cooper.system.b.dto.Value;
import org.cooper.system.b.dto.ValueCotacao;
import org.cooper.system.b.dto.ValueCotacaoResponse;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@ApplicationScoped
public class CotacaoBService {

    @ConfigProperty(name = "url.api.cotacao.micro.a")
    String urlCotacaoMicroA;


    public Response getCotacaoDolarDia(String dataCotacao) throws ParseException {
        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
        ResteasyWebTarget target = client.target(urlCotacaoMicroA);
        CotacaoAInterface proxy = target.proxy(CotacaoAInterface.class);
        Response response = proxy.getCotacaoDolarDia(dataCotacao);
        client.close();

        if(response.getStatus() == 200) {
            ValueCotacaoResponse valueCotacaoResponse = new ValueCotacaoResponse();
            valueCotacaoResponse.setCotacaoDiaInformado(getValueCotacao(response));
            valueCotacaoResponse.setCotacaoDiaUtilAnterior(getCotacaoDolarDiaUtilAnterior(dataCotacao));
            return Response.ok(valueCotacaoResponse).build();
        }
        Log.info("Algo deu errado! Response Status: "+response.getStatus());
        return response;
    }

    public ValueCotacao getCotacaoDolarDiaUtilAnterior(String dataCotacao) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dataCotacao.replace("'", "").replace("-", "/")));

        Calendar ultimoDiaUtil = getUltimoDiaUtilDoMes(c);
        String dataUltimoDiaUtil = format.format(ultimoDiaUtil.getTime());

        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
        ResteasyWebTarget target = client.target(urlCotacaoMicroA);
        CotacaoAInterface proxy = target.proxy(CotacaoAInterface.class);
        Response response = proxy.getCotacaoDolarDia("'"+dataUltimoDiaUtil+"'");
        client.close();

        if(response.getStatus() == 200) {
            ValueCotacao valueCotacao = getValueCotacao(response);
            return valueCotacao;
        }
        Log.info("Algo deu errado! Response Status: "+response.getStatus());
        return null;
    }

    public ValueCotacao getValueCotacao(Response response) {
        ValueCotacao valueCotacao = new ValueCotacao();
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            valueCotacao = mapper.readValue(response.readEntity(String.class), ValueCotacao.class);
        } catch (JsonProcessingException e) {
            Log.error("Erro ao retornar valores da cotação!", e);
        }
        return valueCotacao;
    }

    public static Calendar getUltimoDiaUtilDoMes(Calendar calendar) {
        calendar.add(Calendar.DATE, -1);
        while(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, -1);
        }
        return calendar;
    }
}
