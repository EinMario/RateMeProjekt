package hskl.swtp.rateme.api;

import hskl.swtp.rateme.db.PoiDB;
import hskl.swtp.rateme.db.UserDB;
import hskl.swtp.rateme.model.Poi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/test")
@Singleton
public class TestController {
    @Inject
    UserDB db;

    @GET
    @Produces("text/plain")
    public String test(){
        System.out.println("Test");
        return "ja";//abc+db.getAllUser().toString();
    }


}
