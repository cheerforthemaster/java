import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class back_end extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://118.25.25.123:3306/sa?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "xiw752256693";

    public back_end() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型

        Connection conn=null;
        Statement stmt=null;

        response.setContentType("text/json;charset=UTF-8");

        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                conn=DriverManager.getConnection(DB_URL,USER,PASS);
                stmt=conn.createStatement();
                String sql;
                String requestURL = request.getRequestURL().toString();
                String queryString = request.getQueryString();
                String column=new String(request.getParameter("select").getBytes("ISO8859-1"),"UTF-8");

                sql="select "+column+",count("+column+") as num from medicaldata GROUP BY "+column;
                ResultSet rs=stmt.executeQuery(sql);

                JSONObject jobj=new JSONObject();
                JSONArray jarr=new JSONArray();
                while (rs.next()){
                    String sql_column=rs.getString(column);
                    int sql_num= rs.getInt("num");
                    jobj.put("value",sql_num);
                    jobj.put("name",sql_column);
                    jarr.add(jobj);
                    //System.out.println(sql_column);
                    //System.out.println(sql_num);
                }
                out.print(jarr.toString());
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
