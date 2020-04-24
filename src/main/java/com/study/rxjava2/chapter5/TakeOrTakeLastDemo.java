package com.study.rxjava2.chapter5;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import java.util.concurrent.TimeUnit;

public class TakeOrTakeLastDemo {

    public static void main(String[] args) {
        //testTake();
//        testTake2();
//        testTakeWithTime();
//        testTakeLast();
        testTakeLastWithTime();


    }

    private static void testTakeLast() {
        Observable.just(1,2,3,4)
                .takeLast(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("NEXT" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("ERROR");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("sequence complete");
                    }
                });
    }

 private static void testTakeLastWithTime() {
        Observable.intervalRange(0,10,1,1,TimeUnit.SECONDS)
                .takeLast(4, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("next" + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("ERROR");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("sequence complete");
                    }
                });
     try {
         TimeUnit.SECONDS.sleep(10);
     } catch (InterruptedException e) {
         e.printStackTrace();
     }
 }

    private static void testTake() {
        Observable.just(1,2,3,4)
                .take(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("NEXT" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("ERROR");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("sequence complete");
                    }
                });
    }

    private static void testTake2() {
        //发射少于take
        Observable.just(1,2,3,4)
                .take(5)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("NEXT" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("ERROR");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("sequence complete");
                    }
                });
    }

    private static void testTakeWithTime() {

        Observable.intervalRange(0,10,1,1, TimeUnit.SECONDS)
                .take(3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("next" + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("ERROR");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("sequence complete");
                    }
                });
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
