package beanFactory;

import java.io.File;
import java.util.*;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class beanFactory {
    private Map map;
    public beanFactory(String filename) throws IOException {

        File thefile=new File(filename);
        int size=(int)thefile.length();
        byte[] temContent=new byte[size];
        FileInputStream input=new FileInputStream(thefile);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        String read_line;//每行数据
        String f_temStr="";//第一个数据，名称
        String s_temStr="";//第二个数据，路径
        boolean isFirst=true;
        this.map=new HashMap();
        while ((read_line=bufferedReader.readLine())!=null)
        {
            f_temStr="";
            s_temStr="";
            for (int i=0;i<read_line.length();i++)
            {
                if (read_line.charAt(i)=='/'&&read_line.charAt(i+1)=='/')
                    break;
                if (read_line.charAt(i)!=' ')
                {
                    if (isFirst)
                    {
                        f_temStr+=read_line.charAt(i);
                    }
                    else
                    {
                        s_temStr+=read_line.charAt(i);
                    }
                }
                else
                {
                    isFirst=false;
                }
            }
            this.map.put(f_temStr,s_temStr);
        }
    }
    public String getways(String str){
        return String.valueOf(this.map.get(str));
    }
}
