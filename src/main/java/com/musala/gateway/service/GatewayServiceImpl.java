package com.musala.gateway.service;

import com.musala.gateway.model.Gateway;
import com.musala.gateway.response.ValidationResult;

import java.util.regex.Pattern;

public class GatewayServiceImpl implements GatewayService {

    private static final String IPv4_REGEX = "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";

    @Override
    public ValidationResult validateGateway(Gateway gateway) {

        Pattern pattern = Pattern.compile(this.IPv4_REGEX);
        if (!pattern.matcher(gateway.getIp()).matches())
            return new ValidationResult(false, "Ip address is not valid");
        if (gateway.getPeripheralDevices() != null && gateway.getPeripheralDevices().size() > 10)
            return new ValidationResult(false, "No more than 10 peripheral devices are allowed per gateway");

        return new ValidationResult(true, "Gateway is valid");
    }
}
