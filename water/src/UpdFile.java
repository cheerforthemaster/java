//import com.jspsmart.upload.Files;
//import com.jspsmart.upload.SmartUpload;
//import com.jspsmart.upload.SmartUploadException;
//import jxl.Sheet;
//import jxl.read.biff.BiffException;
//import org.apache.poi.ss.usermodel.Workbook;
//
//import java.io.*;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class UpdFile extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/water?useSSL=false&serverTimezone=UTC";
//
//    static final String USER = "root";
//    static final String PASS = "xiw752256693";
//
//    public UpdFile() {
//        super();
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // 设置响应内容类型
//        Connection conn=null;
//        Statement stmt=null;
//
//        response.setContentType("text/html;charset=UTF-8");
//
//        PrintWriter out = response.getWriter();
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            try {
//                conn=DriverManager.getConnection(DB_URL,USER,PASS);
//                stmt=conn.createStatement();
//
//
//
//                String row1=new String(request.getParameter("row1").getBytes("ISO8859-1"),"UTF-8");
//                String row2=new String(request.getParameter("row2").getBytes("ISO8859-1"),"UTF-8");
//
//                String sql;
//                sql="select Np,Wp from nazaroff";
//                ResultSet rs=stmt.executeQuery(sql);
//
//
//                while (rs.next()){
//                    String Row1=rs.getString("Np");
//                    String Row2=rs.getString("Wp");
//
//
//                }
//
//
//
//                //out.println(jObject);
//                //完成后关闭
//                rs.close();
//                stmt.close();
//                conn.close();
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//                System.out.println("失败!");
//            }
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        finally {
//            if (stmt!=null){
//                try {
//                    stmt.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (conn!=null){
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }
//
//
//
//
////    // 处理 POST 方法请求的方法
////    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        doGet(request, response);
////    }
//
//    public void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setCharacterEncoding("utf-8");
//        String type = request.getParameter("type");
//        if (type.equals("excToMqsql")) {
//            /*
//             *先对文件进行上传,代码如下
//             */
//            SmartUpload su = new SmartUpload();
//            su.initialize(this.getServletConfig(), request, response);
//            // 设定允许上传的文件（通过扩展名限制）
//            su.setAllowedFilesList("xls,xlsx");
//            try {
//                su.upload();
//                Files files = su.getFiles();
//                String temp = "";
//                for (int i = 0; i < files.getCount(); i++) {
//                    com.jspsmart.upload.File file = files.getFile(i);
//                    temp = "/upload/staff.xls";//将上传的文件放在根目录下的upload文件夹下并命名为固定的文件名
//                    file.saveAs(temp, SmartUpload.SAVE_VIRTUAL);//将文件进行上传
//                }
//                System.out.println("上传成功!");
//                /*
//                 *文件上传成功后,将调用文件导入的方法;
//                 */
//                InSub  es = new InSub ();//创建自身对象;
//                es.getDate(request, response);//调用导入Excel数据的方法;
//
//                // ExcelToMySql(request, response);
//            } catch (SmartUploadException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }
//
//    // ------------------------------------------
//    //导入Excel信息的方法
//    // ----------------------------------------
//    public void getDate(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String errMessage = null;
//        boolean flags=false;
//
//        List liststu = new ArrayList();
//        // 找到上传后的文件目录,并创建IO流
//        File f = new File(request.getSession().getServletContext().getRealPath("")+"\\upload\\staff.xls");
//        InputStream is = new FileInputStream(f);
//        try {
//            // 创建工作簿
//            Workbook wb = Workbook.getWorkbook(is);
//            // 创建工作表
//            org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheet(String.valueOf(0));
//            String content = null;
//            for (int i = 1; i < sheet.getRows(); i++) {
//                staffBean staff = new staffBean();
//                for (int j = 0; j < sheet.getColumns(); j++) {
//                    content = sheet.getCell(j, i).getContents();
//                    // System.out.print(content);
//                    if (staff.getName() == null)//
//                    {
//                        staff.setName(sheet.getCell(j, i).getContents());
//                        continue;
//                    }
//                    if (staff.getIdcard() == null) {
//                        staff.setIdcard(sheet.getCell(j, i).getContents());
//                        continue;
//                    }
//                }
//                flags=getStaffInfo(staff);
//            }
//            /*
//             * 文件导入成功后进入提示界面
//             */
//            if(flags){
//                System.out.println("总表导入成功!");
//                response.sendRedirect("CompareServlet?type=show");
//            }else{
//                System.out.println("总表未导入成功!");
//                errMessage = "导入总表未成功,请重新导入！";
//                request.setAttribute("error", errMessage);
//                request.getRequestDispatcher("../tips.jsp").forward(request,
//                        response);
//            }
//        } catch (BiffException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    /*
//     *sql的添加方法,此方法也可以写到Dao文件,然后再servlet中调用;
//     */
//    public boolean getStaffInfo(staffBean staff) {
//        boolean flag=false;
//        Connection conn = null;
//        PreparedStatement pt = null;
//        ResultSet rs = null;
//        String sql = "insert into staff(name,idcard) values (?,?)";
//        jdbcDtiver jdbc = new jdbcDtiver();
//        conn = jdbc.Driver();
//        try {
//            pt = conn.prepareStatement(sql);
//            pt.setString(1, staff.getName());
//            pt.setString(2, staff.getIdcard());
//            int n=pt.executeUpdate();
//            if(n>0){
//                flag=true;
//            }
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        return flag;
//    }
//}
