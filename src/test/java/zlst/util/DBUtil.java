package zlst.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	
	public static String DBUrl = "";
	public static String DBName = "";
	public static String user = "";
	public static String pwd = "";
	
	public Connection conn = null;
    public Statement stmt = null;
	
    //创建MySQL链接
	public DBUtil() throws Exception{
		try{
			//加载驱动程序
			Class.forName(DBName);
			try{
				//获取connection对象
				conn = DriverManager.getConnection(DBUrl, user, pwd);
				//创建statement对象
				stmt = conn.createStatement();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	//查询操作
	public ResultSet query(String sql) throws SQLException {
        try {
            return stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return null;
    }

    public boolean delete(String sql) throws SQLException {
        try {
            return stmt.execute(sql);// executeQuery会返回结果的集合，否则返回空值
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return false;
    }

    public int updata(String sql) throws SQLException {
        try {
            return stmt.executeUpdate(sql);// executeQuery会返回结果的集合，否则返回空值
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int insert(String sql) throws SQLException {
        try {
            return stmt.executeUpdate(sql);// executeQuery会返回结果的集合，否则返回空值
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void close(){
        try {
        	stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
