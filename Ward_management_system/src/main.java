import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        OracleJdbc test = new OracleJdbc();
        try {

            //test.query("select * from \"database\".\"医护人员\"", true);
            String rs=test.callBack("department_select","内科");

            System.out.println(rs);
        } catch (SQLException e) {}

        test.close();
    }
}
