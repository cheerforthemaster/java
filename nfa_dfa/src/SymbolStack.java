public class SymbolStack {
    public char currentSymbol;
    public SymbolStack nextSymbol;
    public SymbolStack(char currentSymbol){
        this.currentSymbol = currentSymbol;
        this.nextSymbol = null;
    }
    //定义pop符号栈顶元素的方法
    public void pop(){
        char result;
        SymbolStack tempStack = this;//定义符号栈遍历器
        SymbolStack lastStack = this;//定义前一个栈中元素
        if(tempStack.nextSymbol==null){
            System.out.println("SymbolStack为空!");
        }
        while(tempStack.nextSymbol!=null){
            lastStack = tempStack;
            tempStack = tempStack.nextSymbol;
        }
        result = lastStack.nextSymbol.currentSymbol;
        lastStack.nextSymbol = null;
    }
    //定义push方法加入栈顶元素
    public void push(SymbolStack newSymbol){
        SymbolStack tempStack = this;
        while(tempStack.nextSymbol!=null){
            tempStack = tempStack.nextSymbol;
        }
        tempStack.nextSymbol = newSymbol;
    }

    public char top(){
        SymbolStack tempStack = this;
        while(tempStack.nextSymbol!=null){
            tempStack = tempStack.nextSymbol;
        }
        return tempStack.currentSymbol;
    }
}