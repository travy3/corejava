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

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}