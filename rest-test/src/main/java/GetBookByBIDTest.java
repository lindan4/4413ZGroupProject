import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class GetBookByBIDTest {
    public static void main(String[] args) throws Exception {
        OkHttpClient client = new OkHttpClient.Builder().build();

        final String bid = "b0001";
        final String url = "https://mugabookstore.mybluemix.net/rest/products?bid=" + bid;

        final Response result = client.newCall(new Request.Builder()
                .url(url)
                .build())
                .execute();

        assert (result.isSuccessful());
        System.out.println(result.body().string());
    }
}
