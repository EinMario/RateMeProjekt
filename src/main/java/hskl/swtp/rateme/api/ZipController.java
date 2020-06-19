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
    public Response getAllZip(@PathParam("zip") String userInput){
        Client client = ClientBuilder.newClient();
        return  client.target("http://escher.informatik.hs-kl.de/PlzService/ort?plz="+userInput).request(MediaType.APPLICATION_JSON).get();
    }
}
