package hskl.swtp.rateme.api;

import hskl.swtp.rateme.db.UserDB;
import hskl.swtp.rateme.model.AccessManager;
import hskl.swtp.rateme.model.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.UUID;

@Singleton
@Path("/user")
public class UserController {

    @Inject
    UserDB dbAccess;

    @Inject
    AccessManager acManager;

    @GET
    @Path("/login")
    public Response checkLogin(@CookieParam("LoginID") String loginID){
        if(acManager.isLoggedIn(UUID.fromString(loginID))){
            return Response.status(200).build();
        }else {
            return Response.status(404).build();
        }
    }

    @POST
    @Path("/login/{login}")
    //user:password
    public Response login(@PathParam("login") String login){
        List<String> existingUser = dbAccess.getAllUser();
        String[] input = login.split(":");

        System.out.println(login);

        String username = input[0];
        String password = input[1];

        if(existingUser.contains(username) && dbAccess.validatePassword(username,password)){
            UUID uuid = acManager.login(username);
            NewCookie loginCookie = new NewCookie("LoginID",uuid.toString());
            return Response.status(200).cookie(loginCookie).build();
        }else{
            return Response.status(404).build();
        }
    }

    @DELETE
    @Path("/login")
    public Response logout(@CookieParam("LoginID")String loginID){
        try{
            acManager.logout(UUID.fromString(loginID));
            return Response.status(200).cookie((NewCookie)null).build();
        }catch (Exception e){
            return Response.status(404).build();
        }
    }

    @POST()
    @Path("/register/{register}")
    public Response createUser(@PathParam("register") String register) throws InvalidKeySpecException, NoSuchAlgorithmException {

        String[] input = register.split(":");
        String firstname = input[0];
        String lastname = input[1];
        String street = input[2];
        String streetnr = input[3];
        String zip = input[4];
        String city = input[5];
        String mail = input[6];
        String username = input[7];
        String password = input[8];

        List<String> existingUser = dbAccess.getAllUser();

        if(existingUser.contains(username)){
            return Response.status(404).build();
        }else{

            String hashedPassword = getHashedPassword(password,username);

            User newUser = new User(username,mail,firstname,lastname,street,streetnr,zip,city,hashedPassword);

            dbAccess.createUser(newUser);

            return Response.status(200).build();
        }
    }


    private static String toHex(byte[] array)
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }

    private static String getHashedPassword(String password,String username) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        char[] chars = password.toCharArray();
        byte[] salt = username.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, 1000, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();

        return  toHex(salt) + ":" + toHex(hash);
    }
}
