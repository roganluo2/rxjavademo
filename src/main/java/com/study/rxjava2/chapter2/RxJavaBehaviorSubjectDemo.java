package com.study.rxjava2.chapter2;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;

public class RxJavaBehaviorSubjectDemo {

    public static void main(String[] args) {
        testBehaviorSubject2();
        //testBehaviorSubject1();


    }

    private static void testBehaviorSubject1() {
        BehaviorSubject<String> subject = BehaviorSubject.createDefault("behaviorSubject1");
        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("behaviorSubject:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("behaviorSubject onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("behaviorSubject:complete");
            }
        });
        subject.onNext("behaviorSubject2");
        subject.onNext("behaviorSubject3");
    }
    private static void testBehaviorSubject2() {
        BehaviorSubject<String> subject = BehaviorSubject.createDefault("behaviorSubject1");
        subject.onNext("behaviorSubject2");
        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("behaviorSubject:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("behaviorSubject onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("behaviorSubject:complete");
            }
        });
        subject.onNext("behaviorSubject3");
        subject.onNext("behaviorSubject4");
    }
}
