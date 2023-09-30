package com.elms.auth.dto.career;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentSetting {
    public static final String PAYMENT_TYPE_CASH = "OFFLINE_CASH"; //tra tien truc tiep tien mat
    public static final String PAYMENT_TYPE_CARD = "OFFLINE_CARD";// tra tien truc tiep dung the
    public static final String PAYMENT_TYPE_ONLINE_PAYPAL = "ONLINE_PAYPAL";

    @JsonProperty("code")
    private String code;
}
