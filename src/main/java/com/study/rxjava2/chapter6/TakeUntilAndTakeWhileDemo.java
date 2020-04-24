package com.study.rxjava2.chapter6;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class TakeUntilAndTakeWhileDemo {

    public static void main(String[] args) {
        //takeUntilDemo();
        takeWhileDemo();

    }

    private static void takeUntilDemo() {
        Observable.just(1,2,3,4,5,6,7,8)
                .takeUntil(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer == 5;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(String.valueOf(integer));
                    }
                });
    }

    private static void takeWhileDemo() {
        Observable.just(1,2,3,4,5,6,7,8)
                .takeWhile(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer <= 5;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(String.valueOf(integer));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("ERROR");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("onComplete");
                    }
                });
    }
}
