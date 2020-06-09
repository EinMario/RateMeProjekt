package hskl.swtp.rateme.model;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Singleton
public class AccessManager {
    private Map<UUID,String> logins = new HashMap<>();

    public AccessManager(){}

    public boolean currentlyLoggedIn(UUID uuid){
        return logins.containsKey(uuid);
    }

    public UUID login(String username){
        if( this.logins.containsValue(username) )
        {
            RuntimeException exce = new RuntimeException("ERROR: Benutzer bereits eingeloggt");
            throw exce;
        }

        UUID uuid = UUID.randomUUID();
        logins.put(uuid,username);
        return uuid;
    }

    public void logout(UUID uuid){
        if(logins.containsKey(uuid)==false){
            throw new RuntimeException("ERROR: User war nicht eingeloggt");
        }

        logins.remove(uuid);
    }
}
