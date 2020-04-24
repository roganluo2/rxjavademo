package com.study.rxjava2.chapter7;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class MergeZipDemo {

    public static void main(String[] args) {
        //testMerge();
       testZip();
    }

    private static void testMerge() {

        Observable<Integer> odds = Observable.just(1, 3, 5,7,9);
        Observable<Integer> evens = Observable.just(2, 4, 6);
        Observable.merge(odds, evens)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Sequence complete");
                    }
                });
    }

    private static void testZip() {

        Observable<Integer> odds = Observable.just(1, 3, 5);
        Observable<Integer> evens = Observable.just(2, 4, 6);
        Observable.zip(odds, evens, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Sequence complete");
                    }
                });
    }
}
