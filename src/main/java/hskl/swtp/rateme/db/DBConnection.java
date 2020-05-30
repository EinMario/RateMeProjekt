package hskl.swtp.rateme.db;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.naming.Context;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private DataSource datasource;


    private DBConnection() {
        Context ctxt;
        try {
            ctxt = new InitialContext();
            this.datasource = (DataSource) ctxt.lookup("jdbc/mySQL");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private static DBConnection instance;

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }


    protected Connection getConnection() throws SQLException {
        return this.datasource.getConnection();
    }
}
