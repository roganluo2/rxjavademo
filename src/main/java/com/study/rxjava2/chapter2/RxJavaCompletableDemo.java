package com.study.rxjava2.chapter2;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import java.util.concurrent.TimeUnit;

public class RxJavaCompletableDemo {

    public static void main(String[] args) {
        //testComplete1();
        testComplete2();
    }

    private static void testComplete2() {
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    emitter.onComplete();
                } catch (InterruptedException e) {
                    emitter.onError(e);
                }
            }
        }).andThen(Observable.range(1, 10))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                });
    }

    private static void testComplete1() {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("Hello World");
            }
        });
    }
}
