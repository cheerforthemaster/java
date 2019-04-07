public class lintcode {
    public static int digitCounts(int k, int n) {
        // write your code here
        int count=0;
        int tem=0;
        for (int i=0;i<=n;i++){
            tem=i;
            while (true){
                if (tem%10==k)
                    count++;
                if(tem/10<1)
                    break;
                tem=tem/10;
            }
        }

        return count;
    }

    public static void main(String[] args){
       System.out.println(digitCounts(3,15));
    }
}
