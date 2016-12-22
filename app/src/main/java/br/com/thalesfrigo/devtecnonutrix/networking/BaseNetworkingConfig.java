package br.com.thalesfrigo.devtecnonutrix.networking;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thalesfrigo on 12/20/16.
 */

public class BaseNetworkingConfig {

    private static final String BASE_URL = "http://api.tecnonutri.com.br/api/v4/";
    private static final int TIMEOUT = 60;

    public static <S> S createService(Class<S> serviceClass) {

        //logging retrofit
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        httpBuilder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        httpBuilder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        httpBuilder.writeTimeout(TIMEOUT, TimeUnit.SECONDS);
        httpBuilder.addInterceptor(interceptor);

        final OkHttpClient okHttpClient = httpBuilder.build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(serviceClass);
    }
}
