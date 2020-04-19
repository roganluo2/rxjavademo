package com.study.rxjava2.chapter2;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.AsyncSubject;

public class RxJavaAsyncSubjectDemo {

    public static void main(String[] args) {
        testSubject2();
    }

    private static void testSubject1() {
        AsyncSubject<String> objectAsyncSubject = AsyncSubject.create();
        objectAsyncSubject.onNext("asyncSubject1");
        objectAsyncSubject.onNext("asyncSubject2");
        objectAsyncSubject.onComplete();
        objectAsyncSubject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("asyncSubject:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("asyncSubject onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("asyncSubject:complete");
            }
        });
        objectAsyncSubject.onNext("asyncSubject3");
        objectAsyncSubject.onNext("asyncSubject4");
    }
    private static void testSubject2() {
        AsyncSubject<String> objectAsyncSubject = AsyncSubject.create();
        objectAsyncSubject.onNext("asyncSubject1");
        objectAsyncSubject.onNext("asyncSubject2");
        objectAsyncSubject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("asyncSubject:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("asyncSubject onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("asyncSubject:complete");
            }
        });
        objectAsyncSubject.onNext("asyncSubject3");
        objectAsyncSubject.onNext("asyncSubject4");
        //放在最后
        objectAsyncSubject.onComplete();
    }
}
