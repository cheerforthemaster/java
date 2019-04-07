import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class getName extends HttpServlet {

    private static final long serialVersionUID = 1L;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/water?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "xiw752256693";

    public getName() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        Connection conn=null;
        PreparedStatement stmt=null;

        response.setContentType("text/json;charset=UTF-8");

        PrintWriter out = response.getWriter();


        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                conn=DriverManager.getConnection(DB_URL,USER,PASS);
                //stmt=conn.createStatement();

                //String tablename=new String(request.getParameter("tablename").getBytes("ISO8859-1"),"UTF-8");

                //String sql;
                //sql="show tables";
                //stmt = conn.prepareStatement(sql);
                //ResultSet rs=stmt.executeQuery(sql);
                //ResultSetMetaData data=rs.getMetaData();
                DatabaseMetaData dbMetaData = conn.getMetaData();
                ResultSet rs = dbMetaData.getTables(null, null, null,new String[] { "TABLE" });


                JSONArray jArray1 = new JSONArray();
                //JSONArray jArray2 = new JSONArray();

                int i=1;
                while (rs.next()){
                    String table_name = rs.getString("TABLE_NAME");
                    jArray1.add(table_name);
                    i++;
                }

                JSONObject jObject=new JSONObject();
                jObject.put("table_name", jArray1);
                //jObject.put("Row2",jArray2);

                out.println(jObject);

                // 完成后关闭
                rs.close();
                stmt.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("失败!");
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
