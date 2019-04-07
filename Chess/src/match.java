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
public class match extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/chess?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "xiw752256693";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public match() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected boolean isexists(Statement stmt,ResultSet rs,String sql){
        try {
            rs=stmt.executeQuery(sql);
            if(rs.next())
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
        String username=new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
        Integer next_step=new Integer(request.getParameter("next_step"));
        Integer isregret=new Integer(request.getParameter("isregret"));
        Integer me=new Integer(request.getParameter("me"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                conn=DriverManager.getConnection(DB_URL,USER,PASS);
                stmt=conn.createStatement();
                ResultSet rs = null;
                String sql=null;
                if (next_step==-1){//未准备者第一次请求
                    sql="select red_player from chess_game where black_player='';";
                    if(isexists(stmt,rs,sql)){//存在等待的红方
                        //rs.next();
                        String red_player=rs.getString("red_player");
                        sql="update chess_game set black_play="+username+",next_step=0,now_player='"+red_player+"'isregret=0 where red_player='"+red_player+"';";
                        if(!red_player.equals(username)) {
                            stmt.executeUpdate(sql);//更新该条数据
                            out.println("2");//返回2，表示自己成为黑方
                        }
                    }
                    else {//不存在等待的红方，则自己成为红方
                        sql="insert into chess_game (red_player,black_player,next_step,now_player,isregret) values ('"+username+"',null,null,null,null);";
                        stmt.executeUpdate(sql);//添加一条等待的红方数据
                        out.println("1");//返回1，表示自己成为红方
                    }
                }

                if (next_step==-2){//红方等待中
                    sql="select black_player from chess_game where red_player='"+username+"';";
                    rs=stmt.executeQuery(sql);
                    rs.next();
                    String black_player=rs.getString("black_player");
                    if (!black_player.equals("")){
                        out.println("3");//返回3，表示红方可以开始下棋
                    }
                    out.println("4");
                }

                if (next_step>=0){
                    if (me==0){//黑方
                        sql="select * from chess_game where black_player='"+username+"';";


                    }
                    if(me==1){//红方
                        sql="select * from chess_game where red_player='"+username+"';";
                    }
                    rs=stmt.executeQuery(sql);
                    String red_player=rs.getString("red_player");
                    String black_player=rs.getString("black_player");
                    String now_player=rs.getString("now_player");
                    int nextstep=rs.getInt("next_step");
                    int regret=rs.getInt("isregret");
                    if (regret==1){//悔棋中
                        if (isregret==1) {
                            out.println("-1");//返回-1，表示悔棋成功
                        }
                        else {
                            out.println("-2");//返回-2，表示悔棋失败
                        }
                        if (me==1) {
                            sql = "update chess_game set next_step=0,now_player='" + black_player + "'isregret=0 where red_player='"+username+"';";
                        }
                        if (me==0){
                            sql = "update chess_game set next_step=0,now_player='" + red_player + "'isregret=0 where black_player='"+username+"'';";
                        }
                        stmt.executeUpdate(sql);//更新该条数据
                        rs.close();
                        stmt.close();
                        conn.close();
                        return;
                    }
                    else{//不在悔棋中
                        if (me==1&&now_player.equals(red_player)) {
                            sql = "update chess_game set next_step='"+next_step+"',now_player='" + black_player + "'isregret=0 where red_player='"+username+"';";
                        }
                        if (me==0&&now_player.equals(black_player)){
                            sql = "update chess_game set next_step='"+next_step+"',now_player='" + red_player + "'isregret=0 where black_player='"+username+"';";
                        }
                        stmt.executeUpdate(sql);
                        out.println(new String(String.valueOf(nextstep)));
                    }
                }



                // 完成后关闭
                if(rs!=null)
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