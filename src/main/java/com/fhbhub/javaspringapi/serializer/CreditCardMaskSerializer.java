package com.fhbhub.javaspringapi.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CreditCardMaskSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {
        String serilizedValue = value;

        if (value != null && value.length() == 16) {
            serilizedValue = "****-****-****-" + value.substring(value.length() - 4);
        }

        jsonGenerator.writeString(serilizedValue);
    }
}
