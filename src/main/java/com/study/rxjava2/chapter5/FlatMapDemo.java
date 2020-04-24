package com.study.rxjava2.chapter5;

import com.study.rxjava2.chapter5.entity.User;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import java.util.ArrayList;
import java.util.List;

public class FlatMapDemo {

    public static void main(String[] args) {

        //testMapOperation();
        testFlatMapOperation();

    }

    private static void testMapOperation() {
        User user = new User();
        user.setUserName("tony");
        User.Address address = new User.Address();
        address.setStreet("ren ming road");
        user.setAddresses(new ArrayList<>());
        user.getAddresses().add(address);

        User.Address address2 = new User.Address();
        address2.setStreet("dong wu bei road");
        address2.setCity("SU ZHOU");
        user.getAddresses().add(address2);

        Observable.just(user)
                .map(new Function<User, List<User.Address>>() {
                    @Override
                    public List<User.Address> apply(User user) throws Exception {
                        return user.getAddresses();
                    }
                })
                .subscribe(new Consumer<List<User.Address>>() {
                    @Override
                    public void accept(List<User.Address> addresses) throws Exception {
                        for (User.Address address: addresses) {
                            System.out.println(address.getStreet());

                        }
                    }
                });
    }

    private static void testFlatMapOperation() {
        User user = new User();
        user.setUserName("tony");
        User.Address address = new User.Address();
        address.setStreet("ren ming road");
        user.setAddresses(new ArrayList<>());
        user.getAddresses().add(address);

        User.Address address2 = new User.Address();
        address2.setStreet("dong wu bei road");
        address2.setCity("SU ZHOU");
        user.getAddresses().add(address2);

        Observable.just(user)
                .flatMap(new Function<User, ObservableSource<User.Address>>() {
                    @Override
                    public ObservableSource<User.Address> apply(User user) throws Exception {
                        return Observable.fromIterable(user.getAddresses());
                    }
                })
                .subscribe(new Consumer<User.Address>() {
                    @Override
                    public void accept(User.Address address) throws Exception {
                        System.out.println(address.getStreet());
                    }
                });
    }
}
