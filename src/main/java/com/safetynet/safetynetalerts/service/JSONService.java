package com.safetynet.safetynetalerts.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class JSONService {

    @Value("classpath:data.json")
    Resource jsonDataFile;

    public JsonData getDataFromJSONFile(){

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(jsonDataFile.getFile(), JsonData.class);
        } catch (IOException e) {
            log.info(e.getMessage());
        }

        return null;
    }

}
