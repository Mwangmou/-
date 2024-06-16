package Dao;

import java.sql.*;
import java.util.*;

import Bean.admininfo;
import Bean.roommanagementinfo;
import Util.DBHelper;

public class admininfodao {
    // 查询全部的学生信息
    public static List<admininfo> getStudentList(String sql) {
        Connection conn = null;
        List<admininfo> list = new ArrayList<>();
        ResultSet re = null;
        try {
            conn = DBHelper.getConn();
            re = DBHelper.executeQuery(conn, sql);
            while (re.next()) {
                list.add(new admininfo(re.getInt(1), re.getInt(2), re.getInt(3)));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
				DBHelper.closeResource(re, null, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return list;
    }

    public static int UpdateStudent(String sql) {
        Connection conn = null;
        int re = 0;
        try {
            conn = DBHelper.getConn();
            re = DBHelper.executeUpdate(sql, null);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return re;
    }

    // 添加学生信息
    public static int AddStudent(admininfo stu) {
        int re = 0;
        Connection conn = null;
        try {
            String sql = "insert into admininfo values(?,?,?)";
            List<Object> params = new ArrayList<>();
            params.add(stu.getAdminID());
            params.add(stu.getAdminAccount());
            params.add(stu.getAdminPassword());
//            params.add(stu.getRoomNumber());
//            params.add(stu.getBalance());
            conn = DBHelper.getConn();
            re = DBHelper.executeUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
				DBHelper.closeResource(null, null, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return re;
    }

    // 根据id取学生信息
    public static admininfo getStudent(int id) {
        Connection conn = null;
        admininfo stu = null;
        ResultSet re = null;
        try {
            String sql = "select * from admininfo where ID=?";
            conn = DBHelper.getConn();
            re = DBHelper.executeQuery(conn, sql, Arrays.asList(id));
            if (re.next()) {
                stu = new admininfo(re.getInt(1), re.getInt(2), re.getInt(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
				DBHelper.closeResource(re, null, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return stu;
    }

    // 根据ID修改学生信息
    public static int UpdateStudent(admininfo stu, int id) {
        int re = 0;
        Connection conn = null;
        try {
            String sql = "update admininfo set AdminAccount=?, AdminPassword=?where AdminID=?";
            List<Object> params = new ArrayList<>();
            params.add(stu.getAdminID());
            params.add(stu.getAdminAccount());
            params.add(stu.getAdminPassword());
//            params.add(stu.getBalance());
            params.add(id);
            conn = DBHelper.getConn();
            re = DBHelper.executeUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
				DBHelper.closeResource(null, null, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return re;
    }

    // 登录判断
    public static boolean StudentLogin(String s1, String s2) {
        String sql = "select * from admininfo where AdminAccount=? and AdminPassword=?";
        Connection conn = null;
        ResultSet re = null;
        boolean ok = false;
        try {
            conn = DBHelper.getConn();
            re = DBHelper.executeQuery(conn, sql, Arrays.asList(Integer.parseInt(s1), s2));
            if (re.next()) {
                ok = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
				DBHelper.closeResource(re, null, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return ok;
    }

    // 修改密码
    public static int UpdatePassword(String psw, int id) {
        int re = 0;
        Connection conn = null;
        try {
            String sql = "update admininfo set AdminPassword=? where AdminID=?";
            conn = DBHelper.getConn();
            re = DBHelper.executeUpdate(sql, Arrays.asList(psw, id));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
				DBHelper.closeResource(null, null, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return re;
    }

    // 分页查询
    public static List<admininfo> getStudentPage(int pageSize, int pageNum) throws SQLException {
        List<admininfo> list = new ArrayList<>();
        ResultSet re = null;
        Connection conn = null;
        int offset = (pageNum - 1) * pageSize;
        String sql = "select * from admininfo limit ?,?";
        try {
            conn = DBHelper.getConn();
            re = DBHelper.executeQuery(conn, sql, Arrays.asList(offset, pageSize));
            while (re.next()) {
                list.add(new admininfo(re.getInt(1), re.getInt(2), re.getInt(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResource(re, null, conn);
        }
        return list;
    }

    // 获取总记录数
    public static int getPageRows() throws SQLException {
        ResultSet re = null;
        Connection conn = null;
        int totalPage = 0;
        String sql = "select count(*) from admininfo";
        try {
            conn = DBHelper.getConn();
            re = DBHelper.executeQuery(conn, sql);
            if (re.next()) {
                totalPage = re.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResource(re, null, conn);
        }
        return totalPage;
    }
}
