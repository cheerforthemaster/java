public class NFAStack {
    public NFA currentNFA;//当前位置的NFA
    public NFAStack nextNFA;//下一个入栈的NFA
    public NFAStack(NFA currentNFA){
        this.currentNFA = currentNFA;
        this.nextNFA = null;
    }
    //定义pop方法返回栈顶元素
    public void pop(){
        NFA resultNFA;
        NFAStack tempNFA = this;//定义循环遍历器
        NFAStack lastNFA = this;//定义栈中前一个NFAStack元素
        if(tempNFA.nextNFA==null){
            System.out.println("NFAStack 为空!");
        }
        while(tempNFA.nextNFA!=null){
            lastNFA = tempNFA;
            tempNFA = tempNFA.nextNFA;
        }
        resultNFA=lastNFA.nextNFA.currentNFA;
        lastNFA.nextNFA=null;
    }
    //定义push方法将元素加入栈顶
    public void push(NFAStack newNFA){
        NFAStack tempNFA = this;//定义遍历器
        while(tempNFA.nextNFA!=null){
            tempNFA = tempNFA.nextNFA;
        }
        tempNFA.nextNFA = newNFA;
    }
    //定义top方法
    public NFA top(){
        NFAStack tempNFA = this;//定义遍历器
        while(tempNFA.nextNFA!=null){
            tempNFA = tempNFA.nextNFA;
        }
        return tempNFA.currentNFA;
    }
}