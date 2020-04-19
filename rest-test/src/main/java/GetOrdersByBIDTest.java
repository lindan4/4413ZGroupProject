import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class GetOrdersByBIDTest {
    public static void main(String[] args) throws Exception {
        OkHttpClient client = new OkHttpClient.Builder().build();

        final String bid = "b0001";
        final String severletContext = "MUGABookstore_war_exploded";

        final Response result = client.newCall(new Request.Builder()
                .url("http://localhost:8080/" + severletContext + "/orders/" + bid)
                .build())
                .execute();

        assert (result.isSuccessful());
        System.out.println(result.body().string());
    }
}
