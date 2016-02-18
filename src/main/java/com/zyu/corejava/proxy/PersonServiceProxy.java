package com.zyu.corejava.proxy;

/**
 * Created by chenjie on 2016/2/14.
 */
public class PersonServiceProxy implements PersonService {

    private PersonServiceImpl personService;

    public void setPersonService(PersonServiceImpl personService){
        this.personService = personService;
    }

    public void delete() {
        TransactionManager.begin();
        personService.delete();
        TransactionManager.end();
    }

    public void update() {
        TransactionManager.begin();
        personService.update();
        TransactionManager.end();
    }
}
