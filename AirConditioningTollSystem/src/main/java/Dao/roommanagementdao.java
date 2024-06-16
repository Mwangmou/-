package Dao;

import java.sql.*;
import java.util.*;

import Bean.roommanagementinfo;
import Util.DBHelper;

public class roommanagementdao {

    // 查询全部的学生信息
    public static List<roommanagementinfo> getStudentList(String sql) {
        List<roommanagementinfo> list = new ArrayList<>();
        try (Connection conn = DBHelper.getConn();
             ResultSet re = DBHelper.executeQuery(conn, sql)) {
            while (re.next()) {
                list.add(new roommanagementinfo(re.getInt(1), re.getInt(2), re.getInt(3), re.getInt(4), re.getDouble(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int UpdateStudent(String sql) {
        int re = 0;
        try (Connection conn = DBHelper.getConn()) {
            re = DBHelper.executeUpdate(sql, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }

    // 添加学生信息
    public static int AddStudent(roommanagementinfo stu) {
        int re = 0;
        String sql = "insert into roommanagement values(?,?,?,?,?)";
        List<Object> params = Arrays.asList(stu.getID(), stu.getLouDong(), stu.getUnitNumber(), stu.getRoomNumber(), stu.getBalance());
        try (Connection conn = DBHelper.getConn()) {
            re = DBHelper.executeUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }

    // 根据id取学生信息
    public static roommanagementinfo getStudent(int ID) {
        roommanagementinfo stu = null;
        String sql = "select * from roommanagement where ID=?";
        try (Connection conn = DBHelper.getConn();
             ResultSet re = DBHelper.executeQuery(conn, sql, Arrays.asList(ID))) {
            if (re.next()) {
                stu = new roommanagementinfo(re.getInt(1), re.getInt(2), re.getInt(3), re.getInt(4), re.getDouble(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stu;
    }

    // 根据ID修改学生信息
    public static int UpdateStudent(roommanagementinfo stu, int id) {
        int re = 0;
        String sql = "update roommanagement set LouDong=?, UnitNumber=?, RoomNumber=?, Balance=? where ID=?";
        List<Object> params = Arrays.asList(stu.getLouDong(), stu.getUnitNumber(), stu.getRoomNumber(), stu.getBalance(), id);
        try (Connection conn = DBHelper.getConn()) {
            re = DBHelper.executeUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }

    // 登录判断
    public static boolean StudentLogin(String s1, String s2) {
        boolean ok = false;
        String sql = "select * from roommanagement where ID=? and password=?";
        try (Connection conn = DBHelper.getConn();
             ResultSet re = DBHelper.executeQuery(conn, sql, Arrays.asList(Integer.parseInt(s1), s2))) {
            if (re.next()) {
                ok = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    // 修改密码
    public static int UpdatePassword(String psw, int id) {
        int re = 0;
        String sql = "update roommanagement set password=? where ID=?";
        try (Connection conn = DBHelper.getConn()) {
            re = DBHelper.executeUpdate(sql, Arrays.asList(psw, id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }

    // 分页查询
    public static List<roommanagementinfo> getStudentPage(int pageSize, int pageNum, int louDong, int unitNumber) {
        List<roommanagementinfo> list = new ArrayList<>();
        String sql = "SELECT * FROM roommanagement WHERE LouDong = ? AND UnitNumber = ? LIMIT ?, ?";
        int offset = (pageNum - 1) * pageSize;
        try (Connection conn = DBHelper.getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.println("Executing query with parameters - LouDong: " + louDong + ", UnitNumber: " + unitNumber + ", Offset: " + offset + ", PageSize: " + pageSize);
            pstmt.setInt(1, louDong);
            pstmt.setInt(2, unitNumber);
            pstmt.setInt(3, offset);
            pstmt.setInt(4, pageSize);
            try (ResultSet re = pstmt.executeQuery()) {
                while (re.next()) {
                    list.add(new roommanagementinfo(re.getInt(1), re.getInt(2), re.getInt(3), re.getInt(4), re.getDouble(5)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

//    public static List<roommanagementinfo> getStudentPage(int pageSize, int pageNum, int louDong, int unitNumber) {
//        List<roommanagementinfo> list = new ArrayList<>();
//        String sql = "SELECT * FROM roommanagement WHERE LouDong = ? AND UnitNumber = ? LIMIT ?, ?";
//        int offset = (pageNum - 1) * pageSize;
//        try (Connection conn = DBHelper.getConn();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, louDong);
//            pstmt.setInt(2, unitNumber);
//            pstmt.setInt(3, offset);
//            pstmt.setInt(4, pageSize);
//            try (ResultSet re = pstmt.executeQuery()) {
//                while (re.next()) {
//                    list.add(new roommanagementinfo(re.getInt(1), re.getInt(2), re.getInt(3), re.getInt(4), re.getDouble(5)));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    public static List<roommanagementinfo> getStudentPage(int pageSize, int pageNum, int louDong, int unitNumber) {
//        List<roommanagementinfo> list = new ArrayList<>();
//        String sql = "SELECT * FROM roommanagement WHERE LouDong = ? AND UnitNumber = ? LIMIT ?, ?";
//        int offset = (pageNum - 1) * pageSize;
//        try (Connection conn = DBHelper.getConn();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, louDong);
//            pstmt.setInt(2, unitNumber);
//            pstmt.setInt(3, offset);
//            pstmt.setInt(4, pageSize);
//            try (ResultSet re = pstmt.executeQuery()) {
//                while (re.next()) {
//                    list.add(new roommanagementinfo(re.getInt(1), re.getInt(2), re.getInt(3), re.getInt(4), re.getDouble(5)));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

    // 获取总记录数
    public static int getPageRows() {
        int totalPage = 0;
        String sql = "select count(*) from roommanagement";
        try (Connection conn = DBHelper.getConn();
             ResultSet re = DBHelper.executeQuery(conn, sql)) {
            if (re.next()) {
                totalPage = re.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalPage;
    }
}











//package Dao;
//
//import java.sql.*;
//import java.util.*;
//
//import Bean.roommanagementinfo;
//import Util.DBHelper;
//
//public class roommanagementdao {
//
//    // 查询全部的学生信息
//    public static List<roommanagementinfo> getStudentList(String sql) {
//        Connection conn = null;
//        List<roommanagementinfo> list = new ArrayList<>();
//        ResultSet re = null;
//        try {
//            conn = DBHelper.getConn();
//            re = DBHelper.executeQuery(conn, sql);
//            while (re.next()) {
//                list.add(new roommanagementinfo(re.getInt(1), re.getInt(2), re.getInt(3), re.getInt(4), re.getDouble(5)));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                DBHelper.closeResource(re, null, conn);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
//    }
//
//    public static int UpdateStudent(String sql) {
//        Connection conn = null;
//        int re = 0;
//        try {
//            conn = DBHelper.getConn();
//            re = DBHelper.executeUpdate(sql, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return re;
//    }
//
//    // 添加学生信息
//    public static int AddStudent(roommanagementinfo stu) {
//        int re = 0;
//        Connection conn = null;
//        try {
//            String sql = "insert into roommanagement values(?,?,?,?,?)";
//            List<Object> params = new ArrayList<>();
//            params.add(stu.getID());
//            params.add(stu.getLouDong());
//            params.add(stu.getUnitNumber());
//            params.add(stu.getRoomNumber());
//            params.add(stu.getBalance());
//            conn = DBHelper.getConn();
//            re = DBHelper.executeUpdate(sql, params);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                DBHelper.closeResource(null, null, conn);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return re;
//    }
//
//    // 根据id取学生信息
//    public static roommanagementinfo getStudent(int ID) {
//        Connection conn = null;
//        roommanagementinfo stu = null;
//        ResultSet re = null;
//        try {
//            String sql = "select * from roommanagement where ID=?";
//            conn = DBHelper.getConn();
//            re = DBHelper.executeQuery(conn, sql, Arrays.asList(ID));
//            if (re.next()) {
//                stu = new roommanagementinfo(re.getInt(1), re.getInt(2), re.getInt(3), re.getInt(4), re.getDouble(5));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                DBHelper.closeResource(re, null, conn);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return stu;
//    }
//
//    // 根据ID修改学生信息
//    public static int UpdateStudent(roommanagementinfo stu, int id) {
//        int re = 0;
//        Connection conn = null;
//        try {
//            String sql = "update roommanagement set LouDong=?, UnitNumber=?, RoomNumber=?, Balance=? where ID=?";
//            List<Object> params = new ArrayList<>();
//            params.add(stu.getLouDong());
//            params.add(stu.getUnitNumber());
//            params.add(stu.getRoomNumber());
//            params.add(stu.getBalance());
//            params.add(id);
//            conn = DBHelper.getConn();
//            re = DBHelper.executeUpdate(sql, params);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                DBHelper.closeResource(null, null, conn);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return re;
//    }
//
//    // 登录判断
//    public static boolean StudentLogin(String s1, String s2) {
//        String sql = "select * from roommanagement where ID=? and password=?";
//        Connection conn = null;
//        ResultSet re = null;
//        boolean ok = false;
//        try {
//            conn = DBHelper.getConn();
//            re = DBHelper.executeQuery(conn, sql, Arrays.asList(Integer.parseInt(s1), s2));
//            if (re.next()) {
//                ok = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                DBHelper.closeResource(re, null, conn);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return ok;
//    }
//
//    // 修改密码
//    public static int UpdatePassword(String psw, int id) {
//        int re = 0;
//        Connection conn = null;
//        try {
//            String sql = "update roommanagement set password=? where ID=?";
//            conn = DBHelper.getConn();
//            re = DBHelper.executeUpdate(sql, Arrays.asList(psw, id));
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                DBHelper.closeResource(null, null, conn);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return re;
//    }
//
//    // 分页查询
//    public static List<roommanagementinfo> getStudentPage(int pageSize, int pageNum, String louDong, String unitNumber) throws SQLException {
//        List<roommanagementinfo> list = new ArrayList<>();
//        ResultSet re = null;
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        int offset = (pageNum - 1) * pageSize;
//        String sql = "SELECT * FROM roommanagement WHERE LouDong = ? AND UnitNumber = ? LIMIT ?, ?";
//        try {
//            conn = DBHelper.getConn();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, louDong);
//            pstmt.setString(2, unitNumber);
//            pstmt.setInt(3, offset);
//            pstmt.setInt(4, pageSize);
//            re = pstmt.executeQuery();
//            while (re.next()) {
//                list.add(new roommanagementinfo(re.getInt(1), re.getInt(2), re.getInt(3), re.getInt(4), re.getDouble(5)));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            DBHelper.closeResource(re, pstmt, conn);
//        }
//        return list;
//    }
//
//    // 获取总记录数
//    public static int getPageRows() throws SQLException {
//        ResultSet re = null;
//        Connection conn = null;
//        int totalPage = 0;
//        String sql = "select count(*) from roommanagement";
//        try {
//            conn = DBHelper.getConn();
//            re = DBHelper.executeQuery(conn, sql);
//            if (re.next()) {
//                totalPage = re.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DBHelper.closeResource(re, null, conn);
//        }
//        return totalPage;
//    }
//}





//package Dao;
//
//import java.sql.*;
//import java.util.*;
//
//import Bean.roommanagementinfo;
//import Util.DBHelper;
//
//public class roommanagementdao {
//
//    // 查询全部的学生信息
//    public static List<roommanagementinfo> getStudentList(String sql) {
//        Connection conn = null;
//        List<roommanagementinfo> list = new ArrayList<>();
//        ResultSet re = null;
//        try {
//            conn = DBHelper.getConn();
//            re = DBHelper.executeQuery(conn, sql);
//            while (re.next()) {
//                list.add(new roommanagementinfo(re.getInt(1), re.getInt(2), re.getInt(3), re.getInt(4), re.getDouble(5)));
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        } finally {
//            try {
//				DBHelper.closeResource(re, null, conn);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }
//        return list;
//    }
//
//    public static int UpdateStudent(String sql) {
//        Connection conn = null;
//        int re = 0;
//        try {
//            conn = DBHelper.getConn();
//            re = DBHelper.executeUpdate(sql, null);
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        return re;
//    }
//
//    // 添加学生信息
//    public static int AddStudent(roommanagementinfo stu) {
//        int re = 0;
//        Connection conn = null;
//        try {
//            String sql = "insert into roommanagement values(?,?,?,?,?)";
//            List<Object> params = new ArrayList<>();
//            params.add(stu.getID());
//            params.add(stu.getLouDong());
//            params.add(stu.getUnitNumber());
//            params.add(stu.getRoomNumber());
//            params.add(stu.getBalance());
//            conn = DBHelper.getConn();
//            re = DBHelper.executeUpdate(sql, params);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//				DBHelper.closeResource(null, null, conn);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }
//        return re;
//    }
//
//    // 根据id取学生信息
//    public static roommanagementinfo getStudent(int ID) {
//        Connection conn = null;
//        roommanagementinfo stu = null;
//        ResultSet re = null;
//        try {
//            String sql = "select * from roommanagement where ID=?";
//            conn = DBHelper.getConn();
//            re = DBHelper.executeQuery(conn, sql, Arrays.asList(ID));
//            if (re.next()) {
//                stu = new roommanagementinfo(re.getInt(1), re.getInt(2), re.getInt(3), re.getInt(4), re.getDouble(5));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//				DBHelper.closeResource(re, null, conn);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }
//        return stu;
//    }
//
//    // 根据ID修改学生信息
//    public static int UpdateStudent(roommanagementinfo stu, int id) {
//        int re = 0;
//        Connection conn = null;
//        try {
//            String sql = "update roommanagement set LouDong=?, UnitNumber=?, RoomNumber=?, Balance=? where ID=?";
//            List<Object> params = new ArrayList<>();
//            params.add(stu.getLouDong());
//            params.add(stu.getUnitNumber());
//            params.add(stu.getRoomNumber());
//            params.add(stu.getBalance());
//            params.add(id);
//            conn = DBHelper.getConn();
//            re = DBHelper.executeUpdate(sql, params);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//				DBHelper.closeResource(null, null, conn);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }
//        return re;
//    }
//
//    // 登录判断
//    public static boolean StudentLogin(String s1, String s2) {
//        String sql = "select * from roommanagement where ID=? and password=?";
//        Connection conn = null;
//        ResultSet re = null;
//        boolean ok = false;
//        try {
//            conn = DBHelper.getConn();
//            re = DBHelper.executeQuery(conn, sql, Arrays.asList(Integer.parseInt(s1), s2));
//            if (re.next()) {
//                ok = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//				DBHelper.closeResource(re, null, conn);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }
//        return ok;
//    }
//
//    // 修改密码
//    public static int UpdatePassword(String psw, int id) {
//        int re = 0;
//        Connection conn = null;
//        try {
//            String sql = "update roommanagement set password=? where ID=?";
//            conn = DBHelper.getConn();
//            re = DBHelper.executeUpdate(sql, Arrays.asList(psw, id));
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//				DBHelper.closeResource(null, null, conn);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }
//        return re;
//    }
//
//    // 分页查询
//    public static List<roommanagementinfo> getStudentPage(int pageSize, int pageNum, String louDong, String unitNumber) throws SQLException {
//        List<roommanagementinfo> list = new ArrayList<>();
//        ResultSet re = null;
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        int offset = (pageNum - 1) * pageSize;
//        String sql = "SELECT * FROM roommanagement WHERE LouDong = ? AND UnitNumber = ? LIMIT ?, ?";
//        try {
//            conn = DBHelper.getConn();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, louDong);
//            pstmt.setString(2, unitNumber);
//            pstmt.setInt(3, offset);
//            pstmt.setInt(4, pageSize);
//            re = pstmt.executeQuery();
//            while (re.next()) {
//                list.add(new roommanagementinfo(re.getInt(1), re.getInt(2), re.getInt(3), re.getInt(4), re.getDouble(5)));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            DBHelper.closeResource(re, pstmt, conn);
//        }
//        return list;
//    }
//
////    public static List<roommanagementinfo> getStudentPage(int pageSize, int pageNum, String louDong, String unitNumber) throws SQLException {
////        List<roommanagementinfo> list = new ArrayList<>();
////        ResultSet re = null;
////        Connection conn = null;
////        int offset = (pageNum - 1) * pageSize;
////        String sql = "select * from roommanagement limit ?,?";
////        try {
////            conn = DBHelper.getConn();
////            re = DBHelper.executeQuery(conn, sql, Arrays.asList(offset, pageSize));
////            while (re.next()) {
////                list.add(new roommanagementinfo(re.getInt(1), re.getInt(2), re.getInt(3), re.getInt(4), re.getDouble(5)));
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            DBHelper.closeResource(re, null, conn);
////        }
////        return list;
////    }
//
//    // 获取总记录数
//    public static int getPageRows() throws SQLException {
//        ResultSet re = null;
//        Connection conn = null;
//        int totalPage = 0;
//        String sql = "select count(*) from roommanagement";
//        try {
//            conn = DBHelper.getConn();
//            re = DBHelper.executeQuery(conn, sql);
//            if (re.next()) {
//                totalPage = re.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DBHelper.closeResource(re, null, conn);
//        }
//        return totalPage;
//    }
//
////	public static List<roommanagementinfo> getSomeCoursePage(int pageSize, int pageNum, String louDong,
////			String unitNumber) {
////		// TODO Auto-generated method stub
////		return null;
////	}
//}
//
//
//
//
//
////package Dao;
////
////import java.sql.*;
////import java.util.*;
////
//////import Bean.StudentInfo;
////import Bean.roommanagement;
////import Util.DBHelper;
////
////public class StudentDao {
////	//查询全部的学生信息
////	 public static List getStudentList(String sql){
////			Connection conn=null;
////			List list=new ArrayList();
////			ResultSet re=null;
////			   try {
////				conn=DBHelper.getConn();
////				re=DBHelper.executeQuery(conn, sql,list);
////				while(re.next()){
////					list.add(new roommanagement(re.getInt(1),re.getInt(2),re.getInt(3),re.getInt(4),re.getDouble(5)));
////					
////				}
////			} catch (Exception e) {
////				// TODO: handle exception
////			}finally{
////				
////			}
////			   return list;
////		   }
////	 public static int UpdateStudent(String sql){
////		   Connection conn=null;
////		   int re=0;
////		   try {
////			   conn=DBHelper.getConn();
////				re=DBHelper.executeUpdate(sql, null);
////		} catch (Exception e) {
////			// TODO: handle exception
////		}
////		   return re;
////	   }
////	 //添加学生信息
////	 public static int AddStudent(roommanagement stu){
////		   int re=0;
////		   try{
////			   String sql="insert into roommanagement values(?,?,?,?,?)";
////			   List params= new ArrayList();
////			   params.add(stu.getID());
////			   params.add(stu.getLouDong());
////			   params.add(stu.getUnitNumber());
////			   params.add(stu.getRoomNumber());
////			   params.add(stu.getBalance());
//////			   params.add(stu.getAddress());
//////			   params.add(stu.getMajor());
//////			   params.add(stu.getClassID());
////			   re=DBHelper.executeUpdate(sql, params);
////		   }catch(Exception e){
////			   System.out.println(e.getMessage());
////		   }
////		return re;
////	   }
////	 //根据id取学生信息
////	 public static roommanagement getStudent(int id){
////		 Connection conn=null;
////		 roommanagement stu=null;
////		ResultSet re=null;
////		 try {
////			 String sql="select * from studentinfo where ID="+id+"";
////			 conn=DBHelper.getConn();
////				re=DBHelper.executeQuery(conn, sql);
////				System.out.println();
////				while(re.next()){
////					stu=new roommanagement(re.getInt(1),re.getInt(2),re.getInt(3),re.getInt(4),re.getDouble(5));
////					
////				}
////		} catch (Exception e) {
////			// TODO: handle exception
////			System.out.println(e.getMessage());
////		}
////		 finally{
//////			 DBHelper.closeResource(re, null, conn);
////		 }
////		 return stu;
////	 }
//////根据ID修改学生信息
////	 public static int UpdateStudent(roommanagement stu,int id){
////		 int re=0;
////		 try {
////			String sql="update StudentInfo set LouDong=?,UnitNumber=?,RoomNumber=?,Balance=? where ID="+id+"";
////			List params=new ArrayList();
////			   params.add(stu.getID());
////			   params.add(stu.getLouDong());
////			   params.add(stu.getUnitNumber());
////			   params.add(stu.getRoomNumber());
////			   params.add(stu.getBalance());
//////			   params.add(stu.getAddress());
//////			   params.add(stu.getMajor());
//////			   params.add(stu.getClassID());
////			re=DBHelper.executeUpdate(sql,params);
////		} catch (Exception e) {
////			// TODO: handle exception
////			System.out.println(e.getMessage());
////		}
////		 return re;
////	}
////	 // 登录判断
//////	 ???2024/06/11???
////	   public static boolean StudentLogin(String s1, String s2) {
////	    String sql = "select * from roommanagement where ID="+Integer.parseInt(s1)+" and password='"+s2+"'";
////	    Connection conn=null;
////	    ResultSet re=null;
////	    boolean ok=false;
////	    System.out.println(sql);
////	    try {
////			conn=DBHelper.getConn();
////			re=DBHelper.executeQuery(conn, sql);
////			if(re.next()) {
////				ok=true;
////			}
////			
////		} catch (Exception e) {
////			// TODO: handle exception
////		}
////	    return ok;
////	    }
////// 修改密码
////	   //???2024/06/11???
////	   public static int UpdatePassword(String psw,int id) {
////		   int re=0;
////		   try {
////			String sql="update roommanagement set password='"+psw+"' where StudentID="+id;
////			re=DBHelper.executeUpdate(sql);
////			System.out.println(sql);
////		} catch (Exception e) {
////			// TODO: handle exception
////		}
////		   return re;
////	   }
//////分页查询
////	   public static List<roommanagement> getStudentPage(int pageSize,int pageNum) throws SQLException{
////		   List<roommanagement> list=new ArrayList<roommanagement>();
////		   ResultSet re=null;
////		   Connection conn=null;
////		   int offset=(pageNum-1)*pageSize;
////		   String sql="select * from roommanagement where 1=1 limit "+offset+","+pageSize;
////		   try {
////			conn=DBHelper.getConn();
////			re=DBHelper.executeQuery(conn, sql);
////			while(re.next()) {
////				list.add(new roommanagement(re.getInt(1),re.getInt(2),re.getInt(3),re.getInt(4),re.getDouble(5)));
////			}
////		} catch (Exception e) {
////			// TODO: handle exception
////			e.printStackTrace();
////		}
////		   finally {
////			   DBHelper.closeResource(re, null, conn);
////		   }
////		   return list;
////	   }
////	   public static List<roommanagement> getStudentPage(int pageSize,int pageNum,int LouDong,int UnitNumber,int RoomNumber,double Balance) throws SQLException{
////		   List<roommanagement> list=new ArrayList<roommanagement>();
////		   ResultSet re=null;
////		   Connection conn=null;
////		   int offset=(pageNum-1)*pageSize;
////		   String sql="select * from roommanagement where 1=1";
////		   
////		   
////		   try {
////			conn=DBHelper.getConn();
////			if("null".equals(LouDong)) {
////				LouDong = null;
////			}
////
////			if("null".equals(UnitNumber)) {
////				UnitNumber = null;
////			}
////
////			if("null".equals(RoomNumber)) {
////				RoomNumber = null;
////			}
////
////			if("null".equals(Balance)) {
////				Balance = null;
////			}
////
//////			if("null".equals(StudentAddress)) {
//////			    StudentAddress = null;
//////			}
////			if(StudentName!=null&&!StudentName.equals("")) {
////				   sql+=" and name like '%"+StudentName+"%'";
////				   System.out.println(sql);
////			   }
////			   if(StudentMajor!=null&&!StudentMajor.equals(""))
////			   {
////				   sql+=" and major like '%"+StudentMajor+"%'";
////				   System.out.println(sql);
////			   }
////			   if(StudentSex!=null&&!StudentSex.equals(""))
////			   {
////				   sql+=" and sex like '%"+StudentSex+"%'";
////				   System.out.println(sql);
////			   }
////			   if(StudentClass!=null&&!StudentClass.equals(""))
////			   {
////				   sql+=" and classid like '%"+StudentClass+"%'";
////				   System.out.println(sql);
////			   }
////			   if(StudentAddress!=null&&!StudentAddress.equals(""))
////			   {
////				   sql+=" and address like '%"+StudentAddress+"%'";
////				   System.out.println(sql);
////			   }
////			   sql+=" limit "+offset+","+pageSize;
////			   System.out.println(sql);
////			re=DBHelper.executeQuery(conn, sql);
////			while(re.next()) {
////				list.add(new roommanagement(re.getInt(1),re.getInt(2),re.getInt(3),re.getInt(4),re.getDouble(5)));
////			}
////		} catch (Exception e) {
////			// TODO: handle exception
////			e.printStackTrace();
////		}
////		   finally {
////			   DBHelper.closeResource(re, null, conn);
////		   }
////		   return list;
////	   }
////	   //获取总记录数
////		public static int getPageRows() throws SQLException{
////			ResultSet re=null;
////			Connection conn=null;
////			int TotalPage = 0;
////			String sql="select count(0) from roommanagement";
////			System.out.println(sql);
////			try {
////				conn = DBHelper.getConn();
////				re = DBHelper.executeQuery(conn, sql);
////				while(re.next()){
////					TotalPage = re.getInt(1);
////				}
////			} catch (SQLException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}finally {
////				DBHelper.closeResource(re, null, conn);
////			}
////			return TotalPage;
////		}
////
////}
