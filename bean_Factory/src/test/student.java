package test;

public class student extends person
{
    private String name;
    private int age;
    public student()
    {
        System.out.println("学生 无参构造");
    }
    public student(String name,int age)
    {
        this.name=name;
        this.age=age;
        System.out.println("学生赋值");
    }
    public String toString()
    {
        return "学生姓名："+this.name+" 年龄："+this.age+"岁";
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name=name;
    }
    public int getAge() {
        return age;
    }

    @Override
    public void work() {
        System.out.println("做作业");
    }
}