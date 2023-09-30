package com.elms.auth.dto.career;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeliverDistanceSetting {
    private Integer km;
    @JsonProperty("min_money")
    private Double minMoney;
    @JsonProperty("ship_money")
    private Double shipMoney;
}
