package com.study.rxjava2.chapter7;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class StartWithDemo {

    public static void main(String[] args) {
        //testStart();
//        testStartArray();
//        testStartBoth();
        testStartWithObservable();
    }

    private static void testStart() {
        Observable.just("Hello Java", "Hello Kotlin", "Hello Scala")
                .startWith("Hello Rx")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }

    private static void testStartArray() {
        Observable.just("Hello Java", "Hello Kotlin", "Hello Scala")
                .startWithArray("Hello Groovy" , "Hello Clojure")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }

    private static void testStartBoth() {
        Observable.just("Hello Java", "Hello Kotlin", "Hello Scala")
                .startWithArray("Hello Groovy" , "Hello Clojure")
                .startWith("Hello Rx")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }

    private static void testStartWithObservable() {
        Observable.just("Hello Java", "Hello Kotlin", "Hello Scala")
                .startWithArray("Hello Groovy" , "Hello Clojure")
                .startWith(Observable.just("Hello Rx"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }

}
