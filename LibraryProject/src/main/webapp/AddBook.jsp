<%@page import="infinite.libraryproject.LibraryDAO"%>
<%@page import="infinite.libraryproject.Books"%>
<%@page import="org.glassfish.jersey.server.internal.process.RequestProcessingContextReference"%>
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
   <form  method="get" action="AddBook.jsp" style="text-align: center;" >
    <h2>Add Book Form </h2>
     Book Id:
     <input type="number" name="id"><br/><br/>
     Book Name:
      <input type="text" name="name"><br/><br/>
     Author Name:
      <input type="text" name="author"><br/><br/>
     Edition:
      <input type="text" name="edition"><br/><br/>
     Department:
       <input type="text" name="dept"><br/><br/>
     Number of Books:
      <input type="number" name="totalbooks"><br/><br/>
     <input type="submit" value="Add">
   </form>
   <%
     if(request.getParameter("id")!=null){
    	 Books book=new Books();
    	 LibraryDAO dao=new LibraryDAO();
    	 book.setId(Integer.parseInt(request.getParameter("id")));
    	 book.setName(request.getParameter("name"));
    	 book.setAuthor(request.getParameter("author"));
    	 book.setEdition(request.getParameter("edition"));
    	 book.setDept(request.getParameter("dept"));
    	 book.setNoOfBooks(Integer.parseInt(request.getParameter("totalbooks")));
    	 dao.addBook(book);
    	 %>
    	 <jsp:forward page="ShowAllBook.jsp" />
    	 <%
    	 }%>
    
</body>
</html>