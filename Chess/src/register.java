import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class register extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/chess?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "xiw752256693";

    public register() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型

        Connection conn=null;
        Statement stmt=null;

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String title = "注册结果";



        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                conn=DriverManager.getConnection(DB_URL,USER,PASS);
                stmt=conn.createStatement();
                String sql;
                String username=new String(request.getParameter("username").getBytes("ISO8859-1"),"UTF-8");
                String password=new String(request.getParameter("password").getBytes("ISO8859-1"),"UTF-8");
                String email=new String(request.getParameter("email").getBytes("ISO8859-1"),"UTF-8");


                sql="insert into chess_player values('"+username+"','"+password+"','"+email+"',"+0+","+0+","+0+","+0+","+0+")";
                stmt.executeUpdate(sql);
                out.println("<script language=\"javascript\">alert(\"注册成功\");top.location='index.html';</script>");
                // 完成后关闭
                stmt.close();
                conn.close();



            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}