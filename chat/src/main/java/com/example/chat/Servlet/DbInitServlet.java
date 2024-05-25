package com.example.chat.Servlet;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;


public class DbInitServlet extends HttpServlet {
    @Resource(name = "jdbc/chat_app")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            // 验证连接池配置
            Connection conn = dataSource.getConnection();
            if (conn != null) {
                conn.close();
            }
            ServletContext context = getServletContext();
            context.setAttribute("DBConnectionPool", dataSource);

        } catch (SQLException e) {
            throw new ServletException("Database connection problem.", e);
        }
    }
}
