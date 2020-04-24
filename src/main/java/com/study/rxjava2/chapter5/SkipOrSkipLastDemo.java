package com.study.rxjava2.chapter5;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import java.util.concurrent.TimeUnit;

public class SkipOrSkipLastDemo {

    public static void main(String[] args) {
        //testSkip();
        testSkipWithTime();

    }

    private static void testSkip() {
        Observable.just(1,2,3,4)
                .skip(1)
                .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("NEXT" + integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("ERROR");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("sequence complete");
            }
        });
    }

    private static void testSkipWithTime() {
        Observable.interval(1, TimeUnit.SECONDS)
                .skip(3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("next" + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("ERROR");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("sequence complete");
                    }
                });
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
