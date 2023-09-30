package com.elms.auth.constant;


import java.util.Objects;

public class ElmsConstant {
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";


    public static final Integer USER_KIND_ADMIN = 1;
    public static final Integer USER_KIND_SHOP = 2;
    public static final Integer USER_KIND_CUSTOMER = 3;
    public static final Integer USER_KIND_TABLET = 4;
    public static final Integer USER_KIND_MOBILE = 5;
    public static final Integer USER_KIND_WEBSITE = 6;
    public static final Integer USER_KIND_API_PARTNER = 7;
    public static final Integer USER_KIND_BOARD = 8;
    public static final Integer USER_KIND_PAYMENT = 9;

    public static final Integer STATUS_ACTIVE = 1;
    public static final Integer STATUS_PENDING = 0;
    public static final Integer STATUS_LOCK = -1;
    public static final Integer STATUS_DELETE = -2;

    public static final Integer GROUP_KIND_ADMIN = 1;
    public static final Integer GROUP_KIND_SHOP = 2;
    public static final Integer GROUP_KIND_CUSTOMER = 3;
    public static final Integer GROUP_KIND_PARTNER_API = 4;
    public static final Integer GROUP_KIND_ORDERCON = 5;
    public static final Integer GROUP_KIND_QRLIVE = 6;

    public static final Integer MAX_ATTEMPT_FORGET_PWD = 5;
    public static final int MAX_TIME_FORGET_PWD = 5 * 60 * 1000; //5 minutes
    public static final Integer MAX_ATTEMPT_LOGIN = 5;

    //payment offline group
    public static final Integer PAYMENT_METHOD_OFFLINE_CASH = 1;
    public static final Integer PAYMENT_METHOD_OFFLINE_CARD = 2;
    //payment online group
    public static final Integer PAYMENT_METHOD_ONLINE_PAYPAL = 5;
    public static boolean validatePaymentType(Integer paymentType){
        if(paymentType == null){
            return  false;
        }
        return Objects.equals(paymentType, PAYMENT_METHOD_OFFLINE_CASH)
                || Objects.equals(paymentType, PAYMENT_METHOD_ONLINE_PAYPAL)
                || Objects.equals(paymentType, PAYMENT_METHOD_OFFLINE_CARD);
    }

    public static final Integer TABLET_DEVICE_TYPE_ORDER_QRCODE_FULL = 1001;
    public static final Integer TABLET_DEVICE_TYPE_ORDER_QRCODE_RECEIVER = 1002;
    public static final Integer TABLET_DEVICE_TYPE_ORDER_QRCODE_DELIVER = 1003;
    public static final Integer TABLET_DEVICE_TYPE_ORDER_QRCODE_TABLE_QRCODE = 1004;

    private ElmsConstant(){
        throw new IllegalStateException("Utility class");
    }
}
