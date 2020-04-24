package com.study.rxjava2.chapter5;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class WindowDemo {

    public static void main(String[] args) {
        Observable.range(1, 10)
                .window(2)
                .subscribe(new Consumer<Observable<Integer>>() {
                    @Override
                    public void accept(Observable<Integer> integerObservable) throws Exception {
                        System.out.println("on Next");
                        integerObservable.subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                System.out.println("accept:" + integer);
                            }
                        });
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("On ERROR");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("on Complete");
                    }
                });
    }
}
