<%@ page import="java.sql.*" %>
<html>
<head>
<title>Student Management System</title>

<style>
body {
    font-family: Arial;
    background: linear-gradient(to right, #dfe9f3, #ffffff);
    text-align: center;
}

/* Heading */
h1 {
    color: #2c3e50;
    margin-top: 20px;
}

/* Add Button */
.top-btn {
    margin: 20px;
}

/* Table Styling */
table {
    margin: auto;
    border-collapse: collapse;
    width: 85%;
    background: white;
    box-shadow: 0 5px 15px rgba(0,0,0,0.2);
    border-radius: 10px;
    overflow: hidden;
}

th, td {
    padding: 12px;
    text-align: center;
}

th {
    background-color: #3498db;
    color: white;
}

tr:nth-child(even) {
    background-color: #f2f2f2;
}

tr:hover {
    background-color: #eaf2f8;
}

/* Buttons */
.btn {
    padding: 6px 12px;
    border: none;
    color: white;
    cursor: pointer;
    border-radius: 5px;
    transition: 0.3s;
}

.add { background-color: #2ecc71; }
.update { background-color: #f39c12; }
.delete { background-color: #e74c3c; }

.btn:hover {
    transform: scale(1.05);
    opacity: 0.9;
}

a {
    text-decoration: none;
}
</style>

</head>

<body>

<h1>Student Management System</h1>

<!-- Add Student Button -->
<a href="add.jsp">
    <button class="btn add top-btn">+ Add Student</button>
</a>

<table>
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Class</th>
    <th>Division</th>
    <th>City</th>
    <th>Actions</th>
</tr>

<%
try {
    // Load Driver
    Class.forName("com.mysql.cj.jdbc.Driver");

    // Database Connection
    Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/college_db",
        "root",
        ""
    );

    // Fetch Data
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("SELECT * FROM students_info");

    while(rs.next()) {
%>

<tr>
    <td><%= rs.getInt("stud_id") %></td>
    <td><%= rs.getString("stud_name") %></td>
    <td><%= rs.getString("class") %></td>
    <td><%= rs.getString("division") %></td>
    <td><%= rs.getString("city") %></td>

    <td>
        <!-- Update Button -->
        <a href="update.jsp?id=<%= rs.getInt("stud_id") %>">
            <button class="btn update">Update</button>
        </a>

        <!-- Delete Button -->
        <a href="delete.jsp?id=<%= rs.getInt("stud_id") %>">
            <button class="btn delete"
                onclick="return confirm('Are you sure you want to delete this record?')">
                Delete
            </button>
        </a>
    </td>
</tr>

<%
    }

    con.close();

} catch(Exception e) {
    out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
}
%>

</table>

</body>
</html>