

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
/**
 * Servlet implementation class FeedbackListServlet
 */
@WebServlet("/FeedbackListServlet")
public class FeedbackListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getServletPath();
		 try {
		 switch (action) {
		 case "/FeedbackListServlet/delete":
		 deleteFeedback(request, response);
		 break;
		 case "/FeedbackListServlet/edit":
		 showEditForm(request, response);
		 break;
		 case "/FeedbackListServlet/update": 
		 updateFeedback(request, response);
		 break;
		 case "/FeedbackListServlet/dashboard":
		 listFeedback(request, response);
		 break;
		 }
		 } catch (SQLException ex) {
		 throw new ServletException(ex);
		 }
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	 private String jdbcURL = "jdbc:mysql://localhost:3306/feedbacklistlatest";
	 private String jdbcUsername = "root";
	 private String jdbcPassword = "password";
	 
	 private static final String INSERT_FEEDBACKS_SQL = "INSERT INTO FeedbackListLatest" + " (question1, question2, question3, question4) VALUES " + " (?, ?, ?);";
	private static final String SELECT_FEEDBACK_BY_ID = "select question1,question2,question3,question4 from FeedbackListLatest where question1 =?";
	private static final String SELECT_ALL_FEEDBACK = "SELECT * FROM `feedbacklistlatest` ";
	private static final String DELETE_FEEDBACK_SQL = "delete from FeedbackListLatest where question1 = ?;";
	private static final String UPDATE_FEEDBACKS_SQL = "update FeedbackListLatest set question1 = ?,question2= ?,question3 =?,question4 =? where question1 = ?;";
	
	 protected Connection getConnection() {
		 Connection connection = null;
		 try {
		 Class.forName("com.mysql.jdbc.Driver");
		 connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		 } catch (SQLException e) {
		 e.printStackTrace();
		 } catch (ClassNotFoundException e) {
		 e.printStackTrace();
		 }
		 return connection;
		 }
	private void listFeedback(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException
			{
			List <Feedback> feedbacks = new ArrayList <>();
			 try (Connection connection = getConnection();
			 // Step 5.1: Create a statement using connection object
			 PreparedStatement preparedStatement =
			connection.prepareStatement(SELECT_ALL_FEEDBACK);) {
			 // Step 5.2: Execute the query or update query
			 ResultSet rs = preparedStatement.executeQuery();
			 // Step 5.3: Process the ResultSet object.
			 while (rs.next()) {
			 String question1 = rs.getString("question1");
			 String question2 = rs.getString("question2");
			 String question3 = rs.getString("question3");
			 String question4 = rs.getString("question4");
			 feedbacks.add(new Feedback(question1, question2, question3, question4));
			 }
			 } catch (SQLException e) {
			 System.out.println(e.getMessage());
			 }
			// Step 5.4: Set the users list into the listUsers attribute to be pass to the userManagement.jsp
			request.setAttribute("listFeedback", feedbacks);
			request.getRequestDispatcher("/feedbackManagement.jsp").forward(request, response);
			}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
			//get parameter passed in the URL
			String question1 = request.getParameter("question1");
			Feedback existingFeedback = new Feedback("", "", "", "");
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement =
			connection.prepareStatement(SELECT_FEEDBACK_BY_ID);) {
			preparedStatement.setString(1, question1);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object
			while (rs.next()) {
			question1 = rs.getString("question1");
			String question2 = rs.getString("question2");
			String question3 = rs.getString("question3");
			String question4 = rs.getString("question4");
			existingFeedback = new Feedback (question1, question2, question3, question4);
			}
			} catch (SQLException e) {
			System.out.println(e.getMessage());
			}
			//Step 5: Set existingUser to request and serve up the userEdit form
			request.setAttribute("feedback", existingFeedback);
			request.getRequestDispatcher("/feedbackEdit.jsp").forward(request, response);
			}
	
	private void updateFeedback(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
			//Step 1: Retrieve value from the request
			String oriquestion1 = request.getParameter("oriquestion1");
			 String question1 = request.getParameter("question1");
			 String question2 = request.getParameter("question2");
			 String question3 = request.getParameter("question3");
			 String question4 = request.getParameter("question4");

			 //Step 2: Attempt connection with database and execute update user SQL query
			 try (Connection connection = getConnection(); PreparedStatement statement =
			connection.prepareStatement(UPDATE_FEEDBACKS_SQL);) {
			 statement.setString(1, question1);
			 statement.setString(2, question2);
			 statement.setString(3, question3);
			 statement.setString(4, question4);
			 statement.setString(5, oriquestion1);
			 int i = statement.executeUpdate();
			 }
			 //Step 3: redirect back to UserServlet (note: remember to change the url to your project name)
			 response.sendRedirect("http://localhost:8090/ProjectBryan/FeedbackListServlet/dashboard");
			}
	
	//method to delete user
	private void deleteFeedback(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException {
	//Step 1: Retrieve value from the request
	 String question1 = request.getParameter("question1");
	 //Step 2: Attempt connection with database and execute delete user SQL query
	 try (Connection connection = getConnection(); PreparedStatement statement =
	connection.prepareStatement(DELETE_FEEDBACK_SQL);) {
		 statement.setString(1, question1);
	 int i = statement.executeUpdate();
	 }
	 //Step 3: redirect back to UserServlet dashboard (note: remember to change the url to your project name)
	 response.sendRedirect("http://localhost:8090/ProjectBryan/FeedbackListServlet/dashboard");
	}

}
