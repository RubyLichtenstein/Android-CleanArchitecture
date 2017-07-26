package com.fernandocejas.android10.sample.data.disk;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Ruby on 7/26/2017.
 */

public class AssetsReaderImpl implements AssetsReader {

  private final Context context;
  private final FileManagerImpl fileManager;

  public AssetsReaderImpl(Context context, FileManagerImpl fileManager) {
    this.context = context;
    this.fileManager = fileManager;
  }

  @Override public String readFromAssets(String fileName) throws IOException {
    return fileManager.read(getAssetInputStream(fileName));
  }

  private InputStream getAssetInputStream(String fileName) throws IOException {
    return context.getAssets().open(fileName);
  }
}
