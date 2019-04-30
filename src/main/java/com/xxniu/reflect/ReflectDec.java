package com.xxniu.reflect;

import java.lang.reflect.Field;

import com.xxniu.reflect.bean.CustomerInfo;

public class ReflectDec
{
    public static void main(String[] args)
    {
        try
        {
            CustomerInfo ci = new CustomerInfo();
            Class<? extends CustomerInfo> cls = ci.getClass();
            Object obj = cls.newInstance();
            
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
