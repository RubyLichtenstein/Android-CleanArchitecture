package com.fernandocejas.android10.sample.data.disk;

import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ruby on 7/26/2017.
 */

@Singleton public class AssetsReaderImpl implements AssetsReader {

  private final AssetManager assetManager;
  private final StreamReader fileManager;

  @Inject public AssetsReaderImpl(AssetManager assetManager, StreamReader fileManager) {
    this.assetManager = assetManager;
    this.fileManager = fileManager;
  }

  @Override public String readFromAssets(String fileName) throws IOException {
    return fileManager.read(getAssetInputStream(fileName));
  }

  private InputStream getAssetInputStream(String fileName) throws IOException {
    return assetManager.open(fileName);
  }
}
