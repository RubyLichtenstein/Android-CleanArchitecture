package com.fernandocejas.android10.sample.data.disk;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ruby on 7/26/2017
 */

public class StreamReaderTest {
  private final String STREAM_DATA_1 = "content";
  private final String STREAM_DATA_2 = "";
  private final String STREAM_DATA_3 = "Ah1UMvJrhEl44PZpuWiDvrL3DU9ZN8iaP80VZ35moKN6qoF9bM54ssWmVKkZTCKc56eBuExnxN43pS68zjphwN9jH0fVW8AMOrWS06LWVuqBbYRPnlLOBt132sXSZwVvIUVhmc8BYrYMVCD"
      + "DEqsg7qvSh2vZtt92sqSt3DeeBFCwAeP3QenwJF3sJicgfknOu6BheQG5v0iWhZWB3Z2GLaA27ayj6T7eMQaGqjqvbIcfoaETmCz7huRVcXKlWP3HxJk40h6g8UogCT6rq4bC7UAmbyD4ND"
      + "Eqi4f4KTv1j0hnro4PeKljw8zQWc5BbcKgJRolVozbDX5Bngo2Xa8zFvThtJaqK1GDT7sNOqVMIrqRpszE30zx6fibGJY3Kmi0CbPnG7KhjiRrJiHkZBP6qS5q6DhIV23qz7wzr5SQ5JRK7"
      + "D84v2f4QvzGGNpx9tYaVe11a0yT5tQ6l1oqKn9GcVZSVxFVaos4DuE6ga4O5X1tYPYZPFUsOYF5u1363w5PIB8zh6bCqst3aKekKvyCrO0PovkUxWO9LgECcBuMTRep2ZR2mx6gX9jyvRD1"
      + "ZcyDTxZj12uaW7wj2Ykxnanq2ayFZDuPlo0tJQZEcslV7C9cXs6C76ETRRl4TmrywfCMuFn3R4uJZWeKeX558cpvRi83NPrJhCA9ZwCA0cHxrUXlCs7J5MKRkBwo8KXFPMQQ3OuhtoUgja8"
      + "tUUTx6Mzni4RLTALfVRKO5wrfPl7RE6l2pxGDqisvUsJUP2KRtuL4SwyJKESguWwR0NMilk5vLwQL5He3jis8sSSJEtWHTZioIlt4kvfhGTvtuCF9YOnU6LW8jPsIl6Cx93e4yzOXkcoT9k"
      + "pyKHoUqA6patAvBQ81ZeIVG0AWq3GhIvmpIueqfIHIItjcWXNxBpJhFrIoaEKnRUj5R8CTqA3BW2VxTCMEvWloqqcef1AiQizMBlovCMQXO182QGRUmYObv0skfZFx2NHyKkg3KTqYtBiHj"
      + "PUoHkPlkRlYrXs0VIPSiHpvtnKm3CXsfTQ1wunQhbO21BjfgjlEmJTStH0ACGWyQSfXo6ozpWT82xKNbfaDkYOmI1eRy1uoFIlhvk2jxWZDUygL1LMKk0GaO2Zuw5cn74wxz2zTfRDezojn"
      + "19lEgD7CEivmB5Mer12HQ6B3n8cnKW8lw2vAelmsixq6F0uspNZWJkg41eUqVZRM1W7ypu4XFXhinnvFU0Fk8D518XOOBDwRkwb3glhTmhRmfupz8xPwBwBXPfhPYAYLqypFS5Hfl9j1vMY"
      + "3scwWwgtvMcNgG4y1SvqH92gf5K1WWK3sIpPsg8X9aSwuDlxjn33j4xNNIvoflMEikBQqfLlsXXmzuJEDzcDODwzQLVPNu4UQ0qslckFeAORjWvQszGCRmCpP4tTX30keEr2gO0bm7VhVtH";

  private StreamReaderImpl streamReader;

  @Before public void setUp() {
    streamReader = new StreamReaderImpl();
  }

  @Test public void testReadInputStream() throws IOException {
    assertReadInputStream(STREAM_DATA_1);
    assertReadInputStream(STREAM_DATA_2);
    assertReadInputStream(STREAM_DATA_3);
  }

  public void assertReadInputStream(String in) throws IOException {
    String out = "";
    InputStream inputStream = createInputStream(in);
    out = streamReader.read(inputStream);
    assertThat(in).isEqualTo(out);
  }

  private InputStream createInputStream(String data) {
    return new ByteArrayInputStream(data.getBytes());
  }
}
