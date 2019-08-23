package me.teenyda.mvp_template.common.api;

import io.reactivex.Observable;
import me.teenyda.mvp_template.common.net.resp.BaseResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * author: teenyda
 * date: 2019/8/21
 * description:
 */
public interface ApiServer {

    @FormUrlEncoded
    @POST("login")
    Observable<BaseResponse> login(@Field("username")String username,
                                   @Field("password")String password);

    @GET("book/list")
    Observable<String> bookList();

    @Multipart
    @POST
    Observable<BaseResponse> uploadImage(@Part MultipartBody.Part file,
                                         @Part("name") RequestBody requestBody);
}
