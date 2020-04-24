package com.study.rxjava2.chapter6;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class DefaultIfEmptyDemo {

    public static void main(String[] args) {

        //defaultIfEmptyTest();
        switchIfEmptyTest();

    }

    private static void defaultIfEmptyTest() {
        Observable.empty()
                .defaultIfEmpty(8)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println("defaultIfEmpty" + o);
                    }
                });
    }

    private static void switchIfEmptyTest() {
        Observable.empty()
                .switchIfEmpty(Observable.just(1,2,3))
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println("switchIfEmpty" + o);
                    }
                });
    }


}
