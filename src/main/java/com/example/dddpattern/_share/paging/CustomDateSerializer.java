package com.example.dddpattern._share.paging;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CustomDateSerializer extends StdSerializer<Instant> {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

  public CustomDateSerializer(Class<Instant> t) {
    super(t);
  }

  public CustomDateSerializer() {
    this(null);
  }

  @Override
  public void serialize(Instant value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    LocalDateTime date = value.atZone(ZoneId.systemDefault()).toLocalDateTime();
    gen.writeString(date.format(formatter));
  }
}
