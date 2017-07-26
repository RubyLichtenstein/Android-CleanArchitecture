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

public class FileManagerImplTest  {
  private FileManagerImpl fileManager;

  @Before public void setUp() {
    fileManager = new FileManagerImpl();
  }

  @Test public void testReadInputStream() {
    String streamData = "content";
    String out = "";
    InputStream inputStream = createInputStream(streamData);

    try {
      out = fileManager.readInputStream(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertThat(streamData).isEqualTo(out);
  }

  private InputStream createInputStream(String data) {
    return new ByteArrayInputStream(data.getBytes());
  }
}