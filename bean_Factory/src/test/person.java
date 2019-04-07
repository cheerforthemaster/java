package test;

public abstract class person
{
    private String name;
    private int age;
    public person(String name,int age)
    {
        this.name=name;
        this.age=age;
    }
    public person()
    {
        System.out.println("人 无参构造");
    }

    public String toString()
    {
        return "人";
    }

    public abstract void work();
}


