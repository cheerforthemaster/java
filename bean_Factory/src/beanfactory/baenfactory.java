package beanfactory;
import test.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


class Factory
{
    Class<?> getTheClass(String str) throws ClassNotFoundException
    {
        return Class.forName("test."+str);
    }
}

public class baenfactory
{
    public static void main(String[] agrs) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        /*Class<?> c = Class.forName("java.lang.Class");
        System.out.println(c);*/

        Factory fa=new Factory();
        Class<?> c=fa.getTheClass("student");
        Constructor<?> con=c.getConstructor(String.class,int.class);
        student stu = (student)con.newInstance("张三",3);
        System.out.println(stu);

        Method set=c.getMethod("setName", String.class);
        set.invoke(stu,"王五");
        Method get=c.getMethod("getName");
        Object o =  get.invoke(stu);
        System.out.println(o);

        Method[] way = c.getMethods();
        for (Method name:way)
        {
            System.out.println(name);
        }


    }
}
