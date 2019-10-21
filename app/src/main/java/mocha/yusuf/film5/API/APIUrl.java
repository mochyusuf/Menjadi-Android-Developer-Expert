package mocha.yusuf.film5.API;

import mocha.yusuf.film5.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIUrl {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
