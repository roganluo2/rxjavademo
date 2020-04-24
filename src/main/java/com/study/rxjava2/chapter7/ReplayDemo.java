package com.study.rxjava2.chapter7;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ReplayDemo {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Observable<Long> obs = Observable.interval(1, TimeUnit.SECONDS).take(6);

        ConnectableObservable<Long> connectableObservable = obs.replay();
        connectableObservable.connect();
        connectableObservable.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("subscriber1:onNext->" + aLong + "->time:" + sdf.format(new Date()));
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("subscribe on Error");
            }

            @Override
            public void onComplete() {
                System.out.println("subscribe on complete");
            }
        });
        //延迟3,秒订阅
        connectableObservable.delaySubscription(3, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("  subscribe2:onNext->" + aLong + "-> time:" + sdf.format(new Date()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("subscribe2:onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("subscribe2: on complete");
                    }
                });


        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
