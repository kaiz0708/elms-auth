package com.elms.auth.dto.career;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageOrder {

    @JsonProperty("accepted")
    public String accepted;

    @JsonProperty("processing")
    public String processing;

    @JsonProperty("pickup_done")
    public String pickupDone;
    @JsonProperty("deliver_done")
    public String deliverDone;
    @JsonProperty("qrlive_done")
    public String qrliveDone;
}
