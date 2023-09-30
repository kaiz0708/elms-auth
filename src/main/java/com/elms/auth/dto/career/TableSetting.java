package com.elms.auth.dto.career;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableSetting {
    private String question;
    private List<String> options;

    @JsonProperty("footer_message")
    private String footerMessage;

    // Khi khách đặt hàng mà quá thời gian này ko submit thì chớp trên máy bồi để bồi biết
    @JsonProperty("time_wait_for_notify_order")
    private Integer timeWaitForNotifyOrder;
}
