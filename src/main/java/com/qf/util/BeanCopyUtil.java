package com.qf.util;

import com.qf.annoations.BeanCopyField;

import java.lang.reflect.Field;

/**
 * @program: brandshop
 * @description: 基于反射 实现对象的复制 dto-pojo的复制
 * @author: money
 * @create: 2020-07-17 20:57
 */
public class BeanCopyUtil {
    public static <T> T copyDto(Class<T> clz, Object dto, Field... fs){
        //1.获取pojo类的实例
        try {
            T obj = clz.newInstance();
            //2.遍历dto的所有字段
            for (Field f:fs){
                //fn用于接收dto属性的值
                String fn;
                //获取注解
                BeanCopyField bcf = f.getAnnotation(BeanCopyField.class);
                if (bcf != null){
                    //获取注解里的属性名
                    fn = bcf.value();
                }else {
                    //属性的名字
                    fn = f.getName();
                }

                //获取pojo并判断是否存在对应的属性
                Field fd = clz.getDeclaredField(fn);
                if (fd!=null){
                    //4.设置是否进行访问校验
                    //打破封装
                    fd.setAccessible(true);
                    f.setAccessible(true);
                    //设置dto字段的值到pojo
                    fd.set(obj,f.get(dto));
                }
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
