package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.roommanagementinfo;
import Dao.roommanagementdao;
import net.sf.json.JSONArray;

@WebServlet("/StuCourseServlet")
public class StuCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StuCourseServlet() {
        super();
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNum = 1;
        int pageSize = 12;
        if (request.getParameter("pageNum") != null) {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        if (request.getParameter("pageSize") != null) {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }
        int LouDong = 0;
        int UnitNumber = 0;
        try {
            LouDong = Integer.parseInt(request.getParameter("LouDong"));
            UnitNumber = Integer.parseInt(request.getParameter("UnitNumber"));
        } catch (NumberFormatException e) {
            // 处理异常，参数解析失败
            e.printStackTrace();
        }

        try {
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            List<roommanagementinfo> list = null;

            // 增加日志，帮助调试
            System.out.println("Received request with parameters - pageNum: " + pageNum + ", pageSize: " + pageSize + ", LouDong: " + LouDong + ", UnitNumber: " + UnitNumber);

            list = roommanagementdao.getStudentPage(pageSize, pageNum, LouDong, UnitNumber);
            JSONArray jsonarry = JSONArray.fromObject(list);
            pw.write(jsonarry.toString());

            // 打印返回的 JSON 数据，帮助调试
            System.out.println("Response JSON: " + jsonarry.toString());

            pw.close();
        } catch (Exception e) {
            e.printStackTrace(); // 打印异常堆栈信息，帮助调试
        }
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int pageNum = 1;
//        int pageSize = 12;
//        if (request.getParameter("pageNum") != null) {
//            pageNum = Integer.parseInt(request.getParameter("pageNum"));
//        }
//        if (request.getParameter("pageSize") != null) {
//            pageSize = Integer.parseInt(request.getParameter("pageSize"));
//        }
//        Int LouDong = request.getParameter("LouDong");
//        Int UnitNumber = request.getParameter("UnitNumber");
//
//        try {
//            response.setContentType("application/json;charset=UTF-8");
//            response.setCharacterEncoding("UTF-8");
//            PrintWriter pw = response.getWriter();
//            List<roommanagementinfo> list = null;
//
//            // 增加日志，帮助调试
//            System.out.println("Received request with parameters - pageNum: " + pageNum + ", pageSize: " + pageSize + ", LouDong: " + LouDong + ", UnitNumber: " + UnitNumber);
//
//            list = roommanagementdao.getStudentPage(pageSize, pageNum, LouDong, UnitNumber);
//            JSONArray jsonarry = JSONArray.fromObject(list);
//            pw.write(jsonarry.toString());
//
//            // 打印返回的 JSON 数据，帮助调试
//            System.out.println("Response JSON: " + jsonarry.toString());
//
//            pw.close();
//        } catch (Exception e) {
//            e.printStackTrace(); // 打印异常堆栈信息，帮助调试
//        }
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
