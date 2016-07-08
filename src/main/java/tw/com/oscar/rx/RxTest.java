/**
 * RxTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/1/15
 * <p>
 * H i s t o r y
 * 2016/1/15 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.rx;

import com.google.common.collect.Lists;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import tw.com.oscar.orm.hibernate.domain.Account;
import tw.com.oscar.orm.hibernate.domain.Story;
import tw.com.oscar.orm.hibernate.domain.StoryItem;
import tw.com.oscar.orm.hibernate.domain.enums.Gender;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Title: RxTest.java<br>
 * </p>
 * <strong>Description:</strong> //TODO <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2016<br>
 * </p>
 * <p>
 * Company: Acer Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2016/1/15
 * @since 2016/1/15
 */
public class RxTest {

    public static void main(String[] args) {
        hello("Oscar", "Amy", "Sunny");

        sample1();
        sample2();

        // Creating a predicate instance
        // Func2 interface is 2 parameters and one return value
        Func2<Account, Integer, Boolean> predicate1 = (account, integer) -> account.getGender() == Gender.MALE;
        // Invoking a predicate instance
        boolean flag = predicate1.call(new Account(), 1);

        // Creating an Observer instance
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        // A Subscriber implementing an Observer interface and add onStart()/unsubscribe() methods
        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        // Creating an Observable instance
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                // here is just a 'plan'
                // can do something else here...
                // String xx = doSomething();
                // subscriber.onNext(xx);
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                if (!subscriber.isUnsubscribed()) {
                    // subscriber.unsubscribe();
                }
                subscriber.onCompleted();
            }
        });

        // Same as previous codes
        Observable observable1 = Observable.just("Hello", "Hi", "Aloha");

        // Same as previous codes
        String[] words1 = {"Hello", "Hi", "Aloha"};
        List<String> words2 = Arrays.asList("Hello", "Hi", "Aloha");
        Observable observable2 = Observable.from(words1);
        Iterator<String> iterator = Observable.from(words2).toBlocking().getIterator();

        // Starting subscribe...in the 'same thread'
        // throttleFirst(): ignore new event in 500 milliseconds, often used in click event
        observable.throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(observer);
        observable.subscribe(subscriber);

        // Pass a class as argument to Observable instance
        // Action1 is no return value and one parameter can passed to
        Action1<String> onNextAction = new Action1<String>() {

            // like onNext() method
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };

        Action1<Throwable> onErrorAction = new Action1<Throwable>() {

            // like onError() method
            @Override
            public void call(Throwable throwable) {
                // Error handling
            }
        };

        // Action0 is no return value and no parameter passed
        Action0 onCompletedAction = () -> System.out.println("Done");

        observable.subscribe(onNextAction);
        observable.subscribe(onNextAction, onErrorAction);
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);

        LiftAllTransformer transformer = new LiftAllTransformer();
        observable.compose(transformer).subscribe(new Action1<String>() {

            @Override
            public void call(String s) {
                //
            }
        });

        // NOT a good idea to implements this lift() method
        observable1.lift(new Observable.Operator<String, Integer>() {

            @Override
            public Subscriber<? super Integer> call(Subscriber<? super String> subscriber) {
                return new Subscriber<Integer>() {

                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        subscriber.onError(throwable);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        subscriber.onNext("" + integer);
                    }
                };
            }
        });

        // making a method to an observable object
        Observable<File> fileObservable = Observable.fromCallable(() -> download(""));

        List<StoryItem> storyItems = Lists.newArrayList();
        Observable<Story> storyObservable = Observable.from(storyItems).map(item -> item.getStory())
                .distinct(story -> story.getName());

        Observable<StoryItem> itemObservable =
                Observable.merge(Observable.from(storyItems), Observable.from(storyItems));
        Observable.from(storyItems).concatWith(Observable.from(storyItems)).distinct();

        // 1 to 10
        Observable.range(1, 10).forEach(i -> System.out.println(i));

        Subscription subscription = Observable.interval(1, TimeUnit.SECONDS).subscribe(l -> System.out.println(l));
        // Observable.just(storyItems).delay(10, TimeUnit.SECONDS).subscribe(s -> s.unsubscribe());
    }

    private static void hello(String... names) {
        Observable.from(names).subscribe(s -> {
            System.out.println(String.format("Hello %s!", s));
        });
    }

    private static void sample1() {
        File[] folders = new File[] {};
        Observable.from(folders).flatMap(folder -> Observable.from(folder.listFiles()))
                .filter(new Func1<File, Boolean>() {

                    @Override
                    public Boolean call(File file) {
                        return file.getName().endsWith(".png");
                    }
                }).map(new Func1<File, BufferedImage>() {

            @Override
            public BufferedImage call(File file) {
                BufferedImage image = null;
                try {
                    image = ImageIO.read(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return image;
            }
        }).subscribeOn(Schedulers.io()) // assign subscribe() to IO thread
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        // this doOnSubscribe() will use next subscribeOn thread
                    }
                })
                .subscribeOn(Schedulers.immediate())
                .observeOn(Schedulers.immediate()) // assign Subscriber occurred in current thread
                .subscribe(new Action1<BufferedImage>() {

                    @Override
                    public void call(BufferedImage image) {
                        // do something...
                    }
                });
    }

    private static void sample2() {
        List<Account> accounts = Lists.newArrayList();
        List<String> emails = Observable.from(accounts).filter(new Func1<Account, Boolean>() {

            @Override
            public Boolean call(Account account) {
                return account.getGender() == Gender.MALE;
            }
        }).map(new Func1<Account, String>() {

            @Override
            public String call(Account account) {
                return account.getEmail();
            }
        }).take(10).toList().toBlocking().single();
        System.out.println(emails.size());
    }

    public static File download(String url) {
        return null;
    }

    static class LiftAllTransformer implements Observable.Transformer<Integer, String> {

        @Override
        public Observable<String> call(Observable<Integer> integerObservable) {
            return integerObservable
                    .map(i -> i + 10)
                    .map(i -> i + 10)
                    .map(String::valueOf);
        }
    }
}