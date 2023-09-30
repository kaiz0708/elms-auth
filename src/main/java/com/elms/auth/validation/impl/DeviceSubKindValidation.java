package com.elms.auth.validation.impl;



import com.elms.auth.validation.DeviceSubKind;
import com.elms.auth.constant.ElmsConstant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class DeviceSubKindValidation implements ConstraintValidator<DeviceSubKind, Integer> {
    private boolean allowNull;

    @Override
    public void initialize(DeviceSubKind constraintAnnotation) {
        allowNull = constraintAnnotation.allowNull();
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if(allowNull && integer == null){
            return true;
        }else{
            if (!Objects.equals(integer, ElmsConstant.TABLET_DEVICE_TYPE_ORDER_QRCODE_DELIVER)
                    && !Objects.equals(integer, ElmsConstant.TABLET_DEVICE_TYPE_ORDER_QRCODE_FULL)
                    && !Objects.equals(integer, ElmsConstant.TABLET_DEVICE_TYPE_ORDER_QRCODE_RECEIVER)
                    && !Objects.equals(integer, ElmsConstant.TABLET_DEVICE_TYPE_ORDER_QRCODE_TABLE_QRCODE) ){
                return false;
            }
        }
        return true;
    }
}
