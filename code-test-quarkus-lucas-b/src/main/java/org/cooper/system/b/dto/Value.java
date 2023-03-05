package org.cooper.system.b.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonDeserialize(as=Value.class)
public class Value {

    public Double cotacaoCompra;
    public Double cotacaoVenda;
    public String dataHoraCotacao;

    public Value() {
    }
}
