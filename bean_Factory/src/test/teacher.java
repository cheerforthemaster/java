package test;

public class teacher extends person
{
    public teacher()
    {
        System.out.println("老师 无参构造");
    }

    @Override
    public String toString() {
        return "老师";
    }

    @Override
    public void work() {
        System.out.println("教学");
    }
}
