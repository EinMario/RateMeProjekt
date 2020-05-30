package hskl.swtp.rateme.model;

import java.sql.Timestamp;
import java.util.Date;

public class Rating {

	private int ratingId;
	private int userId;
	private long osmId;
	private String ratingType;
	private int grade;
	private String txt;
	private String imagePath;
	private Timestamp create;
	private Timestamp modify;

	public Rating(int ratingId, int userId, long osmId, String ratingType, int grade, String txt, String imagePath) {
		super();
		this.ratingId = ratingId;
		this.userId = userId;
		this.osmId = osmId;
		this.ratingType = ratingType;
		this.grade = grade;
		this.txt = txt;
		this.imagePath = imagePath;

		java.util.Date d = new Date();
		Timestamp s = new Timestamp(d.getTime());

		this.setCreate(s);
		this.setModify(s);
	}
	
	public Rating(int ratingId, int userId, long osmId, String ratingType, int grade, String txt, String imagePath,
				  Timestamp create, Timestamp modify) {
		super();
		this.ratingId = ratingId;
		this.userId = userId;
		this.osmId = osmId;
		this.ratingType = ratingType;
		this.grade = grade;
		this.txt = txt;
		this.imagePath = imagePath;
		this.create = create;
		this.modify = modify;
	}

	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public long getOsmId() {
		return osmId;
	}

	public void setOsmId(long osmId) {
		this.osmId = osmId;
	}

	public String getRatingType() {
		return ratingType;
	}

	public void setRatingType(String ratingType) {
		this.ratingType = ratingType;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Timestamp getCreate() {
		return create;
	}

	public void setCreate(Timestamp create) {
		this.create = create;
	}

	public Timestamp getModify() {
		return modify;
	}

	public void setModify(Timestamp modify) {
		this.modify = modify;
	}

	@Override
	public String toString() {
		return "Rating{" +
				"ratingId=" + ratingId +
				", userId=" + userId +
				", osmId=" + osmId +
				", ratingType='" + ratingType + '\'' +
				", grade=" + grade +
				", txt='" + txt + '\'' +
				", imagePath='" + imagePath + '\'' +
				", create=" + create +
				", modify=" + modify +
				'}';
	}
}
