package com.study.rxjava2.chapter3;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ObservableCreateDemo {

    public static void main(String[] args) {
        //testCreate();
        //testJust();
        //testJustIte();
        //testFromArr();
        //testFromItr();
        //testFromFuture();
//        testFromFutureTimeout();
        //testRepeat();
        //testRepeatWhen();
        //testRepeatUntil();
        //testDefer();
        //testInterval();
        testTimer();


    }

    private static void testTimer() {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(aLong + "hello timer");
                    }
                });
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testInterval() {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(aLong);
                    }
                });
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testDefer() {
        Observable<String > observable = Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends  String> call() throws Exception {
                return Observable.just("hello defer");
            }
        });
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    private static void testRepeatUntil() {
        long startTimeMills = System.currentTimeMillis();
        Observable.interval(500, TimeUnit.MILLISECONDS)
                .take(5)
                .repeatUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() throws Exception {
                        return System.currentTimeMillis() - startTimeMills > 5000;
                    }
                }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println(aLong);
            }
        });
        try {
            TimeUnit.SECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testRepeatWhen() {
        Observable.range(0, 9)
                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                        return Observable.timer(10, TimeUnit.SECONDS);
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
        try {
            TimeUnit.SECONDS.sleep(12);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testRepeat() {
        Observable.just("hello repeat")
                .repeat(3)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("s=" + s);
                    }
                });
    }

    private static void testFromFuture() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        Observable.fromFuture(future)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }
    private static void testFromFutureTimeout() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        Observable.fromFuture(future, 4 ,TimeUnit.SECONDS)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }

    static class MyCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("模拟一些耗时的任务");
            TimeUnit.SECONDS.sleep(5);
            return "OK";
        }
    }

    private static void testFromItr() {
        List<Integer> items = new ArrayList<>();
        for(int i = 0; i < 10 ; i++ )
        {
            items.add(i);
        }

        Observable.fromIterable(items)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next: " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error:" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete.");
                    }
                });
    }

    private static void testFromArr() {
        Observable.fromArray("hello", "from")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }

    private static void testJustIte() {
        Observable.just(1,2,3,4,5,6,7,8,9,10)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next:" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error:" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete");
                    }
                });
    }

    private static void testJust() {
        Observable.just("Hello just")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }

    private static void testCreate() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                try {
                    if(!emitter.isDisposed())
                    {
                        for(int i = 0; i < 10; i++)
                        {
                            emitter.onNext(i);
                        }
                    }
                }catch (Exception e)
                {
                    emitter.onError(e);
                }
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("Next: " + integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.err.println("Error: " + throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("Sequence complete.");
            }
        });
    }
}
