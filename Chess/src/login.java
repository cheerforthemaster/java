import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/chess?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "xiw752256693";

    public login() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型

        Connection conn=null;
        Statement stmt=null;

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String title = "登陆结果";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                conn=DriverManager.getConnection(DB_URL,USER,PASS);
                stmt=conn.createStatement();
                String sql;
                String username=new String(request.getParameter("username").getBytes("ISO8859-1"),"UTF-8");
                String password=new String(request.getParameter("password").getBytes("ISO8859-1"),"UTF-8");


                sql="select password from chess_player where name='"+username+"'";
                ResultSet rs=stmt.executeQuery(sql);

                rs.next();
                String sql_password=rs.getString("password");

                if (password.equals(sql_password)){
                    sql="select * from chess_player where name='"+username+"'";
                    rs=stmt.executeQuery(sql);
                    rs.next();
                    String name="用户名:"+rs.getString("name")+"<br/>";
                    String score ="评分:"+rs.getObject("score")+"<br/>";
                    String grade ="等级:"+rs.getObject("grade")+"<br/>";
                    String victory ="胜局:"+rs.getObject("victory")+"<br/>";
                    String defeat ="负局:"+rs.getObject("defeat")+"<br/>";
                    String draw ="平局:"+rs.getObject("draw")+"<br/>";
                    out.println(
                            "<!DOCTYPE html>\n" +
                                    "<html lang=\"en\" >\n" +
                                    "<head>\n" +
                                    "  <meta charset=\"UTF-8\">\n" +
                                    "  <title>中国象棋（网页版）</title>\n" +
                                    "  <link rel='stylesheet prefetch' href='css/bootstrap.min.css'>\n" +
                                    "  <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                                    "</head>\n" +
                                    "<body>\n" +
                                    "    <div style=\"float:right;text-align:left\">\n" +
                                    "      <p>"+name+score+grade+victory+defeat+draw+
                                    "</p>\n" +
                                    "    </div>\n" +
                                    "    <div class=\"wrapper\">\n" +
                                    "    <form class=\"form-signin\" action=\"login\" method=\"GET\">       \n" +
                                    "      <h2 class=\"form-signin-heading\">对局选择</h2>\n" +
                                    "      <a href=\"chess_vs_person.html?name="+rs.getString("name")+"\">\n" +
                                    "\t      <button class=\"btn btn-lg btn-primary btn-block\" type=\"button\">快速匹配</button><br>\n" +
                                    "      </a>\n" +
                                    "      <a href=\"chess_vs_computer.html\">\n" +
                                    "\t      <button class=\"btn btn-lg btn-primary btn-block\" type=\"button\">人机大战</button>\n" +
                                    "      </a>\n" +
                                    "    </form>\n" +
                                    "  </div>\n" +
                                    "</body>\n" +
                                    "</html>"
                    );

                }
                else {
                    out.println("<script>alert(\"登陆失败\");top.location='index.html';</script>");
                }
                //out.println("end");
                //out.println("</body></html>");
                // 完成后关闭
                rs.close();
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