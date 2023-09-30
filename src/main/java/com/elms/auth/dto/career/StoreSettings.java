package com.elms.auth.dto.career;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreSettings {
    @JsonProperty("email_shop")
    public String emailShop;
    @JsonProperty("tablet_min_time_complete_online")
    public Integer tabletMinTimeCompleteOnline;
    @JsonProperty("tablet_time_step_online")
    public Integer tabletTimeStepOnline;
    @JsonProperty("tablet_min_time_complete_nails")
    public Integer tabletMinTimeCompleteQRCode;
    @JsonProperty("tablet_time_step_nails")
    public Integer tabletTimeStepQRCode;
    @JsonProperty("tablet_time_wait_hide_archive")
    public Integer tabletTimeWaitHideArchive;
    @JsonProperty("tablet_time_change_from_order_to_processing")
    public Integer tabletTimeChangeFromOrderToProcessing;
    @JsonProperty("tablet_time_wait_move_to_archive")
    public Integer tabletTimeWaitMoveToArchive;
    @JsonProperty("tablet_time_wait_hide_none_payment")
    public Integer tabletTimeWaitHideNonePayment;

    @JsonProperty("tablet_time_finish_one_order")
    public Integer tabletTimeFinishOneOrder;

    @JsonProperty("currentcy")
    public String currentcy;
    @JsonProperty("currentcy_position")
    public String currentcyPosition = "1";
    @JsonProperty("decimal_separator")
    public String decimalSeparator;
    @JsonProperty("group_separator")
    public String groupSeparator;
    @JsonProperty("allow_view_order")
    public Boolean allowViewOrder;
    @JsonProperty("in_vat")
    public Integer inVat;
    @JsonProperty("out_vat")
    public Integer outVat;
    @JsonProperty("date_time_format")
    public String dateFormat;
    @JsonProperty("printers")
    public List<Printer> printers;

    @JsonProperty("deliver_setting")
    public DeliverySetting deliverySetting;

    @JsonProperty("happy_hours_settings")
    public List<HappyHourTimeFrame> happyHoursSettings;
    @JsonProperty("timezone")
    public TimeZoneClaxx timezone;
    @JsonProperty("time_work")
    public TimeWork timeWork;

    @JsonProperty("tablet_random")
    public TabletRandomSetting tabletRandomSetting;
    @JsonProperty("message")
    public MessageOrder message;

    @JsonProperty("paymentQrlive")
    public List<PaymentSetting> paymentQrlive;

    @JsonProperty("paymentDeliver")
    public List<PaymentSetting> paymentDeliver;

    @JsonProperty("tableSetting")
    private TableSetting tableSetting;

    // Thong tin cua nha hang
    @JsonProperty("imprint")
    private String imprint;

    private String printHeadIn;
    private String printHeadOut;
    private String printHeadPickup;
    private String printHeadDeliver;
}