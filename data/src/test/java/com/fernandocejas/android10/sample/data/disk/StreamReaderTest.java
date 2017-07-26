package com.fernandocejas.android10.sample.data.disk;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ruby on 7/26/2017.
 */

public class StreamReaderTest {
  private final String STREAM_DATA = "content";

  private StreamReaderImpl fileManager;

  @Before public void setUp() {
    fileManager = new StreamReaderImpl();
  }

  @Test public void testReadInputStream() {
    String out = "";
    InputStream inputStream = createInputStream(STREAM_DATA);

    try {
      out = fileManager.read(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertThat(STREAM_DATA).isEqualTo(out);
  }

  private InputStream createInputStream(String data) {
    return new ByteArrayInputStream(data.getBytes());
  }
}
