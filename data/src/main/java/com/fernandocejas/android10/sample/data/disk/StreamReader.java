package com.fernandocejas.android10.sample.data.disk;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Ruby on 7/27/2017.
 */

public interface StreamReader {
  String read(InputStream in) throws IOException;
}
