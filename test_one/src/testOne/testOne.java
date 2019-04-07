package testOne;

import java.util.Scanner;

public class testOne
{
    public static void main(String[] agrs)
    {
        int num=0,n=0;
        Scanner input=new Scanner(System.in);

        System.out.println("输入人数：");
        while (true)
        {
            try
            {
                num=input.nextInt();
                if (num>1)
                    break;
            }
            catch(Exception e)
            {
                System.out.println("人数有问题(小于1),请重新输入：");
                input.next();
            }
        }

        System.out.println("输入死亡位置(从0开始计数)：");
        while (true)
        {
            try
            {
                n=input.nextInt();
                if (n>=0&&n<=num-1)
                    break;
            }
            catch (Exception e)
            {
                input.next();
            }
            System.out.println("死亡位置有问题(小于0)，请重新输入：");
        }

        String[] name=new String[num];
        input=new Scanner(System.in);
        for (int i=0;i<num;i++)
        {
            System.out.println("输入第"+(i+1)+"个人的名字：");
            name[i]=input.nextLine();
        }

        joseph jos=new joseph(num,n,name);
        jos.start();
        System.out.println("gameover!");
}

}
