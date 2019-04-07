import java.util.ArrayList;

//构建NFA图中的节点单元
public class NFANode {
    public int stateNum;
    //pathChar表示前一个节点通过字符pathChar转到当前状态,对于同一个状态，它有很多入方向，故根据不同的入方向相应的改变pathChar的值
    public char pathChar;
    public ArrayList<NFANode> nextNodes;//链表形式进行后继节点存储
    public NFANode(int stateNum, char pathChar){
        this.pathChar = pathChar;
        this.stateNum = stateNum;
        this.nextNodes = new ArrayList<NFANode>();
    }
}