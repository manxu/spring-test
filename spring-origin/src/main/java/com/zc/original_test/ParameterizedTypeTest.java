package com.zc.original_test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import com.zc.model.Yellow;

public class ParameterizedTypeTest {

    public static void main(String[] args) {
        //getFields 获取类及其父类的public属性
        //getDeclaredFields 获取类的所有属性，不包含父类的
        final Field[] fields = ParamterizedBean.class.getDeclaredFields();

        for (Field f : fields){
            //System.out.println("field"+ f.getName());
            //getType 返回 Class , getGenricType 返回Type。 主要区别getGenericType能返回泛型。
            //比如List<String>： getType 返回List , getGenericType 返回List<String>
            //System.out.println(f.getType());
            //System.out.println(f.getGenericType());
            if(f.getGenericType() instanceof ParameterizedType){
                final ParameterizedType f1 = (ParameterizedType) f.getGenericType();
                //返回泛型类型： 如List<String> 返回 String
                System.out.println(f1.getActualTypeArguments()[0].getTypeName());
                //ownerType： O.A<T> 返回O
                //System.out.println(f1.getOwnerType().getTypeName());
                System.out.println(f1.getRawType().getTypeName());
                System.out.println(f1.getTypeName());
            }
        }

    }

    class ParamterizedBean extends Yellow {
        String s0;
        List<String> s1;
        List s2;
        Map<String,Object> s3;
        Map s4;

        public void m1(){

        }
    }


}
