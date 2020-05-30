package hskl.swtp.rateme.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hskl.swtp.rateme.model.RatemeDbException;
import hskl.swtp.rateme.model.User;
import hskl.swtp.rateme.db.DBConnection;;

public class UserDB {
	final private String createUserSQL ="INSERT INTO rateme_user (username,E_Mail,firstname,lastname,street,streetNr,zip,city,password)"
			+ "VALUES (?,?,?,?,?,?,?,?,?)";
	final private String getUserID ="SELECT * FROM rateme_user WHERE user_id = ?";
	final private String getUserName ="SELECT * FROM rateme_user WHERE username = ?";
	final private String validatePassword ="SELECT username,password FROM rateme_user WHERE username = ?";


	private DBConnection dbConnection = DBConnection.getInstance();

	private static UserDB instance;
	
	public UserDB() {
				
	}
	
	public static UserDB getInstance() {
		if(instance == null) {
			instance = new UserDB();
		}
		
		return instance;
	}
	
	public int createUser(User user) {
		 try (Connection connection = dbConnection.getConnection(); PreparedStatement pstmt = connection.prepareStatement(createUserSQL))
	      {
			 pstmt.setString(1, user.getUsername());
			 pstmt.setString(2, user.getMail());
			 pstmt.setString(3, user.getFirstname());
			 pstmt.setString(4, user.getLastname() );
			 pstmt.setString(5, user.getStreet() );
			 pstmt.setString(6, user.getStreetNr());
			 pstmt.setString(7, user.getZip());
			 pstmt.setString(8, user.getCity());
			 pstmt.setBytes(9, user.getPassword());

	         int x = pstmt.executeUpdate();
	         
	         return x;
	      } catch (SQLException ex)
	      {
	         ex.printStackTrace();
	         throw new RatemeDbException("ERROR createUser", ex);
	      }
	}
	public User loadUser(int userId) {
		 try (Connection connection = dbConnection.getConnection(); PreparedStatement pstmt = connection.prepareStatement(getUserID))
	      {
			 
			 pstmt.setInt(1, userId);
			 
			  try (ResultSet rs = pstmt.executeQuery())
		         {
				  	User u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
							rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
							rs.getBytes(10),rs.getTimestamp(11),rs.getTimestamp(12));
				  	return u;
		         }
	      } catch (SQLException ex)
	      {
	         ex.printStackTrace();
	         throw new RatemeDbException("ERROR loadUser", ex);
	      }
	}
	public User loadUser(String userName) {
		try (Connection connection = dbConnection.getConnection(); PreparedStatement pstmt = connection.prepareStatement(getUserName))
		{

			pstmt.setString(1, userName);

			try (ResultSet rs = pstmt.executeQuery())
			{
				User u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getBytes(10),
						rs.getTimestamp(11),rs.getTimestamp(12));
				return u;
			}
		} catch (SQLException ex)
		{
			ex.printStackTrace();
			throw new RatemeDbException("ERROR loadUser", ex);
		}
	}
	
	public Boolean validatePassword(String username, String password) {
		try (Connection connection = dbConnection.getConnection(); PreparedStatement pstmt = connection.prepareStatement(validatePassword))
		{
			pstmt.setString(1, username);
			String userDB;
			String pwDB;
			try (ResultSet rs = pstmt.executeQuery())
			{
				userDB = rs.getString(1);
				pwDB = new String(rs.getBytes(2));
			}

			if (username.contentEquals(userDB) && pwDB.contentEquals(password))
				return true;
			else
				return false;

		} catch (SQLException ex)
		{
			ex.printStackTrace();
			throw new RatemeDbException("ERROR validatePassword", ex);
		}
	}
}
