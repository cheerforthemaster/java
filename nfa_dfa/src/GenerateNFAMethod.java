public class GenerateNFAMethod {
    GetStateNumber getNum = new GetStateNumber();
    char nul = 'E';//nul表示状态转换条件为空
    //当遇到非符号数时只需新建一个NFA，其中包含起点和终点；
    public NFA meetNonSymbol(char nonSymbol){
        NFANode headNode = new NFANode(getNum.getStateNum(),nul);
        NFANode tailNode = new NFANode(getNum.getStateNum(),nonSymbol);//入方向的符号为nonSymbol
        headNode.nextNodes.add(tailNode);
        NFA newNFA = new NFA(headNode,tailNode);
        return newNFA;
    }

    //当遇到符号数'*'时增加头尾节点并连接
    public NFA meetStarSymbol(NFA oldNFA){
        NFANode oldHeadNode = oldNFA.headNode;
        NFANode oldTailNode = oldNFA.tailNode;
        NFANode newHeadNode = new NFANode(getNum.getStateNum(),nul);
        NFANode newTailNode = new NFANode(getNum.getStateNum(),nul);
        newHeadNode.nextNodes.add(oldHeadNode);
        newHeadNode.nextNodes.add(newTailNode);
        oldTailNode.nextNodes.add(newTailNode);
        oldTailNode.nextNodes.add(oldHeadNode);
        NFA newNFA = new NFA(newHeadNode,newTailNode);
        return newNFA;
    }

    //当遇到符号数为'.'即表示连接操作时
    public NFA meetAndSymbol(NFA firstNFA, NFA secondNFA){
        //前一个NFA的尾节点与后一个NFA的头节点相连,需要增加头尾节点重新组成一个NFA；
        NFANode newHeadNode = new NFANode(getNum.getStateNum(),nul);
        NFANode newTailNode = new NFANode(getNum.getStateNum(),nul);
        firstNFA.tailNode.nextNodes.add(secondNFA.headNode);
        newHeadNode.nextNodes.add(firstNFA.headNode);
        secondNFA.tailNode.nextNodes.add(newTailNode);
        NFA newNFA = new NFA(newHeadNode,newTailNode);
        return newNFA;
    }

    //当遇到符号数为'|'时添加头尾节点进行或操作
    public NFA meetOrSymbol(NFA firstNFA, NFA secondNFA){
        NFANode oldFirstHeadNode = firstNFA.headNode;
        NFANode oldSecondHeadNode = secondNFA.headNode;
        NFANode oldFirstTailNode = firstNFA.tailNode;
        NFANode oldSecondTailNode = secondNFA.tailNode;
        NFANode newHeadNode = new NFANode(getNum.getStateNum(),nul);
        NFANode newTailNode = new NFANode(getNum.getStateNum(),nul);
        newHeadNode.nextNodes.add(oldFirstHeadNode);
        newHeadNode.nextNodes.add(oldSecondHeadNode);
        oldFirstTailNode.nextNodes.add(newTailNode);
        oldSecondTailNode.nextNodes.add(newTailNode);
        NFA newNFA = new NFA(newHeadNode,newTailNode);
        return newNFA;
    }
}