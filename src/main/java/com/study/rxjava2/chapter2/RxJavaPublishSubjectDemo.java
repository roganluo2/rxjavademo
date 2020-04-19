package com.study.rxjava2.chapter2;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;

public class RxJavaPublishSubjectDemo {

    public static void main(String[] args) {
        testSubject3();
    }

    private static void testSubject1() {
        PublishSubject<String > subject = PublishSubject.create();
        subject.onNext("publishSubject1");
        subject.onNext("publishSubject2");
        subject.onComplete();
        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("publishSubject:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("puhlishSubject onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("publishSubject:complete");
            }
        });

        subject.onNext("publishSubject3");
        subject.onNext("publishSubject4");
    }
    private static void testSubject2() {
        PublishSubject<String > subject = PublishSubject.create();
        subject.onNext("publishSubject1");
        subject.onNext("publishSubject2");
        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("publishSubject:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("puhlishSubject onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("publishSubject:complete");
            }
        });

        subject.onNext("publishSubject3");
        subject.onNext("publishSubject4");
        subject.onComplete();
    }
    private static void testSubject3() {
        PublishSubject<String > subject = PublishSubject.create();
        subject.subscribeOn(Schedulers.io()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("publishSubject:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("puhlishSubject onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("publishSubject:complete");
            }
        });

        subject.onNext("foo");
        subject.onNext("bar");
        subject.onComplete();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
