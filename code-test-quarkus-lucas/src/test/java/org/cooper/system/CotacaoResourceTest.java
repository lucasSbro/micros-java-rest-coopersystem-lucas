package org.cooper.system;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CotacaoResourceTest {

    @Test
    public void getCotacaoDolarDia() {
        String dataCotacao = "'03-03-2023'";
        given().
                pathParam("dataCotacao", dataCotacao)
                .when()
                .get("/cotacao/micro-servico-A/cotacao-dolar-dia/{dataCotacao}")
                .then()
                .statusCode(200);
    }
}
