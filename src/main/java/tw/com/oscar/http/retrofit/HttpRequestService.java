/**
 * HttpRequestService.java
 * Title: Oscar Wei Java Project
 * Copyright: Copyright(c)2016, oscarwei168
 *
 * @author Oscar Wei
 * @since 2016/4/9
 * <p>
 * H i s t o r y
 * 2016/4/9 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.http.retrofit;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.*;
import rx.Observable;
import tw.com.oscar.orm.hibernate.domain.Account;
import tw.com.oscar.orm.hibernate.domain.Course;
import tw.com.oscar.orm.hibernate.domain.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: HttpRequestService.java<br>
 * </p>
 * <strong>Description:</strong> The Retrofit HTTP request definition samples<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2016<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2016/4/9
 * @since 2016/4/9
 */
public interface HttpRequestService {

    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/").build();

    // https://api.github.com/account/list
    @GET("account/list")
    Call<List<Account>> accountList();

    @GET("account/list?sort=desc")
    Observable<Account> accountListSortByDesc();

    @GET("account/{id}/list")
    Observable<Account> groupAccountList(@Path("id") String groupId);

    @GET("account/{groupId}/list")
    Observable<Account> groupAcccountListSort(@Path("groupId") String groupId, @Query("sort") String sort);

    @GET("account/{groupId}/list")
    Observable<Account> groupAcccountList(@Path("groupId") String groupId, @QueryMap Map<String, String> options);

    @POST("users/new")
    Observable<User> createUser(@Body User user);

    @FormUrlEncoded
    @POST("user/edit")
    Observable<User> updateUser(@Field("firstName") String firstName, @Field("lastName") String lastName);

    @Multipart
    @PUT("user/photo")
    Call<User> updateUser(@Part("photo") RequestBody photo, @Part("description") RequestBody description);

    @Headers("Cache-Control: max-age=640000")
    @GET("course/list")
    Call<List<Course>> courseList();

    @Headers({"Accept: application/vnd.github.v3.full+json", "User-Agent: Retrofit-Sample-App"})
    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

    @GET("user")
    Call<User> getUserByHeeader(@Header("Authorization") String authorization);

    @GET("/groupId")
    Observable<String> getGroupId();

    @POST("http://api.nuuneoi.com/special/user/list")
    Call<User> loadSpecialUsers();
}
