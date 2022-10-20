<%@page import="infinite.libraryproject.Books"%>
<%@page import="java.util.List"%>
<%@page import="infinite.libraryproject.LibraryDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="infinite.libraryproject.ConnectionHelper"%>
<%@page import="java.sql.Connection"%>
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
  <%
  LibraryDAO dao=new LibraryDAO();
  List<Books> bookList=dao.showBooks();
  %>
  <table border="3" align="center">
  <tr>
      <th>Book Id</th>
      <th>Book Name</th>
      <th>Author</th>
      <th>Edition</th>
      <th>Department</th>
      <th>No of Books</th>
      <th>Update</th>
  </tr>
  <% for(Books book:bookList){
  %>
  <%if(book.getNoOfBooks()<=2){ %>
  <tr style="background-color:blue;">
    <td><%=book.getId() %></td>
    <td><%=book.getName() %></td>
    <td><%=book.getAuthor() %></td>
    <td><%=book.getEdition() %></td>
    <td><%=book.getDept() %></td>
    <td><%=book.getNoOfBooks() %></td>
    <td><a href=UpdateBook.jsp?Id=<%=book.getId()%>">Update</a></td>
  </tr>
  
  <%}
  else{
	  %>
	  <tr>
    <td><%=book.getId() %></td>
    <td><%=book.getName() %></td>
    <td><%=book.getAuthor() %></td>
    <td><%=book.getEdition() %></td>
    <td><%=book.getDept() %></td>
    <td><%=book.getNoOfBooks() %></td>
    <td><a href=UpdateBook.jsp?Id=<%=book.getId()%>>Update</a></td>
  </tr>
  <%}
  
  }%>
 
  </table>
</body>
</html>