package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.admininfodao;
//import Dao.roommanagementdao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.print(username);
        boolean ok = admininfodao.StudentLogin(username, password);
        if (ok) {
            request.getSession().setAttribute("AdminID", Integer.parseInt(username));
            request.getSession().setAttribute("isLogin", true);
            response.sendRedirect("ss.jsp");
        } else {
            out.println("<script>alert('用户名或密码错误！'); location.href='Login.jsp';</script>");
        }
    }
}
