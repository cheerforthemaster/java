import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Chinese_chess
 */
@WebServlet("/Chinese_chess")
public class Chinese_chess extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/chess?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "xiw752256693";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Chinese_chess() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        Connection conn=null;
        Statement stmt=null;

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String title = "数据库中的数据";
        // 处理中文
        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" );

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                conn=DriverManager.getConnection(DB_URL,USER,PASS);
                stmt=conn.createStatement();
                String sql;
                sql="select name,password,email from chess_player";
                ResultSet rs=stmt.executeQuery(sql);

                while (rs.next()){
                    String name =rs.getString("name");
                    String password=rs.getString("password");
                    String email=rs.getString("email");

                    out.println("name:"+name);
                    out.println(" password:"+password);
                    out.println(" email:"+email);
                }
                out.println("</body></html>");
                // 完成后关闭
                rs.close();
                stmt.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("连接失败!");
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