package com.study.rxjava2.chapter2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class RxJavaColdObservableDemo {

    public static void main(String[] args) {

        Consumer<Long> subscribe1 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                System.out.println("subcribe1:" + aLong);
            }
        };

        Consumer<Long> subscribe2 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                System.out.println("   subcribe2:" + aLong);
            }
        };

        Observable<Long> observable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> observableEmitter) throws Exception {
                Observable.interval(10, TimeUnit.DAYS.MICROSECONDS, Schedulers.computation())
                        .take(Integer.MAX_VALUE)
                        .subscribe(observableEmitter::onNext);
            }
        }).observeOn(Schedulers.newThread());

        observable.subscribe(subscribe1);
        observable.subscribe(subscribe2);
        try{
            Thread.sleep(1L);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
