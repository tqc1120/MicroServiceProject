package com.example.common.util;

import com.example.common.domain.GeneralResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.Map;

public class ResponseUtil {

    private final ObjectMapper objectMapper;

    public ResponseUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public GeneralResponse deserializeResponse(String response) throws Exception {
        // Deserialize the JSON response
        Map<String, Object> responseMap = objectMapper.readValue(response, Map.class);
        Object data = responseMap.get("data");

        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setCode(0);
        generalResponse.setTimestamp(new Date());
        generalResponse.setData(data);

        return generalResponse;
    }
}
