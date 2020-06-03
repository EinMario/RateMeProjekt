package hskl.swtp.rateme.api;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/zip/{zip}")
public class ZipController  {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllZip(@PathParam("zip") String zip) {
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://escher.informatik.hs-kl.de/PlzService/ort?plz=" + zip) .request(MediaType.APPLICATION_JSON) .get();
        return response;
    }
}
