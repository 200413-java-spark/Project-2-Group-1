package output.dao;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import output.tables.State;

import java.util.ArrayList;
import java.util.List;

@Ignore
public class StateDaoTest {

    public static StateDao dao = new StateDao();
    public static List<State> states = new ArrayList<State>();

    @BeforeClass
    public static void setup() {
        states.add(new State("New York", "NY"));
        states.add(new State("Texas", "TX"));
        states.add(new State("Hawaii", "HI"));
        dao.deleteAll();
    }

    @AfterClass
    public static void clearTable() {
        dao.deleteAll();
    }

    @Test
    public void testInsertMany() {
        Assert.assertEquals(0, dao.count());
        Assert.assertTrue(dao.insertMany(states));
        Assert.assertEquals(3, dao.count());
    }

    @Test
    public void testSelect(){
        State state = dao.select(new State("Texas", "TX"));
        Assert.assertEquals("Texas", state.name);
        Assert.assertEquals("TX", state.abbreviation);
    }

}