package com.prueba.api.dto;

import tools.jackson.databind.node.StringNode;

public record PhoneDto(String number,String citycode,String contrycode) {
}
