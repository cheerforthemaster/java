import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class operation extends HttpServlet {

    public operation() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        OracleJdbc test = new OracleJdbc();

        response.setContentType("text/json;charset=UTF-8");

        PrintWriter out = response.getWriter();

        try {
            String sql=new String();

            String requestURL = request.getRequestURL().toString();
            String queryString = request.getQueryString();
            String table=new String(URLDecoder.decode(request.getParameter("table"),"GBK"));
            String op=new String(request.getParameter("op").getBytes("ISO8859-1"),"UTF-8");
            String value=new String(URLDecoder.decode(request.getParameter("value"),"GBK"));

            String[] val=value.split(",");
            boolean isselect=false;
            if (op.equals("select"))
            {
                isselect=true;
                if (table.equals("病房"))
                {
                    sql="select * from \"database\".\""+table+"\" where \"database\".\""+table+"\".\"病人\"=\'"+val[4]+"\'";
                }
                if (table.equals("科室"))
                {
                    sql="select * from \"database\".\""+table+"\" where \""+table+"\".\"科名\"=\'"+val[0]+"\'";
                }
                if (table.equals("医护人员"))
                {
                    sql="select * from \"database\".\""+table+"\" where \""+table+"\".\"姓名\"=\'"+val[0]+"\'";
                }
                if (table.equals("住院病人"))
                {
                    sql="select * from \"database\".\""+table+"\" where \""+table+"\".\"姓名\"=\'"+val[0]+"\'";
                }
            }


            if (op.equals("insert"))
            {
                if (table.equals("病房"))
                {
                    sql="insert into \"database\".\""+table+"\"(\"病床数\",\"所属科\",\"病房号\",\"床号\",\"病人\") values("+Integer.parseInt(val[0])+",\'"+val[1]+"\',"+Integer.parseInt(val[2])+","+Integer.parseInt(val[3])+",\'"+val[4]+"\')";
                }
                if (table.equals("科室"))
                {
                    sql="insert into \"database\".\""+table+"\"(\"科名\",\"主任\",\"科地址\",\"电话\",\"医生\") values(\'"+val[0]+"\',\'"+val[1]+"\',\'"+val[2]+"\',"+Integer.parseInt(val[3])+",\'"+val[4]+"\')";
                }
                if (table.equals("医护人员"))
                {
                    sql="insert into \"database\".\""+table+"\"(\"姓名\",\"技术职称\",\"所属科室\") values(\'"+val[0]+"\',\'"+val[1]+"\',\'"+val[2]+"\')";
                }
                if (table.equals("住院病人"))
                {
                    sql="insert into \"database\".\""+table+"\"(\"姓名\",\"病历号\",\"年龄\",\"性别\",\"诊断\",\"主管医生\",\"科\",\"房间\",\"病床号\") values(\'"+val[0]+"\',"+Integer.parseInt(val[1])+","+Integer.parseInt(val[2])+",\'"+val[3]+"\',\'"+val[4]+"\',\'"+val[5]+"\',\'"+val[6]+"\',"+Integer.parseInt(val[7])+","+Integer.parseInt(val[8])+")";
                }
            }

            if (op.equals("delete"))
            {
                if (table.equals("病房"))
                {
                    sql="delete from \"database\".\""+table+"\" where \"database\".\""+table+"\".\"病人\"=\'"+val[4]+"\'";
                }
                if (table.equals("科室"))
                {
                    sql="delete from \"database\".\""+table+"\" where \""+table+"\".\"科名\"=\'"+val[0]+"\'";
                }
                if (table.equals("医护人员"))
                {
                    sql="delete from \"database\".\""+table+"\" where \""+table+"\".\"姓名\"=\'"+val[0]+"\'";
                }
                if (table.equals("住院病人"))
                {
                    sql="delete from \"database\".\""+table+"\" where \""+table+"\".\"姓名\"=\'"+val[0]+"\'";
                }
            }



            String rs=test.query(sql,isselect,table);
            out.print(rs);
            //System.out.println(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
