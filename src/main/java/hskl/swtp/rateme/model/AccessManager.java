package hskl.swtp.rateme.model;

import hskl.swtp.rateme.db.UserDB;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

@Singleton
public class AccessManager {
    @Inject
    UserDB userDB;

    private List<User> userList = new ArrayList<>();
    private Map<UUID,String> logins = new HashMap<>();

    public AccessManager(){}

    public String getLoginName(UUID loginID)
    {
        String loginname = this.logins.get(loginID);
        if( loginname != null )
        {
            return loginname;
        }
        else
        {
            return null;
        }
    }

    public boolean isLoggedIn(UUID loginID)
    {
        return this.logins.containsKey(loginID);
    }

    public UUID login(String username){
        if( this.logins.containsValue(username) )
        {
            throw new RuntimeException("ERROR: Benutzer bereits eingeloggt");
        }

        User isInList = userDB.loadUser(username);
        if(!userList.contains(isInList)) {
            userList.add(isInList);
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
