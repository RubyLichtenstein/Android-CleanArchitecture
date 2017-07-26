package com.fernandocejas.android10.sample.data.disk;

import java.io.IOException;

/**
 * Created by Ruby on 7/26/2017.
 */

public interface AssetsReader {
  String readFromAssets(String fileName) throws IOException;
}
