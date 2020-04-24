package com.study.rxjava2.chapter4;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ScheduleDemo {

    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                System.out.println(Thread.currentThread().getName());
                observableEmitter.onNext( "hello");
                observableEmitter.onNext("world");
            }
        }).observeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(Thread.currentThread().getName() + s);
                    }
                });
    }

}
