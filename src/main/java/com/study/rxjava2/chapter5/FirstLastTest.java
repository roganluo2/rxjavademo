package com.study.rxjava2.chapter5;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class FirstLastTest {

    public static void main(String[] args) {
        //testFirst1();
//        testFirstEmpty();
        testLast();
    }

    private static void testLast() {
        Observable.just(1,2,3)
                .last(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("on Next" + integer);
                    }
                });
    }

    private static void testFirst1() {
        Observable.just(1,2,3)
                .first(1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("on Next" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error" + throwable.getMessage());
                    }
                });
    }

    private static void testFirstEmpty() {
        Observable.<Integer>empty()
                //只是发射默认值
                .first(1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("on Next" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error" + throwable.getMessage());
                    }
                });
    }
}
