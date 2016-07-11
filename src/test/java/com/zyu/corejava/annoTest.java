package com.zyu.corejava;


import com.zyu.corejava.anno.User;
import org.junit.Test;

/**
 * Created by chenjie on 2016/7/11.
 */

public class annoTest {

    @Test
    public void sqlTest(){

        User user = new User();

        user.setAge(27);
        user.setName("zyu");
        user.setId(1);
        user.setPass("asd");
        com.zyu.corejava.anno.Session session = new com.zyu.corejava.anno.Session();
        try {
            String sql = session.getSql(user);
            System.out.println(sql);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
