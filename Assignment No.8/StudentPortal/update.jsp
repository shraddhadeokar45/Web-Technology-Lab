<%@ page import="java.sql.*" %>

<%
int id = Integer.parseInt(request.getParameter("id"));

Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/college_db",
    "root",
    ""
);

PreparedStatement ps = con.prepareStatement(
    "SELECT * FROM students_info WHERE stud_id=?"
);

ps.setInt(1, id);
ResultSet rs = ps.executeQuery();
rs.next();
%>

<html>
<head>
<title>Update Student</title>

<style>
body {
    font-family: Arial;
    background: linear-gradient(135deg, #74ebd5, #9face6);
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.container {
    background: white;
    padding: 30px;
    width: 350px;
    border-radius: 15px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.2);
    text-align: center;
}

h2 {
    margin-bottom: 20px;
    color: #2c3e50;
}

input {
    width: 90%;
    padding: 10px;
    margin: 8px 0;
    border-radius: 8px;
    border: 1px solid #ccc;
}

input:focus {
    border-color: #e67e22;
    outline: none;
    box-shadow: 0 0 5px #e67e22;
}

.btn {
    margin-top: 15px;
    padding: 10px;
    width: 100%;
    border: none;
    border-radius: 8px;
    background: #2ecc71;
    color: white;
    font-size: 16px;
    cursor: pointer;
}

.btn:hover {
    background: #2ecc71;
    transform: scale(1.05);
}

a {
    display: block;
    margin-top: 10px;
    text-decoration: none;
    color: #555;
}
</style>

</head>
<body>

<div class="container">

<h2> Update Student</h2>

<form action="updateProcess.jsp" method="post">

<input type="hidden" name="id" value="<%= id %>">

<input type="text" name="name" value="<%= rs.getString("stud_name") %>" required>
<input type="text" name="class" value="<%= rs.getString("class") %>" required>
<input type="text" name="division" value="<%= rs.getString("division") %>" required>
<input type="text" name="city" value="<%= rs.getString("city") %>" required>

<button class="btn">Update Student</button>

</form>

<a href="student.jsp"><- Back to Home</a>

</div>

</body>
</html>