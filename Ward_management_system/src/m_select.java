import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class m_select extends HttpServlet {

    public m_select() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        OracleJdbc test = new OracleJdbc();

        response.setContentType("text/json;charset=UTF-8");

        PrintWriter out = response.getWriter();

        try {
            String requestURL = request.getRequestURL().toString();
            String queryString = request.getQueryString();
            String sel=new String(request.getParameter("select").getBytes("ISO8859-1"),"UTF-8");
            String name=new String(URLDecoder.decode(request.getParameter("name"),"GBK"));
            String rs=test.callBack(sel,name);
            out.print(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
