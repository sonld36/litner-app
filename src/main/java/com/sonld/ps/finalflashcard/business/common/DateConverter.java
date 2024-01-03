package com.sonld.ps.finalflashcard.business.common;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.Instant;

public class DateConverter extends TypeAdapter<Instant> {

    @Override
    public void write(JsonWriter out, Instant value) throws IOException {
        out.value(value.toString());
    }

    @Override
    public Instant read(JsonReader in) throws IOException {
        return Instant.parse(in.nextString());
    }
}

