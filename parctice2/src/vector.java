public class vector implements api {
    private Comparable []vec;
    private int size;
    private int pos;
    public vector(){
        pos=0;
        size = 50;
        vec=new Comparable[size];
    }
    @Override
    public boolean insert(Comparable Data) {
        isfull();
        vec[pos]=Data;
        size--;
        pos++;
        return true;
    }

    @Override
    public boolean visit() {
        for (int i=0;i<pos;i++){
            System.out.println(vec[i]);
        }
        return true;
    }

    @Override
    public void search(Comparable Data) {
        int tem=0;
        for (int i=0;i<pos;i++){
            if (Data==vec[i]){
                System.out.println("前一个："+vec[tem]+"后一个"+vec[i+1]);
            }
            tem=i;
        }
    }
    private int isfull(){
        if (size<=0)
        {
            size=size*2;
            Comparable []temvec=new Comparable[size];
            for (int i=0;i<size;i++){
                temvec[i]=vec[i];
            }
            vec=temvec;
            return 1;
        }
        else
            return 0;
    }
}
