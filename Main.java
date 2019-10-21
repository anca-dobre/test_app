/*
 ************************************************************************
 * ADOBE CONFIDENTIAL
 * ___________________
 *
 * Copyright 2018 Adobe Systems Incorporated
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Adobe Systems Incorporated and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Adobe Systems Incorporated and its
 * suppliers and are protected by all applicable intellectual property laws,
 * including trade secret and copyright laws.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 ***********************************************************************
 */

import com.adobe.test_app.model.Company;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

public class Main {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        objectMapper.setVisibility(FIELD, JsonAutoDetect.Visibility.ANY);

        final Company adobe = new Company("Adobe", 21_000);
        try {
            logger.info("Object before serialization: {}", adobe);
            final String adobeObjectAsJson = objectMapper.writeValueAsString(adobe);

            final Company company = objectMapper.readValue(adobeObjectAsJson, Company.class);
            logger.info("Object after serialization: {}", company);
        } catch (JsonProcessingException e) {
            logger.error("Errors while trying to serialize/deserialize Company objects");
        }
    }
}
