package testOne;

public class joseph
{
    int size,postion;
    String[] allname;
    public joseph(int num,int n,String[] name)//人数和死亡位置
    {
        size=num;
        postion=n;
        allname=new String[size];
        for (int i=0;i<size;i++)
            allname[i]=name[i];
    }

    public int start()
    {
        int[] game=new int[size];
        for (int i=0;i<size;i++)
            game[i]=i;
        System.out.println("游戏开始!");
        System.out.println("人数为"+size+"人，死亡位置为"+postion+"号");
        int prePos=0;
        int i=0;
        int arraysize=size;
        while (true)
        {
            if (postion==0)
            {
                for (int j = 0; j < size; j++)
                    System.out.println(j + "号位置的" + allname[j] + "出局");
                break;
            }
            if (game[prePos]!=-1&&(i%postion==0&&i>=postion))
            {
                game[prePos] = -1;
                size--;
                System.out.println(prePos+"号位置的"+allname[prePos]+"出局");
                if (size==1)
                {
                    while (game[prePos]==-1)
                        prePos=(++prePos)%arraysize;
                        System.out.println(prePos+"号位置的"+allname[prePos]+"出局");
                    break;
                }
            }
            else
            {
                prePos++;
            }
            i++;
            prePos=prePos%arraysize;
        }
        return 0;
    }

}
