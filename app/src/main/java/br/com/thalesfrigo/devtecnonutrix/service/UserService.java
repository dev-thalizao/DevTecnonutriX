package br.com.thalesfrigo.devtecnonutrix.service;

import android.support.annotation.Nullable;

import java.util.Map;

import br.com.thalesfrigo.devtecnonutrix.networking.UserDetailResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by thalesfrigo on 12/23/16.
 */

public interface UserService {

    @GET("profile/{id}")
    Call<UserDetailResponse> getFeed(@Path("id") String id, @Query("p") int pageNumber, @Query("t") String timestamp);
}
