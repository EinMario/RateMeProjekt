package hskl.swtp.rateme.api;

import hskl.swtp.rateme.db.UserDB;
import hskl.swtp.rateme.model.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Singleton
@Path("/user")
public class UserController {

    @Inject
    UserDB dbAccess;


    @POST()
    @Path("/{register}")
    @Produces("text/plain")
    public int createUser(@PathParam("register") String register) throws InvalidKeySpecException, NoSuchAlgorithmException {
        // 1 => Fehler
        // 2 => Benutzer vorhanden
        // 3 => Erfolg

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
            return 2;
        }else{

            String hashedPassword = getHashedPassword(password,username);

            User newUser = new User(username,mail,firstname,lastname,street,streetnr,zip,city,hashedPassword);

            int result = dbAccess.createUser(newUser);

            if(result > 0){
                return 3;
            }else {
                return 1;
            }
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
