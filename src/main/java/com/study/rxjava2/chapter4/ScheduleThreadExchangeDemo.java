package com.study.rxjava2.chapter4;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class ScheduleThreadExchangeDemo {

    public static void main(String[] args) {

        //testThreadExchangeDemo();
        //singleSubscribeOn();
        multSubscribeOn();
    }

    private static void singleSubscribeOn() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                System.out.println(Thread.currentThread().getName() + " emitter");
                emitter.onNext("hello");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(Thread.currentThread().getName() + "next"+ s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println(Thread.currentThread().getName() + "test");
                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        System.out.println("disosable");
                    }
                });

        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void multSubscribeOn() {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            System.out.println(Thread.currentThread().getName() + " emitter");
            emitter.onNext("HELLO");
            emitter.onComplete();
        }).subscribeOn(Schedulers.single())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        System.out.println(Thread.currentThread().getName() + s);
                        return s.toLowerCase();
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        System.out.println(Thread.currentThread().getName() + s);
                        return s + "tony";
                    }
                })
                .subscribeOn(Schedulers.computation())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        System.out.println(Thread.currentThread().getName() + s);
                        return s + "it is a test";
                    }
                }).subscribeOn(Schedulers.newThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        System.out.println(Thread.currentThread().getName() + s);
                        return s + "end";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(Thread.currentThread().getName() + "next"+ s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println(Thread.currentThread().getName() + "test");
                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        System.out.println("disosable");
                    }
                });

        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testThreadExchangeDemo() {
        Observable.just("aaa", "bbb")
                .observeOn(Schedulers.newThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        System.out.println(Thread.currentThread().getName());
                        return s.toUpperCase();
                    }
                })
                .subscribeOn(Schedulers.single())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(Thread.currentThread().getName() + s);
                    }
                });

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
