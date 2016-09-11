package com.zyu.DataStruct.first;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

/**
 * Created by travy on 2016/7/14.
 */
public class ResizeingArrayStackTest {
    @Test
    public void testResizeingArrayStack(){
       ResizeingArrayStack<String> strings = new ResizeingArrayStack<String>();
        strings.push("asd");
        Iterator iterator = strings.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
//        Assert.assertEquals(strings.pop(),"asd");
        Assert.assertEquals("出问题",strings.pop(),"asd");
    }
}
