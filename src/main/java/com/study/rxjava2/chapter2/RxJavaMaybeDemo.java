package com.study.rxjava2.chapter2;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class RxJavaMaybeDemo {

    public static void main(String[] args) {
        testMaybe2();
    }

    private static void testMaybe1() {
        Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> emitter) throws Exception {
                emitter.onComplete();
                emitter.onSuccess("testA");
                emitter.onSuccess("testB");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("s= " + s);
            }
        });
    }

    private static void testMaybe2() {
        Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> emitter) throws Exception {
                emitter.onComplete();
                emitter.onSuccess("testA");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("s= " + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("error");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("Maybe on Complete");
            }
        });
    }

}
