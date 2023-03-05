package org.cooper.system.b.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(as=ValueCotacao.class)
public class ValueCotacao {
    @JsonProperty("@odata.context")
    public String dataContext;

    public List<Value> value;

    public ValueCotacao() {
    }
}
