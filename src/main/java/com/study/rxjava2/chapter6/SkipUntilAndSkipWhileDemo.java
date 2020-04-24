package com.study.rxjava2.chapter6;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

import java.util.concurrent.TimeUnit;

public class SkipUntilAndSkipWhileDemo
{

    public static void main(String[] args) {
        //testSkipUntil();
        Observable.just(1,2,3,4,5)
                .skipWhile(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer <= 2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(String.valueOf(integer));
            }
        });
    }

    private static void testSkipUntil() {
        Observable.intervalRange(1,9,0,1, TimeUnit.MILLISECONDS)
                .skipUntil(Observable.timer(4, TimeUnit.MILLISECONDS))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(String.valueOf(aLong));
                    }
                });

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
