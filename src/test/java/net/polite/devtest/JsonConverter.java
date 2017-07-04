package net.polite.devtest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * {@link }
 *
 * @author Oleksii Polite Rudenko
 * @version 1.0
 */
public class JsonConverter {

    public static byte[] convertObjectToJsonBytes(Object object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}
