/**
 * RetrofitSample.java
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import tw.com.oscar.orm.hibernate.domain.Account;
import tw.com.oscar.orm.hibernate.domain.User;

import java.io.IOException;

/**
 * <p>
 * Title: RetrofitSample.java<br>
 * </p>
 * <strong>Description:</strong> //TODO <br>
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
public class RetrofitSample {

    public static void main(String[] args) throws IOException {
        Retrofit retrofit = HttpRequestService.retrofit;
        Retrofit retrofit1 = new Retrofit.Builder().baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HttpRequestService service = retrofit.create(HttpRequestService.class);
        Observable<Account> accounts = service.groupAccountList("oscarwei168");
        accounts.doOnNext(new Action1<Account>() {

            @Override
            public void call(Account account) {
                processAccount(account);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread())
                .subscribe(new Action1<Account>() {

                    @Override
                    public void call(Account account) {
                        //
                    }
                });

        service.getGroupId().flatMap(new Func1<String, Observable<Account>>() {

            @Override
            public Observable<Account> call(String groupId) {

                return service.groupAccountList(groupId);
            }
        }).observeOn(Schedulers.computation())
                .subscribe(new Action1<Account>() {

                    @Override
                    public void call(Account account) {
                        //
                    }
                });

        Call<User> call = service.getUser("oscarwei");
        User user = call.execute().body();
        // asynchronously
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {

            }
        });

        if (!call.isCanceled()) {
            call.cancel();
        }
    }

    private static void processAccount(Account account) {
        //
    }
}
