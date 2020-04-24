package com.study.rxjava2.chapter7;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import java.util.concurrent.TimeUnit;

public class CombineLatestJoinDemo {
    public static void main(String[] args) {
        //testCombineLatest();
        testJoin2();
    }

    private static void testJoin() {
        Observable<Integer> o1 = Observable.just(1, 2, 3);
        Observable<Integer> o2 = Observable.just(4, 5, 6);
        o1.join(o2, new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just(String.valueOf(integer)).delay(200, TimeUnit.MILLISECONDS);
            }
        }, new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just(String.valueOf(integer)).delay(200, TimeUnit.MILLISECONDS);
            }
        }, new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer integer, Integer integer2) throws Exception {
                return integer + ":"+ integer2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("onNext" + s);
            }
        });

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testJoin2() {
        Observable<Integer> o1 = Observable.just(1, 2, 3).delay(200, TimeUnit.MILLISECONDS);
        Observable<Integer> o2 = Observable.just(4, 5, 6);
        o1.join(o2, new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just(String.valueOf(integer)).delay(200, TimeUnit.MILLISECONDS);
            }
        }, new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just(String.valueOf(integer)).delay(200, TimeUnit.MILLISECONDS);
            }
        }, new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer integer, Integer integer2) throws Exception {
                return integer + ":"+ integer2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("onNext" + s);
            }
        });

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testCombineLatest() {

        Observable<Integer> odds = Observable.just(1, 3, 5,7,9);
        Observable<Integer> evens = Observable.just(2, 4, 6);
        Observable.combineLatest(odds, evens, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("Next:" + integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("Error");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("Sequence complete");
            }
        });
    }

}
