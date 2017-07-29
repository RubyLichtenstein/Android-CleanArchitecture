package com.fernandocejas.android10.sample.data.disk;

import android.content.res.AssetManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Ruby on 7/26/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class AssetsReaderTest {

  private static final String FAKE_FILE_NAME = "file_name";
  private static final String FAKE_FILE_CONTENT = "fake_file_content";

  private InputStream inputStream;

  @InjectMocks private AssetsReaderImpl assetsReader;

  @Mock private StreamReaderImpl mockFileManager;
  @Mock private AssetManager mockAssetManager;

  @Before public void setUp() throws IOException {
    inputStream = new ByteArrayInputStream(FAKE_FILE_CONTENT.getBytes());

    when(mockAssetManager.open(FAKE_FILE_NAME)).thenReturn(inputStream);
    when(mockFileManager.read(inputStream)).thenReturn(FAKE_FILE_CONTENT);
  }

  @Test public void testReadFromAssets() throws IOException {

    assetsReader.readFromAssets(FAKE_FILE_NAME);

    verify(mockFileManager).read(inputStream);
    verify(mockAssetManager).open(FAKE_FILE_NAME);
  }
}
