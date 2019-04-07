import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class binary_conversion
{
    private static char[] array = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
            .toCharArray();
    private static String numStr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static List<String> nums=new ArrayList<>();

    //10进制转为其他进制，除留取余，逆序排列
    public static String dec_to_N(long number, int N) {
        Long rest = number;
        Stack<Character> stack = new Stack<Character>();
        StringBuilder result = new StringBuilder(0);
        while (rest != 0) {
            stack.add(array[new Long((rest % N)).intValue()]);
            rest = rest / N;
        }
        for (; !stack.isEmpty();) {
            result.append(stack.pop());
        }
        return result.length() == 0 ? "0":result.toString();

    }

    // 其他进制转为10进制，按权展开
    public static long N_to_dec(String number, int N) {
        char ch[] = number.toCharArray();
        int len = ch.length;
        long result = 0;
        if (N == 10) {
            return Long.parseLong(number);
        }
        long base = 1;
        for (int i = len - 1; i >= 0; i--) {
            int index = numStr.indexOf(ch[i]);
            result += index * base;
            base *= N;
        }
        return result;
    }

    public static String other_to_dec(String exec, int N) {//表达式，进制数
        splitExec(exec);
        long tem;
        for (int i=0;i<nums.size();i++){
            tem=N_to_dec(nums.get(i),N);
            nums.set(i,Long.toString(tem));
        }
        return mergeExec(exec);
    }


    private static String mergeExec(String exec){
        String tem="";
        boolean is=false;
        int j=0;
        for (int i=0;i<exec.length();i++){
            if (('0'<=exec.charAt(i)&&'9'>=exec.charAt(i))
                    ||(exec.charAt(i)>='a'&&exec.charAt(i)<='z')
                    ||(exec.charAt(i)>='A'&&exec.charAt(i)<='Z'))
            {
                if (is){
                    continue;
                }
                else
                {
                    tem+=nums.get(j);
                    j++;
                    is=true;
                }
            }
            else
            {
                is=false;
                tem+=exec.charAt(i);
            }
        }
        while (j<nums.size()){
            tem+=nums.get(j);
            j++;
        }

        return tem;
    }

    private static void splitExec(String exec) {
        String tem="";
        for (int i=0;i<exec.length();i++){
            if (('0'<=exec.charAt(i)&&'9'>=exec.charAt(i))
                    ||(exec.charAt(i)>='a'&&exec.charAt(i)<='f')
                    ||(exec.charAt(i)>='A'&&exec.charAt(i)<='F'))
            {
                tem += exec.charAt(i);
            }
            else {
                nums.add(tem);
                tem="";
            }
        }
        if (tem!="")
            nums.add(tem);
    }

    /*
    public static void main(String[] args) {
        System.out.println(other_to_dec("10+111",2));
    }
    */
}

