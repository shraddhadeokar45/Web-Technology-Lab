<%@ page import="java.sql.*" %>

<%
int id = Integer.parseInt(request.getParameter("id"));
String name = request.getParameter("name");
String cls = request.getParameter("class");
String div = request.getParameter("division");
String city = request.getParameter("city");

Class.forName("com.mysql.cj.jdbc.Driver");

Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/college_db",
    "root",
    ""
);

PreparedStatement ps = con.prepareStatement(
    "UPDATE students_info SET stud_name=?, class=?, division=?, city=? WHERE stud_id=?"
);

ps.setString(1, name);
ps.setString(2, cls);
ps.setString(3, div);
ps.setString(4, city);
ps.setInt(5, id);

ps.executeUpdate();

response.sendRedirect("student.jsp");
%>