import java.sql.*;

public class DbUtil {
    public static void main(String[] args) {
        PreparedStatement ps = null;
        Connection ct = null;
        ResultSet rs = null;

        String url = "jdbc:sqlserver://localhost:1433;databaseName=employee";
        String user="sa";
        String password="xiw752256693";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("加载驱动成功！");
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("加载驱动失败！");
        }
        try {
            //2.连接
            ct=DriverManager.getConnection( url,user,password);
            System.out.println("连接数据库成功！");
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("连接数据库失败！");
        }

        /* 尝试添加数据到数据库中  */
        try {
            //在连接对象的基础上创建会话对象
            CallableStatement cstmt = ct.prepareCall("{?=call printName(?,?,?)}");
            cstmt.registerOutParameter(1, Types.INTEGER);
            //cstmt.setInt(1, 0);
            System.out.println("年龄最低值为18");
            cstmt.setInt(2, 18);
            System.out.println("年龄最低值为60");
            cstmt.setInt(3, 60);
            cstmt.registerOutParameter(4, Types.INTEGER);
            rs=cstmt.executeQuery();
            while(rs != null && rs.next()){
                System.out.println("名字:"+rs.getString(1));
            }
            int three = cstmt.getInt(4);
            System.out.println("总人数："+three);
            int returnValue=cstmt.getInt(1);
            System.out.println("返回值："+returnValue);

            //关闭会话对象
            cstmt.close();
            //关闭连接对象
            ct.close();

        }catch(SQLException ex) {
            ex.printStackTrace();
            System.out.println("失败");
        }
    }
}
