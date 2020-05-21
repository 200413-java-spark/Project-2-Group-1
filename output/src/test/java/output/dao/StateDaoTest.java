package output.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import output.tables.State;

@Ignore
public class StateDaoTest {

    public static StateDao dao = new StateDao();
    public static List<State> states = new ArrayList<State>();

    @BeforeClass
    public static void setup() {
        states.add(new State(1, "NY"));
        states.add(new State(2, "TX"));
        states.add(new State(3, "HI"));
        dao.deleteAll();
    }

    @Test
    public void testInsertMany(){
        Assert.assertEquals(0, dao.count());
        Assert.assertTrue(dao.insertMany(states));
        Assert.assertEquals(3, dao.count());
    }

    @Test
    public void testSelect(){
        State state = dao.select(new State("TX"));
        Assert.assertEquals(2, state.id);
    }

    @AfterClass
    public static void clearTable() {
        dao.deleteAll();
    }

}