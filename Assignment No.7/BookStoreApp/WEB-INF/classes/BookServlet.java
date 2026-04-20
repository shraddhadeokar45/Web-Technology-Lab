import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class BookServlet extends HttpServlet {

    // 🔹 SHOW BOOKS (GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Bookstore</title>");

        // CSS
        out.println("<style>");
        out.println("body { font-family: Arial; background:#f4f6f9; text-align:center; }");
        out.println(".header { background:#2c3e50; color:white; padding:20px; font-size:28px; }");
        out.println("table { margin:auto; border-collapse: collapse; width:70%; background:white; }");
        out.println("th, td { padding:10px; border:1px solid #ddd; }");
        out.println("th { background:#3498db; color:white; }");
        out.println(".btn { padding:10px 20px; margin:15px; background:#3498db; color:white; border:none; border-radius:5px; text-decoration:none; }");
        out.println(".btn:hover { background:#2980b9; }");
        out.println("</style>");

        out.println("</head><body>");

        out.println("<div class='header'>📚 Bookstore Management System</div><br>");

        out.println("<table>");
        out.println("<tr><th>ID</th><th>Title</th><th>Author</th><th>Price</th><th>Qty</th></tr>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bookstore", "root", "");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ebookshop");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("title") + "</td>");
                out.println("<td>" + rs.getString("author") + "</td>");
                out.println("<td>" + rs.getDouble("price") + "</td>");
                out.println("<td>" + rs.getInt("qty") + "</td>");
                out.println("</tr>");
            }

            con.close();

        } catch (Exception e) {
            out.println("<p style='color:red;'>" + e + "</p>");
        }

        out.println("</table>");

        // Buttons
        out.println("<br>");
        out.println("<a href='addBook.html' class='btn'>➕ Add Book</a>");
        out.println("<a href='editBook.html' class='btn'>✏️ Edit Book</a>");
        out.println("<a href='deleteBook.html' class='btn'>❌ Delete Book</a>");

        out.println("</body></html>");
    }

    // 🔹 HANDLE ADD / UPDATE / DELETE (POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bookstore", "root", "");

            // ➕ ADD BOOK
            if ("add".equals(action)) {

                String title = request.getParameter("title");
                String author = request.getParameter("author");
                double price = Double.parseDouble(request.getParameter("price"));
                int qty = Integer.parseInt(request.getParameter("qty"));

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO ebookshop(title, author, price, qty) VALUES (?, ?, ?, ?)");

                ps.setString(1, title);
                ps.setString(2, author);
                ps.setDouble(3, price);
                ps.setInt(4, qty);

                ps.executeUpdate();
            }

            // ✏️ UPDATE BOOK
            else if ("update".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));
                String title = request.getParameter("title");
                String author = request.getParameter("author");
                double price = Double.parseDouble(request.getParameter("price"));
                int qty = Integer.parseInt(request.getParameter("qty"));

                PreparedStatement ps = con.prepareStatement(
                        "UPDATE ebookshop SET title=?, author=?, price=?, qty=? WHERE id=?");

                ps.setString(1, title);
                ps.setString(2, author);
                ps.setDouble(3, price);
                ps.setInt(4, qty);
                ps.setInt(5, id);

                ps.executeUpdate();
            }

            // ❌ DELETE BOOK
            else if ("delete".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));

                PreparedStatement ps = con.prepareStatement(
                        "DELETE FROM ebookshop WHERE id=?");

                ps.setInt(1, id);

                ps.executeUpdate();
            }

            con.close();

            // 🔁 Redirect to refresh table
            response.sendRedirect("BookServlet");

        } catch (Exception e) {
            response.getWriter().println("<h3 style='color:red;'>Error: " + e + "</h3>");
        }
    }
}