package com.musala.gateway.service;

import com.musala.gateway.model.Gateway;
import com.musala.gateway.response.ValidationResult;

public interface GatewayService {
    ValidationResult validateGateway(Gateway gateway);
}
