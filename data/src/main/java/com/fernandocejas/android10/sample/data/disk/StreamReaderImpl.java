package com.fernandocejas.android10.sample.data.disk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ruby on 7/26/2017.
 */

@Singleton public class StreamReaderImpl implements StreamReader {

  @Inject public StreamReaderImpl() {
  }

  public String read(InputStream in) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

    StringBuilder sb = new StringBuilder();
    String mLine = reader.readLine();
    while (mLine != null) {
      sb.append(mLine);
      mLine = reader.readLine();
    }
    reader.close();
    return sb.toString();
  }
}
