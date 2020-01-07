package com.pbo.monopoli.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.pbo.monopoli.models.Papan;

public class PetakConverter {
    // Serialize/deserialize helpers

    

    public static Papan fromJsonString(InputStream json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(Papan obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader reader;
    private static ObjectWriter writer;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        reader = mapper.readerFor(Papan.class);
        writer = mapper.writerFor(Papan.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}