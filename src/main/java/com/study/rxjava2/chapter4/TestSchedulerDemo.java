package com.study.rxjava2.chapter4;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.TestScheduler;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class TestSchedulerDemo {

    public static void main(String[] args) {
        //testAdvanceTimeTo();
        //testAdvanceTimeBy();
        //testTriggerActions1();
//        testTriggerActions2();
//        testTriggerActions3();
        testTriggerActions4();



    }

    private static void testTriggerActions1() {
        TestScheduler scheduler = new TestScheduler();
        scheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("immediate");
            }
        });
        scheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("20s");
            }
        }, 20 , TimeUnit.SECONDS);

        System.out.println("virtual time:" + scheduler.now(TimeUnit.SECONDS));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testTriggerActions3() {
        TestScheduler scheduler = new TestScheduler();
        scheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("immediate");
            }
        });
        scheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("20s");
            }
        }, 20 , TimeUnit.SECONDS);

        scheduler.triggerActions();
        System.out.println("virtual time:" + scheduler.now(TimeUnit.SECONDS));
        //新增advanceTimeBy
        scheduler.advanceTimeBy(20, TimeUnit.SECONDS);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testTriggerActions4() {
        TestScheduler scheduler = new TestScheduler();
        scheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("immediate");
            }
        });
        scheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("20s");
            }
        }, 20 , TimeUnit.SECONDS);

        System.out.println("virtual time:" + scheduler.now(TimeUnit.SECONDS));
        //新增advanceTimeBy
        scheduler.advanceTimeBy(20, TimeUnit.SECONDS);
        //放在最后，也不会执行
        scheduler.triggerActions();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testTriggerActions2() {
        TestScheduler scheduler = new TestScheduler();
        scheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("immediate");
            }
        });
        scheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("20s");
            }
        }, 20 , TimeUnit.SECONDS);

        scheduler.triggerActions();
        System.out.println("virtual time:" + scheduler.now(TimeUnit.SECONDS));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testAdvanceTimeBy() {
        TestScheduler scheduler = new TestScheduler();
        AtomicLong atomicLong = new AtomicLong();

        Observable.timer(2, TimeUnit.SECONDS, scheduler)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        atomicLong.incrementAndGet();
                    }
                });
        System.out.println("atomLong value" + atomicLong.get() + ",virtual time" + scheduler.now(TimeUnit.SECONDS));
        scheduler.advanceTimeBy(1, TimeUnit.SECONDS);
        System.out.println("atomLong value" + atomicLong.get() + ",virtual time" + scheduler.now(TimeUnit.SECONDS));
        scheduler.advanceTimeBy(1, TimeUnit.SECONDS);
        System.out.println("atomLong value" + atomicLong.get() + ",virtual time" + scheduler.now(TimeUnit.SECONDS));
    }

    private static void testAdvanceTimeTo() {
        TestScheduler testScheduler = new TestScheduler();
        testScheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("immediate");
            }
        });
        testScheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("20S");
            }
        },20, TimeUnit.SECONDS);

        testScheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("40S");
            }
        },40, TimeUnit.SECONDS);
        testScheduler.advanceTimeTo(1, TimeUnit.MILLISECONDS);
        System.out.println("virtual time" +testScheduler.now(TimeUnit.SECONDS));

        testScheduler.advanceTimeTo(20, TimeUnit.SECONDS);
        System.out.println("virtual time:" + testScheduler.now(TimeUnit.SECONDS));

        testScheduler.advanceTimeTo(40, TimeUnit.SECONDS);
        System.out.println("virtual time:" + testScheduler.now(TimeUnit.SECONDS));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
