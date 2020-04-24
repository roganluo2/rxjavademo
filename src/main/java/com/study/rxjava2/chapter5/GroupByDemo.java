package com.study.rxjava2.chapter5;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

public class GroupByDemo {

    public static void main(String[] args) {
        //testGroupByKey();
        testGroupPrint();

    }

    private static void testGroupByKey() {
        Observable.range(1, 8)
                .groupBy(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return integer % 2 == 1 ? "奇数组" : "偶数组";
                    }
                }).subscribe(new Consumer<GroupedObservable<String, Integer>>() {
            @Override
            public void accept(GroupedObservable<String, Integer> stringIntegerGroupedObservable) throws Exception {
                System.out.println("groupName :" + stringIntegerGroupedObservable.getKey());
            }
        });
    }

    private static void testGroupPrint() {
        Observable.range(1, 8)
                .groupBy(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return integer % 2 == 1 ? "奇数组" : "偶数组";
                    }
                }).subscribe(new Consumer<GroupedObservable<String, Integer>>() {
            @Override
            public void accept(GroupedObservable<String, Integer> stringIntegerGroupedObservable) throws Exception {
                if(stringIntegerGroupedObservable.getKey().equals("奇数组"))
                {
                    stringIntegerGroupedObservable.subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Exception {
                            System.out.println(stringIntegerGroupedObservable.getKey() + "member" + integer);
                        }
                    });
                }
            }
        });
    }
}
