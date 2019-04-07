import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OracleJdbc {

    private static Connection conn = null;

    private static String driver = "oracle.jdbc.driver.OracleDriver"; //驱动

    private static String url = "jdbc:oracle:thin:@//127.0.0.1:1521/orcl"; //连接字符串

    private static String username = "system"; //用户名

    private static String password = "Xiw752256693"; //密码


    // 获得连接对象
    private static synchronized Connection getConn(){
        if(conn == null){
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public String callBack(String selectName,String arg) throws SQLException {
        conn=getConn();
        String res=new String();
        CallableStatement cstmt = conn.prepareCall("{call \"database\".\""+selectName+"\"(?,?)}");
        try {
            cstmt.setString(1,arg);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet rs=(ResultSet)cstmt.getObject(2);

            while(rs != null && rs.next()){
                if (selectName.equals("department_select"))
                {
                    res="科名:"+rs.getString(1)+
                            ";主任:"+rs.getString(2)+
                            ";病床数:"+rs.getInt(3)+
                            ";病房数:"+rs.getInt(4)+
                            ";已用床数:"+rs.getInt(5);
                }
                if (selectName.equals("doctor_select"))
                {
                    res="所属科室:"+rs.getString(1)+
                            ";主治医生:"+rs.getString(2)+
                            ";技术职称:"+rs.getString(3)+
                            ";病人姓名:"+rs.getString(4);
                }
                if(selectName.equals("patient_select"))
                {
                    res="科:"+rs.getString(1)+
                            ";主治医生:"+rs.getString(2)+
                            ";病人姓名:"+rs.getString(3)+
                            ";病房号:"+rs.getInt(4)+
                            ";床号:"+rs.getInt(5);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cstmt.close();
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(res);
        res = m.replaceAll("");
        //res.replaceAll("\0","");
        return res;
    }


    //执行sql语句
    public String query(String sql, boolean isSelect, String table) throws SQLException{
        PreparedStatement pstmt;
        String all=new String();
        try {
            pstmt = getConn().prepareStatement(sql);
            //建立一个结果集，用来保存查询出来的结果
            ResultSet rs=null;
            if (isSelect==true)
            {
                rs = pstmt.executeQuery();
            }
            else
            {
                pstmt.executeUpdate();
                all="操作成功";
            }
            while (rs!=null&&rs.next()&&isSelect)
            {
                if (table.equals("病房"))
                {
                    all="所属科:"+rs.getString(2)+
                            "病人:" +rs.getString(5)+
                            "病房号:" +rs.getInt(3)+
                            "床号:" +rs.getInt(4)+
                            "病床数:"+rs.getInt(1);
                }
                if (table.equals("科室"))
                {
                    all="科名:" +rs.getString(1)+
                            "主任:" +rs.getString(2)+
                            "科地址:" +rs.getString(3)+
                            "电话: " +rs.getInt(4)+
                            "医生: "+rs.getString(5);
                }
                if (table.equals("医护人员"))
                {
                    all="姓名:" +rs.getString(1)+
                            "技术职称: " +rs.getString(2)+
                            "所属科: "+rs.getString(3);
                }
                if (table.equals("住院病人"))
                {
                    all="姓名:" +rs.getString(1)+
                            "病历号: " +rs.getInt(2)+
                            "年龄: " +rs.getInt(3)+
                            "性别: " +rs.getString(4)+
                            "诊断: " +rs.getString(5)+
                            "主管医生: "+rs.getString(6)+
                            "科: " +rs.getString(7)+
                            "房间号: " +rs.getInt(8)+
                            "病床号: "+rs.getInt(9);
                }
            }
            if (rs!=null)
                rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(all);
        all = m.replaceAll("");
        return all;
    }

    //关闭
    public void close(){
        try {
            getConn().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

