package com.example.chat.Servlet;

import javax.persistence.Table;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/data")

public class DataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DataSource dataSource = (DataSource) getServletContext().getAttribute("DBConnectionPool");
            if (dataSource != null) {
                try (Connection conn = dataSource.getConnection()) {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM users");
                    StringBuilder json = new StringBuilder("[");
                    while (rs.next()) {
                        json.append("{\"username\":\"").append(rs.getString("username")).append("\"},");
                    }
                    if (json.length() > 1) {
                        json.setLength(json.length() - 1); // Remove the last comma
                    }
                    json.append("]");
                    rs.close();
                    stmt.close();
                    out.print(json.toString());
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    out.println("{\"error\":\"Database access error: " + e.getMessage() + "\"}");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.println("{\"error\":\"Database connection is not available.\"}");
            }
        }
    }
}
