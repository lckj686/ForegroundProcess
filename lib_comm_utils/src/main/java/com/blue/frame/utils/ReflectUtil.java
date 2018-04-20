package com.blue.frame.utils;

import java.lang.reflect.Field;

/**
 * Description：反射工具类
 * Created by liw on 2015/12/11.
 */
public class ReflectUtil {
    /**
     * 获得对象的属性值，不管是public或者private的都可以，
     * 但是该方法只能获得在该对象的类中所定义的成员变量的值，不能获得其父类的成员变量的值。
     * @param owner     对象
     * @param fieldName 对象所属类的属性名
     * @return 对象所属类中的属性值
     */
    public static Object getField(Object owner, String fieldName) {
        Object fieldValue = null;
        try {
            Class classObj = owner.getClass();
            Field field = classObj.getDeclaredField(fieldName);
            field.setAccessible(true);
            fieldValue = field.get(owner);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fieldValue;
    }
}
