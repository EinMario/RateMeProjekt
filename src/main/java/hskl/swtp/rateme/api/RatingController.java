package hskl.swtp.rateme.api;

import hskl.swtp.rateme.db.PoiDB;
import hskl.swtp.rateme.db.RatingDB;
import hskl.swtp.rateme.db.UserDB;
import hskl.swtp.rateme.model.Poi;
import hskl.swtp.rateme.model.Rating;
import hskl.swtp.rateme.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
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

    @Path("/{stars}/{comment}/{pic}/{user}/{marker}")
    @POST
    public Response createRating(@PathParam("stars") String stars,
                                 @PathParam("comment") String comment,
                                 @PathParam("pic") String pic,
                                 @PathParam("user") String user,
                                 @PathParam("marker") String markerPOS){
        System.out.println(stars);
        System.out.println(comment);
        System.out.println(pic);
        System.out.println(user);
        System.out.println(markerPOS);

        //text
        //RatingType
        //Stars
        String ratingType;
        int ratingStars = Integer.parseInt(stars);

        ratingType = getType(ratingStars);

        String[] position = getLatLon(markerPOS);

        double lat = Double.parseDouble(position[0]);
        double lon = Double.parseDouble(position[1]);

        //UserId
        User author = userDB.loadUser(user);

        //OsmID
        Long osmId = null;
        Collection<Poi> allPois = poiDB.loadPois();

        for(Poi p : allPois){
            if(p.getLat().equals(lat) && p.getLon().equals(lon)){
                osmId = p.getOsmId();
            }
        }

        if(osmId == null) return Response.status(404).build();



        Rating rating = new Rating(author.getId(),osmId,ratingType,ratingStars,comment,"");
        System.out.println(rating.toString());
        return null;
    }

    private String[] getLatLon(String markerPOS) {
        String help = markerPOS.replace("{\"lat\":","");
        String help2 = help.replace("\"lng\":","");
        String help3 = help2.replace("}","");

        return help3.split(",");
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
