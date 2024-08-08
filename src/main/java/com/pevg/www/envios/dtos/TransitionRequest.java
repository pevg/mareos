package com.pevg.www.envios.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransitionRequest {
    @JsonProperty("transition")
    private String transition;
}
