package com.study.rxjava2.chapter5;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import java.util.List;

/**
 * 定期收集Observable数据并放进一个包裹，然后发射这些包裹
 */
public class BufferDemo {

    public static void main(String[] args) {
//        testBuffer1();
//        testBuffer3();
        testBufferSkip();



    }

    private static void testBuffer1() {
        Observable.range(1, 10)
                .buffer(2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        System.out.println("on Next:" + integers);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("onError");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("onComplete");
                    }
                });
    }

    private static void testBuffer2() {
        //奇数个
        Observable.range(1, 11)
                .buffer(2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        System.out.println("on Next:" + integers);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("onError");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("onComplete");
                    }
                });
    }

    private static void testBuffer3() {
        //奇数个
        Observable.range(1, 11)
                //5个一组
                .buffer(5)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        System.out.println("on Next:" + integers);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("onError");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("onComplete");
                    }
                });
    }

    private static void testBufferSkip() {
        Observable.range(1, 11)
                .buffer(5, 1)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        System.out.println("on Next:" + integers);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("onError");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("onComplete");
                    }
                });
    }
}
