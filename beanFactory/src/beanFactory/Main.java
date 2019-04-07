package beanFactory;

import cn.edu.cug.Student;
import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] agrs) throws IOException, ClassNotFoundException {
        beanFactory bf=new beanFactory("a.txt");
        String input;
        //Scanner sb = new Scanner(System.in);
        //input=sb.next();
        input="object1";
        String ways= bf.getways(input);
        System.out.println(Class.forName(ways));
    }
}
