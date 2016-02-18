package com.zyu.corejava.anno;

import java.lang.reflect.Field;

/**
 * Created by chenjie on 2016/2/14.
 */
public class Session {



    public String getSql(Object obj) throws IllegalAccessException, InstantiationException {
        StringBuffer sb = new StringBuffer();
        sb.append("insert into ");
        Class c = obj.getClass();
        Object o = c.newInstance();
        String tablename = c.getSimpleName();
        System.out.println("tablename:"+tablename);
        Entity entity = (Entity) c.getAnnotation(Entity.class);
        if (entity != null)
            tablename = entity.value();
        sb.append(tablename).append(" ( ");
        Field[] fields  = c.getDeclaredFields();
        for(int i = 0 ; i<fields.length ; i++){
            String columnName = fields[i].getName();
            System.out.println("columnName :"+columnName);
            Column column = (Column) c.getAnnotation(Column.class);
            if (column != null) {
                columnName = column.name();
            }
            sb = i == 0?sb.append(columnName):sb.append(" , ").append(columnName);
        }
        sb.append(" ) values()");
        return sb.toString();
    }
}
