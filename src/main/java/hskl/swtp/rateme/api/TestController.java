package hskl.swtp.rateme.api;

import hskl.swtp.rateme.db.PoiDB;
import hskl.swtp.rateme.model.Poi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/test")
@Singleton
public class TestController {
    @GET
    public Response test(){
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://escher.informatik.hs-kl.de/PlzService/ort?plz=66451") .request(MediaType.APPLICATION_JSON) .get();
        System.out.println(response.toString());
        return response;
    }
}
