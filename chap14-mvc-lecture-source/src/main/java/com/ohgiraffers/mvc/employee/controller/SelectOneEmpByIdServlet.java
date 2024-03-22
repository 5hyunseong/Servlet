package com.ohgiraffers.mvc.employee.controller;

import com.ohgiraffers.mvc.employee.model.dto.EmployeeDTO;
import com.ohgiraffers.mvc.employee.model.service.EmployeeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employee/select")
public class SelectOneEmpByIdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* 필기. 전달한 파라미터 꺼내기 */
        String empId = request.getParameter("empId");

        System.out.println("empId : " + empId);

        /* 필기. 사번을 이용해 사원 정보를 조회하는 비지니스 로직 호출 */
        EmployeeService empService = new EmployeeService();
        EmployeeDTO selectedEmp = empService.selectOneEmpById(empId);

        System.out.println("selectedEmp : " + selectedEmp);

        /* 필기. 비지니스 로직 실행 결과에 따라 뷰 연결 */
        String path = "";
        if(selectedEmp != null) {
            path = "/WEB-INF/views/employee/showEmpInfo.jsp";
            request.setAttribute("selectedEmp", selectedEmp);
        } else {
            path = "/WEB-INF/views/common/errorPage.jsp";
            request.setAttribute("message", "직원 정보 조회 실패!");
        }

        request.getRequestDispatcher(path).forward(request, response);

    }

}
