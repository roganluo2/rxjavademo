package com.study.rxjava2.chapter6;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

import java.util.concurrent.TimeUnit;

public class AllContainsAmbDemo {

    public static void main(String[] args) {
        //testAllCondition();
        //testContainsCondition();
        //testAmb();
        testAmbDelay();

    }

    private static void testAmb() {
        Observable.ambArray(Observable.just(1,2,3), Observable.just(4,5,6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("integer:" + integer);
                    }
                });
    }

    private static void testAmbDelay() {
        Observable.ambArray(Observable.just(1,2,3).delay(1, TimeUnit.SECONDS), Observable.just(4,5,6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("integer:" + integer);
                    }
                });
    }


    private static void testAllCondition() {
        Observable.just(1,2,3,4,5)
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer > 10;
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                System.out.println(aBoolean);
            }
        });
    }

    private static void testContainsCondition() {
        Observable.just(2,30,22,5,60,1)
                .contains(6).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                System.out.println(aBoolean);
            }
        });
    }

}
