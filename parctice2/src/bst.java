class node<T extends Comparable<T>>{
    public T data;
    public node left;
    public node right;
    node(){//无参
        data=null;
        left=null;
        right=null;
    }
    node(T Data, node Left, node Right){//三个参数
        data=Data;
        left=Left;
        right=Right;
    }
    node(T Data){//仅数据域
        data=Data;
    }

    @Override
    public String toString(){
        String l_data="null";
        String r_data="null";
        if (left!=null)
            l_data=String.valueOf(left.data);
        if (right!=null)
            r_data=String.valueOf(right.data);

        return (String.valueOf(data)+"  左结点："+l_data+"右结点："+r_data);
    }
}

public class bst implements api {
    private node root;

    public bst(Comparable Data){//构造函数
        root=new node<>(Data);
    }

    @Override
    public boolean insert(Comparable Data){//插入结点
        if (root==null)
            return false;

        node temNode=root;
        node lastNode=temNode;
        while (temNode!=null){
            if (Data.compareTo((Comparable) temNode.data)>0){
                lastNode=temNode;
                temNode=temNode.right;
            }
            else{
                lastNode=temNode;
                temNode=temNode.left;
            }
        }
        if (Data.compareTo((Comparable) lastNode.data)>0){
            lastNode.right=new node<>(Data);
        }
        else{
            lastNode.left=new node<>(Data);
        }
        return true;
    }

    @Override
    public boolean visit(){//遍历的接口
        return real_visit(root);
    }
    private boolean real_visit(node node){//实际遍历的函数
        if (node==null){
            return false;
        }
        else {
            System.out.println(node);
            real_visit(node.left);
            real_visit(node.right);
            return true;
        }
    }

    @Override
    public void search(Comparable Data) {//查找Data所在的结点
        node temNode = root;
        while (temNode != null && temNode.data != Data) {
            if (Data.compareTo((Comparable) temNode.data) > 0) {
                temNode = temNode.right;
            } else {
                temNode = temNode.left;
            }
        }
        System.out.print(temNode.toString());
    }
}
