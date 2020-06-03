package hskl.swtp;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 */
@Path("/hello")
@Singleton
public class HelloController {

    @GET
    @Path("/test")
    public String sayHello() {
        System.out.println("Test");
        return "Hello World";
    }


}
