import java.util.ArrayList;
import java.util.List;

//平方，立方，开方，取模，随机数，绝对值，lg，ln，log，最小，最大，！，正弦，余弦，正切，反正弦，反余弦，反正切
public class other_function {

    //平方
    public double square(double num){
        return Math.pow(num,2);
    }

    //立方
    public double cube(double num){
        return Math.pow(num,3);
    }

    //开方
    public double Square(double num){
        return Math.sqrt(num);
    }

    //取模
    public double modulus(double num1,double num2){
        return num1%num2;
    }

    //随机数
    public int random(){
        return (int)(1+Math.random()*(100-0+1));
    }

    //绝对值
    public double abs(double num){
        return Math.abs(num);
    }

    //lg
    public double lg(double num){
        return Math.log10(num);
    }

    //ln
    public double ln(double num){
        return Math.log(num);
    }

    //logx(y)
    public double logx_y(double num1,double num2){
        return Math.log(num1)/Math.log(num1);
    }

    //最大
    public double max(String num){
        List<Double> allnum=new ArrayList<>();
        String tem="";
        for (int i=0;i<num.length();i++){
            if (('0'<=num.charAt(i)&&'9'>=num.charAt(i))||num.charAt(i)=='.')
            {
                tem += num.charAt(i);
            }
            else {
                allnum.add(Double.valueOf(tem.toString()));
                tem="";
            }
        }
        if (tem!="")
            allnum.add(Double.valueOf(tem.toString()));

        double max=allnum.get(0);
        for (int i=0;i<allnum.size();i++){
            if (max>allnum.get(i)){
                max=allnum.get(i);
            }
        }
        return max;
    }

    //最小
    public double min(String num){
        List<Double> allnum = new ArrayList<>();
        String tem="";
        for (int i=0;i<num.length();i++){
            if (num.charAt(i)=='.'||('0'<=num.charAt(i)&&'9'>=num.charAt(i)))
            {
                tem += num.charAt(i);
            }
            else {
                allnum.add(Double.valueOf(tem.toString()));
                tem="";
            }
        }
        if (tem!="")
            allnum.add(Double.valueOf(tem.toString()));

        double min=allnum.get(0);
        for (int i=0;i<allnum.size();i++){
            if (min<allnum.get(i)){
                min=allnum.get(i);
            }
        }
        return min;
    }

    //! 阶乘
    public long factorial(int num){
        long s=1;
        for(int i=1;i<=num;i++)
        {
            s*=i;
        }
        return s;
    }

    //正弦
    public double sin(double num){
        return Math.sin(num);
    }

    //余弦
    public double cos(double num){
        return Math.cos(num);
    }

    //正切
    public double tan(double num){
        return Math.tan(num);
    }

    //反正弦
    public double asin(double num){
        return Math.asin(num);
    }

    //反余弦
    public double acos(double num){
        return Math.acos(num);
    }

    //反正弦
    public double atan(double num){
        return Math.atan(num);
    }
}
