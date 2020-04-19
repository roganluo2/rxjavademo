package com.study.rxjava2.chapter2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxJavaObserverDemo {

    public static void main(String[] args) {
        Observable.just("Hello world")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        System.out.println("Subscribe");
                    }

                    @Override
                    public void onNext(String s) {

                        System.out.println(s);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("on Complete");
                    }
                });
    }



}
