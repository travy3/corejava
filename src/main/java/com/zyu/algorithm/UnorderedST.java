package com.zyu.algorithm;

public interface UnorderedST<Key, Value> {
    int size();

    Value get(Key key);

    void put(Key key, Value value);

    void delete(Key key);
}
