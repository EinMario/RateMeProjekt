package hskl.swtp.rateme.model;

import java.util.ArrayList;
import java.util.List;

public class Poi {
	private Long osmId;
	private Double lat;
	private Double lon;
	private String poiType;
	private List<PoiTag> poiTags;

	public Poi(Long osmId, Double lat, Double lon, String poiType) {
		super();
		this.osmId = osmId;
		this.lat = lat;
		this.lon = lon;
		this.poiType = poiType;
		this.poiTags = new ArrayList<>();
	}

	public void addTag(PoiTag poiTag) {
		poiTags.add(poiTag);
	}

	public Long getOsmId() {
		return osmId;
	}

	public Double getLat() {
		return lat;
	}

	public Double getLon() {
		return lon;
	}

	public String getPoiType() {
		return poiType;
	}

	public List<PoiTag> getPoiTags() {
		return poiTags;
	}

	@Override
	public String toString() {
		return "Poi [osmId=" + osmId + ", lat=" + lat + ", lon=" + lon + ", poiType=" + poiType + ", poiTags=" + poiTags
				+ "]";
	}

}
