package com.fernandocejas.android10.sample.data.disk;

import com.fernandocejas.android10.sample.data.ApplicationTestCase;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ruby on 7/26/2017.
 */

public class AssetsReaderTest extends ApplicationTestCase {

  public static final String CITIES_FILE_NAME = "cities.txt";

  private AssetsReader assetsReader;

  @Before public void setUp() {
    assetsReader = new AssetsReaderImpl(context, fileManager);
  }

  @Test public void testReadCitesFromFileWithExcisingFile() {
    String fileContent = "";
    try {
      fileContent = assetsReader.readFromAssets(context(), CITIES_FILE_NAME);
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertThat(fileContent).isNotEmpty();
  }
}
