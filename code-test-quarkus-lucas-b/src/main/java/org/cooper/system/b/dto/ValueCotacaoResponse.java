package org.cooper.system.b.dto;

public class ValueCotacaoResponse {

    public ValueCotacao cotacaoDiaInformado;
    public ValueCotacao cotacaoDiaUtilAnterior;

    public ValueCotacaoResponse() {
    }

    public void setCotacaoDiaInformado(ValueCotacao cotacaoDiaInformado) {
        this.cotacaoDiaInformado = cotacaoDiaInformado;
    }

    public void setCotacaoDiaUtilAnterior(ValueCotacao cotacaoDiaUtilAnterior) {
        this.cotacaoDiaUtilAnterior = cotacaoDiaUtilAnterior;
    }
}
