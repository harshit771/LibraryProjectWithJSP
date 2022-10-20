<%@page import="infinite.libraryproject.LibraryDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="AdminMenu.jsp" />
<br/><br/>
<form method="get" action="AddUser.jsp" style="text-align:center;">
  User Name:
  <input type="text" name="userName"><br/><br/>
  Password:
  <input type="password" name="pwd"><br/><br/>
  <input type="submit" value="Add">
  

</form>
<%
 if(request.getParameter("pwd")!=null){
	 LibraryDAO dao=new LibraryDAO();
	 String user=request.getParameter("userName");
	 String passCode=request.getParameter("pwd");
	 dao.addUser(user, passCode);
 }

%>
</body>
</html>