package Util;

import java.sql.*;
import java.util.List;

public class DBHelper {
    static String dbname = "airconditionerdb";
    static String username = "root";
    static String userpwd = "123456";
    static String url = "jdbc:mysql://localhost:3306/" + dbname;

    // 加载驱动
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
            System.out.println("驱动器异常！");
        }
    }

    // 连接数据库
    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, userpwd);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败！");
        }
        return conn;
    }

    // 查询
    public static ResultSet executeQuery(Connection conn, String sql) throws SQLException {
        ResultSet re = null;
        Statement state = conn.createStatement();
        re = state.executeQuery(sql);
        return re;
    }

    // 查询 带参数
    public static ResultSet executeQuery(Connection conn, String sql, List params) throws SQLException {
        ResultSet re = null;
        PreparedStatement s = conn.prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                s.setObject(i + 1, params.get(i));
            }
        }
        re = s.executeQuery();
        return re;
    }

    // 增删改
    public static int executeUpdate(String sql) throws SQLException {
        int re = 0;
        Statement state = null;
        Connection conn = null;
        try {
            conn = getConn();
            state = conn.createStatement();
            re = state.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(null, state, conn);
        }
        return re;
    }

    // 增删改 带参数
    public static int executeUpdate(String sql, List params) throws SQLException {
        int row = 0;
        PreparedStatement state = null;
        Connection conn = null;
        try {
            conn = getConn();
            state = conn.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                state.setObject(i + 1, params.get(i));
            }
            row = state.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(null, state, conn);
        }
        return row;
    }

    // 关闭资源
    public static void closeResource(ResultSet re, Statement state, Connection conn) throws SQLException {
        if (re != null) {
            re.close();
        }
        if (state != null) {
            state.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public static void main(String[] args) {
        Connection con = getConn();
        if (con != null) {
            System.out.println("数据库连接成功：" + con);
        } else {
            System.out.println("数据库连接失败");
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeStatement(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}





//package Util;
//
//import java.sql.*;
//import java.util.List;
//
//
//
//public class DBHelper {
//	static String dbname="airconditionerdb";
//	static String username="root";
//	static String userpwd="123456";
//	static String url="jdbc:mysql://localhost/"+dbname;
//	
////	加载驱动
//	static {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//			System.out.println("驱动器异常！");
//		} 
//	}
//	
////	连接数据库
//	public static Connection getConn() {
//		Connection conn=null;
//		try {
//		    conn=DriverManager.getConnection(url,username,userpwd);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();;
//		}
//		return conn;
//	}
//	//查询
//	public static ResultSet executeQuery(Connection conn,String sql) throws SQLException {
//		ResultSet re=null; 
//		Statement state=conn.createStatement();
//		re=state.executeQuery(sql);
//		return re;
//	}
//	// 查询 带参数
//	public static ResultSet executeQuery(Connection conn,String sql,List params) throws SQLException {
//		ResultSet re=null;
//		PreparedStatement s=conn.prepareStatement(sql);
//		if(params!=null) {
//			for (int i = 0; i < params.size(); i++) {
//				s.setObject(i+1, params.get(i));
//			}
//		}
//		re=s.executeQuery();
//		return re;
//	}
//	// 4 增删改
//	public static int executeUpdate(String sql) throws SQLException {
////		1.变量声明
////		2.try代码块 中写可能出现异常的代码
////		3.catch 捕获异常
////		4.finally 关闭资源
////		5.返回数据
//		int re=0;//受影响行
//		Statement state=null;
//		Connection conn=null;
//		try {
////			创建statement对象
//			conn=getConn();
//			state=conn.createStatement();
//			//执行SQL语句
//			re=state.executeUpdate(sql);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}finally {
//			closeResource(null,state,conn);
//			// TODO: handle finally clause
//		}
//		return re;
//	}
//	// 增删改 带参数
//	public static int executeUpdate(String sql,List params) throws SQLException {
////		1.变量声明
////		2.try代码块 中写可能出现异常的代码
////		3.catch 捕获异常
////		4.finally 关闭资源
////		5.返回数据
//		int row=0;//受影响行
//		PreparedStatement state=null;
//		Connection conn=null;
//		try {
////			创建预处理对象
//			conn=getConn();
//			state=conn.prepareStatement(sql);
//			for (int i = 0; i < params.size(); i++) {
//				state.setObject(i+1, params.get(i));
//			}
//			//执行SQL语句
//			row=state.executeUpdate();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}finally {
//			closeResource(null,state,conn);
//		}
//		return row;
//	}
//	//关闭资源
//	public static void closeResource(ResultSet re,Statement state,Connection conn) throws SQLException {
//		if(re!=null) {
//			re.close();
//		}
//		if(state!=null) {
//			state.close();
//		}
//		if(conn!=null) {
//			conn.close();
//		}
//	} 
//	public static void main(String[] args) {
//		Connection con = getConn();
//		System.out.println(con);
//	}
//	public static void closeResultSet(ResultSet rs) {
//		// TODO Auto-generated method stub
//		 if (rs != null) {
//	            try {
//	                rs.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	}
//	public static void closeStatement(PreparedStatement pstmt) {
//		// TODO Auto-generated method stub
//		 if (pstmt != null) {
//	            try {
//	                pstmt.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	}
//	public static void closeConnection(Connection conn) {
//		// TODO Auto-generated method stub
//		 if (conn != null) {
//	            try {
//	                conn.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	}
//	
//
//           
//}
