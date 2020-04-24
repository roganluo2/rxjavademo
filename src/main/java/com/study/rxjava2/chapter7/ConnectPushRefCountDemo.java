package com.study.rxjava2.chapter7;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ConnectPushRefCountDemo {
    public static void main(String[] args) {
        //testSimpleConnect();
        testRefCount();


    }

    private static void testSimpleConnect() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Observable<Long> obs = Observable.interval(1, TimeUnit.SECONDS).take(6);

        ConnectableObservable<Long> connectableObservable = obs.publish();
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

        connectableObservable.connect();

        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testRefCount() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Observable<Long> obs = Observable.interval(1, TimeUnit.SECONDS).take(6);

        ConnectableObservable<Long> connectableObservable = obs.publish();
        Observable<Long> obsRefCount = connectableObservable.refCount();

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

        obsRefCount.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("obsRefCount:onNext->" + aLong + "-> time:" + sdf.format(new Date()));
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("obsRefCount:onError");
            }

            @Override
            public void onComplete() {
                System.out.println("obsRefCount: on complete");
            }
        });

        obsRefCount.delaySubscription(3, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("obsRefCount2:onNext:" + aLong + "->time" + sdf.format(new Date()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("obsRefCount2:onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("obsRefCount2:onComplete");
                    }
                });

        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
