package com.javacertification.ocp;

import java.util.List;

public class Generic<T> {

     /*<? extends T>*/ T first(List<? extends T> list) {
        return list.get(0);
    }

    <X> X second(List<? extends X> list) {
        return list.get(1);
    }
}
