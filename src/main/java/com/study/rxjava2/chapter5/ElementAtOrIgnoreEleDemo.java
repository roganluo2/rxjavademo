package com.study.rxjava2.chapter5;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class ElementAtOrIgnoreEleDemo {

    public static void main(String[] args) {

        //testElementAt();
        //testElementAtWithDefault();
        //testIgnoreElements();


    }

    private static void testIgnoreElements() {
        Observable.just(1,2,3,4)
                .ignoreElements()
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error:" + throwable.getMessage());
                    }
                });
    }

    private static void testElementAt() {
        Observable.just(1,2,3,4,5)
                .elementAt(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next:" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.err.println("Error:" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete");
                    }
                });
    }

    private static void testElementAtWithDefault() {
        Observable.just(1,2,3,4,5)
                .elementAt(7, 0)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next:" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.err.println("Error:" + throwable.getMessage());
                    }
                });
    }

}
