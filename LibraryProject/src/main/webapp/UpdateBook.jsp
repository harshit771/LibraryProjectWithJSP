<%@page import="infinite.libraryproject.Books"%>
<%@page import="infinite.libraryproject.LibraryDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="AdminMenu.jsp" />
<br/><br/>
 <%
 LibraryDAO dao=new LibraryDAO();
 int Id=Integer.parseInt(request.getParameter("Id"));
 Books book=dao.searchById(Id);
 
 %>
 <form method="get" action="UpdateBook.jsp" style="text-align:center">
  Book Id:
  <input type="text" name="Id" value=<%=Id %>><br/><br/>
  Book Name:
  <input type="text" name="Name" value="<%=book.getName() %>"><br/><br/>
  Author:
    <input type="text" name="Author" value="<%=book.getAuthor() %>"><br/><br/>
 Edition:
   <input type="text" name="Edition" value="<%=book.getEdition() %>"><br/><br/>
   Department:
     <input type="text" name="Dept" value="<%=book.getDept() %>"><br/><br/>
     No of Books:
       <input type="text" name="TotalBooks" value="<%=book.getNoOfBooks() %>"><br/><br/>
       <input type="submit" value="Update">
 </form>
 <%
 if(request.getParameter("Id")!=null && request.getParameter("TotalBooks")!=null){
	 Books bookNew=new Books();
	 bookNew.setId(Integer.parseInt(request.getParameter("Id")));
	 bookNew.setName(request.getParameter("Name"));
	 bookNew.setAuthor(request.getParameter("Author"));
	 bookNew.setEdition(request.getParameter("Edition"));
	 bookNew.setDept(request.getParameter("Dept"));
	 bookNew.setNoOfBooks(Integer.parseInt(request.getParameter("TotalBooks")));
	 dao.updateBook(bookNew);
	 %>
	 <jsp:forward page="ShowAllBook.jsp" />
	 <%} %>

</body>
</html>