package com.study.rxjava2.chapter2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class RxJavaHotRfCountDemo {

    public static void main(String[] args) {

//        allCancelSubscribeTest();

        partCancelSubscribeTest();


    }

    private static void allCancelSubscribeTest() {
        Consumer<Long> subscribe1 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                System.out.println("subcribe1:" + aLong);
            }
        };

        Consumer<Long> subscribe2 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                System.out.println("  subcribe2:" + aLong);
            }
        };


        ConnectableObservable<Long> connectableObservable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> observableEmitter) throws Exception {
                Observable.interval(10, TimeUnit.DAYS.MICROSECONDS, Schedulers.computation())
                        .take(Integer.MAX_VALUE)
                        .subscribe(observableEmitter::onNext);
            }
        }).observeOn(Schedulers.newThread()).publish();

        connectableObservable.connect();

        Observable<Long> observable = connectableObservable.refCount();

        Disposable disposable1 = observable.subscribe(subscribe1);
        Disposable disposable2 = observable.subscribe(subscribe2);
        try{
            Thread.sleep(1L);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        disposable1.dispose();
        disposable2.dispose();
        System.out.println("subscribe1 subscribe2 重新订阅");
        disposable1 = observable.subscribe(subscribe1);
        disposable2 = observable.subscribe(subscribe2);
        try{
            Thread.sleep(1L);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private static void partCancelSubscribeTest() {
        Consumer<Long> subscribe1 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                System.out.println("subcribe1:" + aLong);
            }
        };

        Consumer<Long> subscribe2 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                System.out.println("  subcribe2:" + aLong);
            }
        };
        Consumer<Long> subscribe3 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                System.out.println("    subcribe3:" + aLong);
            }
        };


        ConnectableObservable<Long> connectableObservable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> observableEmitter) throws Exception {
                Observable.interval(10, TimeUnit.DAYS.MICROSECONDS, Schedulers.computation())
                        .take(Integer.MAX_VALUE)
                        .subscribe(observableEmitter::onNext);
            }
        }).observeOn(Schedulers.newThread()).publish();

        connectableObservable.connect();

        Observable<Long> observable = connectableObservable.refCount();

        Disposable disposable1 = observable.subscribe(subscribe1);
        Disposable disposable2 = observable.subscribe(subscribe2);
        observable.subscribe(subscribe3);
        try{
            Thread.sleep(1L);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        disposable1.dispose();
        disposable2.dispose();
        System.out.println("重新开始数据流");
        disposable1 = observable.subscribe(subscribe1);
        disposable2 = observable.subscribe(subscribe2);
        try{
            Thread.sleep(1L);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
