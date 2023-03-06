package org.cooper.system.b;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CotacaoBResourceTest {

    @Test
    public void getCotacaoDolarDia() {
        String dataCotacao = "'03-03-2023'";
        given().
                pathParam("dataCotacao", dataCotacao)
                .when()
                .get("http://localhost:8082/cotacao-B/micro-servico-B/cotacao-dolar-dia/{dataCotacao}")
                .then()
                .statusCode(200);
    }
}
