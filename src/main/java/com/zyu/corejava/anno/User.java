package com.zyu.corejava.anno;

/**
 * Created by chenjie on 2016/2/14.
 */
 @Entity("TB_USER")
public class User{
    private int id;
    @Column(name = "user_name")
    private String name;
    @Column(name="user_pass")
    private String pass;
    @Column(name="user_age")
    private int age;
}