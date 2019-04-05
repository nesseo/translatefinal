package sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

/*public class TableControllerTest {

    @Test
    public void initialize() {
    }

    @Test
    public void filter() {
    }
}*/
public class TableControllerTest {
    Connection con;

    @Before
    public void before() {
        con = DBConnector.getConnection();
    }

    @After
    public void after() {
        DBConnector.closeConnection(con);
    }

    @Test
    public void closeStatementWithNullShouldNotThrow() {
        DBConnector.closeStatement(null);
    }

    @Test
    public void name() {
    }

    @Test
    public void initialize() {
    }

    @Test
    public void filter() {
    }

    @Test
    public void initialize1() {
    }

    @Test
    public void filter1() {
    }
}