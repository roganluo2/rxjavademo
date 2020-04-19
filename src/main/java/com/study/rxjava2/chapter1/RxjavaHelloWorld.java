package com.study.rxjava2.chapter1;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

public class RxjavaHelloWorld {

    public static void main(String[] args) {

        Observable.just("Hello World").subscribe(System.out::println);

//        testHello1();
    }

    private static void testHello1() {
        Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                observableEmitter.onNext("Hello World");
            }
        }).subscribe(new Consumer<String>() {
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

}
