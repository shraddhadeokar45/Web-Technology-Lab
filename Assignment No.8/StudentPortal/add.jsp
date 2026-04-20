<html>
<head>
<title>Add Student</title>

<style>
body {
    font-family: Arial;
    background: linear-gradient(135deg, #74ebd5, #9face6);
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

/* Card */
.container {
    background: white;
    padding: 30px;
    width: 350px;
    border-radius: 15px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.2);
    text-align: center;
}

/* Heading */
h2 {
    margin-bottom: 20px;
    color: #2c3e50;
}

/* Input Fields */
input {
    width: 90%;
    padding: 10px;
    margin: 8px 0;
    border-radius: 8px;
    border: 1px solid #ccc;
    transition: 0.3s;
}

input:focus {
    border-color: #3498db;
    outline: none;
    box-shadow: 0 0 5px #3498db;
}

/* Button */
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
    transition: 0.3s;
}

.btn:hover {
    background: #27ae60;
    transform: scale(1.05);
}

/* Back Link */
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

<h2>Add Student</h2>

<form action="insert.jsp" method="post">
<input type="text" name="id" placeholder="Enter ID" required>
<input type="text" name="name" placeholder="Enter Name" required>
<input type="text" name="class" placeholder="Enter Class" required>
<input type="text" name="division" placeholder="Enter Division" required>
<input type="text" name="city" placeholder="Enter City" required>

<button class="btn">Add Student</button>
</form>

<a href="student.jsp"><- Back to Home</a>

</div>

</body>
</html>