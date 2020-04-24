package com.study.rxjava2.chapter5;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import java.util.concurrent.TimeUnit;

public class DebounceDemo {

    public static void main(String[] args) {
        //testDebounce();

    }

    private static void testDebounce() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                try {
                    if(emitter.isDisposed())
                    {
                        return;
                    }
                    for (int i = 1; i < 10; i ++)
                    {
                        emitter.onNext(i);
                        Thread.sleep( i * 100);
                    }
                    emitter.onComplete();
                } catch (InterruptedException e) {
                    emitter.onError(e);
                }

            }
            //如果发射数据间隔少于400ms,就过滤拦截
        }).debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next:" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error:");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete");
                    }
                });
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
