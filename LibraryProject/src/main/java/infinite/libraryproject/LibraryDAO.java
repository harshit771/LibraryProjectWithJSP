package infinite.libraryproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAO {
	
	public Books searchById(int id) throws ClassNotFoundException, SQLException {
		Connection con=ConnectionHelper.getConnection();
		String sql="select * from Books where Id=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		Books book=null;
		if(rs.next()) {
			book=new Books();
			book.setName(rs.getString("Name"));
			book.setAuthor(rs.getString("Author"));
			book.setEdition(rs.getString("Edition"));
			book.setDept(rs.getString("Dept"));
			book.setNoOfBooks(rs.getInt("TotalBooks"));
			
		}
		return book;
	}
	public String updateBook(Books book) throws ClassNotFoundException, SQLException {
		Connection con=ConnectionHelper.getConnection();
		String sql="update Books set Name=?,Author=?,Edition=?,Dept=?,TotalBooks=? where Id=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, book.getName());
		ps.setString(2, book.getAuthor());
		ps.setString(3, book.getEdition());
		ps.setString(4, book.getDept());
		ps.setInt(5, book.getNoOfBooks());
        ps.setInt(6, book.getId());
		ps.executeUpdate();
		return "Book Data Updated...";
	}
	public String addUser(String userName,String pwd) throws ClassNotFoundException, SQLException {
		Connection con=ConnectionHelper.getConnection();
		String sql="insert into LibUsers(Username,Password) values(?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, userName);
		ps.setString(2, pwd);
		ps.executeUpdate();
		return "User Added successfully";
	}
	public String addBook(Books book) throws ClassNotFoundException, SQLException {
		Connection con=ConnectionHelper.getConnection();
		String sql="insert into Books(Id,Name,Author,Edition,Dept,TotalBooks) values(?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, book.getId());
		ps.setString(2, book.getName());
		ps.setString(3, book.getAuthor());
		ps.setString(4, book.getEdition());
		ps.setString(5, book.getDept());
		ps.setInt(6, book.getNoOfBooks());
		ps.executeUpdate();
		
		return "Book Added Successfully";
	}
	 public List<TranBook> showBook(String user) throws ClassNotFoundException, SQLException{
		 Connection con=ConnectionHelper.getConnection();
		 String sql="select * from TransReturn where userName=?";
		 PreparedStatement ps=con.prepareStatement(sql);
		 ps.setString(1, user);
		 ResultSet rs=ps.executeQuery();
		 TranBook tranBook = null;
		 List<TranBook> tranBookList = new ArrayList<TranBook>();
		while(rs.next()) {
			tranBook = new TranBook();
			tranBook.setBookId(rs.getInt("BookId"));
			tranBook.setUserName(user);
			tranBook.setFromDate(rs.getDate("FromDate"));
			tranBook.setToDate(rs.getDate("Todate"));
			tranBookList.add(tranBook);
		}
		return tranBookList;	 
	 }
	 public TranBook searchTranBook(String user,int bookId) throws ClassNotFoundException, SQLException{
		 Connection con=ConnectionHelper.getConnection();
		 String sql="select * from TranBook where UserName=? and BookId=?";
		 PreparedStatement ps=con.prepareStatement(sql);
		 ps.setString(1, user);
		 ps.setInt(2, bookId);
		 ResultSet rs=ps.executeQuery();
		 TranBook tranBook=null;
		 if(rs.next()){
			 tranBook=new TranBook();
			 tranBook.setBookId(rs.getInt("BookId"));
			 tranBook.setUserName(user);
			 tranBook.setFromDate(rs.getDate("FromDate"));
		 }return tranBook;
	 }
	public String returnBooks(String user,int bookId) throws ClassNotFoundException, SQLException{
		Connection con=ConnectionHelper.getConnection();
		TranBook tranBook=searchTranBook(user, bookId);
		String sql="insert into TransReturn(userName,BookId,FromDate) values(?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, user);
		ps.setInt(2, bookId);
		ps.setDate(3, tranBook.getFromDate());
		ps.executeUpdate();
		sql="update Books set TotalBooks=TotalBooks+1 where id=?";
		ps=con.prepareStatement(sql);
		ps.setInt(1, bookId);
		ps.executeUpdate();
		sql="delete from TranBook where userName=? and BookId=?";
		ps=con.prepareStatement(sql);
		ps.setString(1, user);
		ps.setInt(2, bookId);
		ps.executeUpdate();
		return "Your Book "+bookId+" Return successfully";
	}
	public List<TranBook> issueBook(String user) throws ClassNotFoundException, SQLException{
		Connection  con=ConnectionHelper.getConnection();
		String sql="select * from TranBook where UserName=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, user);
		ResultSet rs=ps.executeQuery();
		TranBook tranBook = null;
		List<TranBook> tranBookList = new ArrayList<TranBook>();
		while(rs.next()) {
			tranBook = new TranBook();
			tranBook.setBookId(rs.getInt("BookId"));
			tranBook.setUserName(user);
			tranBook.setFromDate(rs.getDate("FromDate"));
			tranBookList.add(tranBook);
		}
		return tranBookList;
				
	}
	public String issueBook(String userName,int BookId) throws ClassNotFoundException, SQLException{
		Connection con=ConnectionHelper.getConnection();
		String sql="select count(*) cnt from TranBook where UserName=? and BookId=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, userName);
		ps.setInt(2, BookId);
		ResultSet rs=ps.executeQuery();
		rs.next();
		int count=rs.getInt("cnt");
		if(count==1){
			return "Book with id " + BookId+"already issued";
		}else{
			
		
		sql="Insert into TranBook(UserName,BookId) values(?,?)";
		 ps=con.prepareStatement(sql);
		ps.setString(1, userName);
		ps.setInt(2, BookId);
		
		ps.executeUpdate();
		sql="Update Books set TotalBooks=TotalBooks-1 where id=?";
		ps=con.prepareStatement(sql);
		ps.setInt(1, BookId);
		ps.executeUpdate();
		return "Book with Id "+BookId +" Issued Successfully";
	}}
	public List<Books> showBooks() throws ClassNotFoundException, SQLException{
		Connection con=ConnectionHelper.getConnection();
		String sql="select * from Books ";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		Books books = null;
		List<Books> booksList = new ArrayList<Books>();
		while(rs.next()) {
			books = new Books();
			books.setId(rs.getInt("id"));
			books.setName(rs.getString("name"));
			books.setAuthor(rs.getString("author"));
			books.setEdition(rs.getString("edition"));
			books.setDept(rs.getString("dept"));
			books.setNoOfBooks(rs.getInt("TotalBooks"));
			booksList.add(books);
		}
		return booksList;
	}
	public List<Books> searchBooks(String searchType,String searchValue) throws ClassNotFoundException, SQLException{
		String sql;
		boolean isValid=true;
		if(searchType.equals("id")){
			sql="select * from Books where Id=?";
		}else if(searchType.equals("bookname")){
			sql="select * from Books where Name=?";
		}else if(searchType.equals("dept")){
			
			sql="select * from Books where Dept=?";
		}else if(searchType.equals("authorname")){
			sql="select * from Books where Author=?";
		}else{
			isValid=false;
			sql="select * from Books ";
		}
		
		Connection con=ConnectionHelper.getConnection();
		PreparedStatement ps=con.prepareStatement(sql);
		if(isValid==true){
			ps.setString(1, searchValue);
		}
		ResultSet rs = ps.executeQuery();
		Books books = null;
		List<Books> booksList = new ArrayList<Books>();
		while(rs.next()) {
			books = new Books();
			books.setId(rs.getInt("id"));
			books.setName(rs.getString("name"));
			books.setAuthor(rs.getString("author"));
			books.setEdition(rs.getString("edition"));
			books.setDept(rs.getString("dept"));
			books.setNoOfBooks(rs.getInt("TotalBooks"));
			booksList.add(books);
		}
		return booksList;
	}
	public int authenticate(String user,String password,String role) throws ClassNotFoundException, SQLException{
		
		String sql = "";
		if(role.equals("user")) {
			sql="select count(*) cnt from libusers where UserName=? and Password=?";
		}else if(role.equals("admin")) {
		 sql="select count(*) cnt from libadmin where UserName=? and Password=?";
		}
		Connection con=ConnectionHelper.getConnection();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, user);
		ps.setString(2, password);
		ResultSet rs=ps.executeQuery();
		rs.next();
		int count=rs.getInt("cnt");
		return count;
	}
	

}
