import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class getRow extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/water?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "xiw752256693";

    public getRow() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
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

                String row1=new String(request.getParameter("row1").getBytes("ISO8859-1"),"UTF-8");

                String sql;
                sql="select * from "+row1;
                ResultSet rs=stmt.executeQuery(sql);

                JSONArray jArray1 = new JSONArray();
                JSONArray jArray2 = new JSONArray();
                JSONArray jArray3 = new JSONArray();
                JSONArray jArray4 = new JSONArray();


                while (rs.next()){
                    String Row1=rs.getString("年月");
                    double Row2=rs.getDouble("Np");
                    double Row3=rs.getDouble("Wp");
                    double Row4=rs.getDouble("NpLp");

                    BigDecimal bg1 = new BigDecimal(Row2);
                    bg1 = bg1.setScale(4,BigDecimal.ROUND_HALF_UP);
                    System.out.println(bg1);//普通的计数法

                    BigDecimal bg2 = new BigDecimal(Row3);
                    bg2 = bg2.setScale(4,BigDecimal.ROUND_HALF_UP);
                    System.out.println(bg2);//普通的计数法

                    BigDecimal bg3 = new BigDecimal(Row4);
                    bg3 = bg3.setScale(9,BigDecimal.ROUND_HALF_UP);
                    System.out.println(bg2);

                    jArray1.add(Row1);
                    jArray2.add(bg1);
                    jArray3.add(bg2);
                    jArray4.add(bg3);


                }

                JSONObject jObject=new JSONObject();
                jObject.put("年月", jArray1);
                jObject.put("Np",jArray2);
                jObject.put("Wp",jArray3);
                jObject.put("NpLp",jArray4);

                out.println(jObject);
                //完成后关闭
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