public class Main {
    public static void main(String[] agrs){
        //System.out.println("create a Main!");
        bst tree=new bst("10");
        tree.insert("21");
        tree.insert("0");
        tree.insert("35");
        System.out.println("遍历：");
        tree.visit();
        System.out.println("搜寻：");
        tree.search("35");

        vector Vec=new vector();
        Vec.insert("a");
        Vec.insert("b");
        Vec.insert("c");
        Vec.insert("d");
        System.out.println("");
        System.out.println("遍历：");
        Vec.visit();
        System.out.println("搜寻：");
        Vec.search("d");
    }
}
