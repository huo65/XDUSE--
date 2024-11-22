package web;

import entity.Stu;
import service.StuInfoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/StuInfoServlet")
public class StuInfoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        System.out.println("接收参数："+ studentId);
        StuInfoService service = new StuInfoService();
        Stu studentInfo = null;
        try {
            studentInfo = service.selectById(studentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher;
        if (studentInfo.getName() != null) {
            // 查询成功，将学生信息设置为request属性，转发回JSP页面显示
            request.setAttribute("studentName", studentInfo.getName());
            request.setAttribute("studentId", studentId);
            request.setAttribute("studentClassName", studentInfo.getClassname());
            System.out.println("成功查询"+ studentInfo.getName());
            // 添加更多属性以展示更多信息
            dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } else {
            // 查询失败，可以设置错误消息并转发回JSP页面显示
            request.setAttribute("errorMessage", "未找到该学号的学生信息！");
            dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
}