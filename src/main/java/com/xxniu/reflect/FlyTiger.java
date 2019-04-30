package com.xxniu.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.xxniu.reflect.bean.FlyQin;
import com.xxniu.reflect.bean.Tiger;

public class FlyTiger extends Tiger implements FlyQin
{

    @Override
    public void fly()
    {
        System.out.println("fly");
    }

    @Override
    public void eat()
    {
        System.out.println("eat");
    }
    
    public void jump(){
        super.jump();
        System.out.println("jump");
    }
    
    public static void main(String[] args)
    {
        try
        {
            FlyTiger ft = new FlyTiger();
            Class<? extends Tiger> cls = ft.getClass();
            Object obj = cls.newInstance();
            
            //��ȡ�������з���
            Method[] mth = cls.getDeclaredMethods();
            for (int i = 1; i < mth.length; i++)
            {
                mth[i].invoke(obj, new Object[]{});
            }
            
            //��ȡ�������б���
            Field[] fld = cls.getDeclaredFields();
            for (int i = 0; i < fld.length; i++)
            {
                System.out.println(fld[i].getName());
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }

}
