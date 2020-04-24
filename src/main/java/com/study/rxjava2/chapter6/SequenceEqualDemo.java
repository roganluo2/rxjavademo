package com.study.rxjava2.chapter6;

import io.reactivex.Observable;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class SequenceEqualDemo {

    public static void main(String[] args) {
        //testSimpleEquals();
        testPredicateEquals();

    }

    private static void testSimpleEquals() {
        Observable.sequenceEqual(
                Observable.just(1,2,3,4),
                Observable.just(1,2,3,4,5)
        ).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                System.out.println("sequenceEqual:" + aBoolean);
            }
        });
    }

    private static void testPredicateEquals() {
        Observable.sequenceEqual(
                Observable.just(1, 2, 3, 4),
                Observable.just(1, 2, 3, 4, 5),
                new BiPredicate<Integer, Integer>() {
                    @Override
                    public boolean test(Integer integer, Integer integer2) throws Exception {
                        return integer == integer2;
                    }
                }
        ).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                System.out.println("sequenceEqual:" + aBoolean);
            }
        });
    }

}
