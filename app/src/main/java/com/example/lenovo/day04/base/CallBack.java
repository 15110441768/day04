package com.example.lenovo.day04.base;

public interface CallBack<K> {
    void onSuccess(K k);
    void onFail(String string);
}
