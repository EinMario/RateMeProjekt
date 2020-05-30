package hskl.swtp.rateme.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hskl.swtp.rateme.model.PoiTag;
import hskl.swtp.rateme.model.RatemeDbException;
import hskl.swtp.rateme.db.DBConnection;

public class PoiTagDB {
	private PoiTagDB() {
	}

	private static PoiTagDB instance;

	public static PoiTagDB getInstance() {
		if (instance == null) {
			instance = new PoiTagDB();
		}

		return instance;
	}

	private final String loadTagsForPoiSqlQuery = "SELECT tag, value FROM rateme_poi_tag WHERE osm_id = ?";

	private DBConnection dbConnection = DBConnection.getInstance();

	public Collection<PoiTag> loadTagsForPoi(long osmId) {
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(loadTagsForPoiSqlQuery)) {

			pstmt.setLong(1, osmId);

			try (ResultSet rs = pstmt.executeQuery()) {
				List<PoiTag> loadedPoiTags = new ArrayList<>();

				while (rs.next()) {
					PoiTag poiTag = new PoiTag(osmId, rs.getString(1), rs.getString(2));
					loadedPoiTags.add(poiTag);
				}

				return loadedPoiTags;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RatemeDbException("ERROR loadTagsForPoi", ex);
		}
	}
}
