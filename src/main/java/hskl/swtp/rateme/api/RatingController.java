package hskl.swtp.rateme.api;

import hskl.swtp.rateme.db.PoiDB;
import hskl.swtp.rateme.db.PoiTagDB;
import hskl.swtp.rateme.db.RatingDB;
import hskl.swtp.rateme.db.UserDB;
import hskl.swtp.rateme.model.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/rating")
@Singleton
public class RatingController {
    @Inject
    PoiDB poiDB;

    @Inject
    RatingDB ratingDB;

    @Inject
    UserDB userDB;

    @Inject
    PoiTagDB poiTagDB;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{marker}")
    public Response create(CreateRating rating,@PathParam("marker")String pos){

        if(rating.getTxt().contains("</script>") ||rating.getTxt().contains("<script")) return Response.status(404).build();

        String ratingType = getType(rating.getStars());

        //Osm Id
        Long osmId = getOsmIDFromLatLon(pos);

        if(osmId == null) return Response.status(404).build();

        //UserId
        User author = userDB.loadUser(rating.getUser());

        if(rating.getPic() != null && rating.getPic().equals("null"))
            rating.setPic("");


        Rating finalRating = new Rating(author.getId(),osmId,ratingType,rating.getStars(),rating.getTxt(),rating.getPic());

        if(ratingDB.createRating(finalRating) == 1 ){
            return Response.status(200).build();
        }else{
            return Response.status(404).build();
        }
    }

    @GET
    @Path("/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserRatings(@PathParam("user")String username){
        //User Id
        User author = userDB.loadUser(username);

        Collection<Rating> ratings = ratingDB.loadRatingsForUser(author.getId());
        for(Rating e : ratings){
            Collection<PoiTag> poiTags = poiTagDB.loadTagsForPoi(e.getOsmId());
            for(PoiTag f : poiTags){
                if(f.getTag().equals("name")){
                    e.setOsmName(f.getValue());
                }
            }
        }
        return Response.status(200).entity(ratings).build();
    }

    @GET
    @Path("/poi/{markerPOS}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPoiRatings(@PathParam("markerPOS") String markerPOS){
        Long osmID = getOsmIDFromLatLon(markerPOS);
        if(osmID == null)
            return Response.status(404).build();

        Collection<Rating> ratings = ratingDB.loadRatingsForPoi(osmID);

        for (Rating e : ratings){
            User u = userDB.loadUser(e.getUserId());
            e.setCreatorName(u.getUsername());
        }

        return Response.status(200).entity(ratings).build();
    }

    //hilfsmethode pos -> osmId
    private Long getOsmIDFromLatLon(String markerPOS) {
        String help = markerPOS.replace("{\"lat\":","");
        String help2 = help.replace("\"lng\":","");
        String help3 = help2.replace("}","");
        String[] position = help3.split(",");

        double lat = Double.parseDouble(position[0]);
        double lon = Double.parseDouble(position[1]);
        //OsmID
        Long osmId = null;
        Collection<Poi> allPois = poiDB.loadPois();

        for(Poi p : allPois){
            if(p.getLat().equals(lat) && p.getLon().equals(lon)){
                osmId = p.getOsmId();
            }
        }

        return osmId;
    }

    private String getType(int ratingStars) {
        String ratingType;
        switch (ratingStars){
            case 1:
                ratingType = "Sucks big time";
                break;
            case 2:
                ratingType ="Kinda bad";
                break;
            case 4:
                ratingType = "Pretty good";
                break;
            case 5:
                ratingType ="Rocks!";
                break;
            default:
                ratingType ="Meh";
        }
        return ratingType;
    }
}
