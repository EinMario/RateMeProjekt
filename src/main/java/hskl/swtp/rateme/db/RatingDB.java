package hskl.swtp.rateme.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


import hskl.swtp.rateme.model.RatemeDbException;
import hskl.swtp.rateme.model.Rating;

public class RatingDB {
	private DBConnection dbConnection = DBConnection.getInstance();
	final private String loadRatingSQLID = "SELECT r.rating_id, r.user_id, r.osm_id, r.rating_type, r.grade, r.txt, r.image_path, r.create_dt, r. modify_dt, u.user_id, p.osm_id FROM"
			+ " rateme_rating r JOIN rateme_user u ON r.user_id = u.user_id JOIN rateme_poi p ON r.osm_id = p.osm_id AND r.rating = ?";
	
	private static RatingDB instance;
	
	public RatingDB() {
				
	}
	
	public static RatingDB getInstance() {
		if(instance == null) {
			instance = new RatingDB();
		}
		
		return instance;
	}
	
	final private String createRatingSQL ="INSERT INTO rateme_rating (user_id,osm_id,rating_type,grade,txt,image_path)"
			+ "VALUES (?,?,?,?,?,?)";
	public int createRating(Rating rating) {
		 try (Connection connection = dbConnection.getConnection(); PreparedStatement pstmt = connection.prepareStatement(createRatingSQL))
	      {
			 pstmt.setInt(1, rating.getUserId());
			 pstmt.setLong(2, rating.getOsmId());
			 pstmt.setString(3, rating.getRatingType());
			 pstmt.setInt(4, rating.getGrade() );
			 pstmt.setString(5, rating.getTxt());
			 pstmt.setString(6, rating.getImagePath());

	         int x = pstmt.executeUpdate();
	         
	         return x;
	      } catch (SQLException ex)
	      {
	         ex.printStackTrace();
	         throw new RatemeDbException("ERROR createUser", ex);
	      }
	}
	
	public Rating loadRating(int ratingId) {
		 try (Connection connection = dbConnection.getConnection(); PreparedStatement pstmt = connection.prepareStatement(loadRatingSQLID))
	      {
	         pstmt.setInt(1, ratingId);

	         try (ResultSet rs = pstmt.executeQuery())
	         {
	            Rating loadedRating = new Rating(rs.getInt(1), rs.getInt(2),rs.getLong(3),
							   rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7),
							   rs.getTimestamp(8),rs.getTimestamp(9));

	            return loadedRating;
	         }
	      } catch (SQLException ex)
	      {
	         ex.printStackTrace();
	         throw new RatemeDbException("ERROR loadedRating", ex);
	      }
   }
	
	private final String loadRatingsPOI = "SELECT rating_id, user_id, osm_id, rating_type, grade,txt,image_path,create_dt,modify_dt FROM rateme_rating WHERE osm_id = ?";
	public Collection<Rating> loadRatingsForPoi(long osmId){
		 try (Connection connection = dbConnection.getConnection(); PreparedStatement pstmt = connection.prepareStatement(loadRatingsPOI))
	      {
			 pstmt.setLong(1, osmId);
			 
			 try(ResultSet rs = pstmt.executeQuery()){
				 Collection<Rating> result = new ArrayList<Rating>();
				 
				 Rating help = null;
				 
				 while(rs.next()) {
					 help = new Rating(rs.getInt(1),rs.getInt(2),rs.getLong(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7),
							 rs.getTimestamp(8),rs.getTimestamp(9));
					 result.add(help);
				 }
				 
				 return result;
			 }
	      } catch (SQLException ex)
	      {
		      ex.printStackTrace();
		      throw new RatemeDbException("ERROR loadRatingsForPoi", ex);
		  }
	}
	
	
	private final String loadRatingsUSER = "SELECT rating_id, user_id, osm_id, rating_type, grade,txt,image_path,create_dt,modify_dt FROM rateme_rating WHERE user_id = ?";
	public Collection<Rating> loadRatingsForUser(int userId){
		 try (Connection connection = dbConnection.getConnection(); PreparedStatement pstmt = connection.prepareStatement(loadRatingsUSER))
	      {
			 pstmt.setLong(1, userId);
			 
			 try(ResultSet rs = pstmt.executeQuery()){
				 Collection<Rating> result = new ArrayList<Rating>();
				 
				 Rating help = null;
				 
				 while(rs.next()) {
					 help = new Rating(rs.getInt(1),rs.getInt(2),rs.getLong(3),rs.getString(4),
							 rs.getInt(5),rs.getString(6),rs.getString(7),rs.getTimestamp(8),
							 rs.getTimestamp(9));
					 result.add(help);
				 }
				 
				 return result;
			 }
	      } catch (SQLException ex)
	      {
		      ex.printStackTrace();
		      throw new RatemeDbException("ERROR loadRatingsUSER", ex);
		  }
	}
}

