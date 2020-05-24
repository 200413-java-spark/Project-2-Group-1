package output.dao;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import output.tables.State;

import java.util.ArrayList;
import java.util.List;

import static output.dao.StateDao.stateDao;

@Ignore
public class StateDaoTest {

    public static List<State> states = new ArrayList<State>();

    @BeforeClass
    public static void setup() {
        states.add(new State("New York", "NY"));
        states.add(new State("Texas", "TX"));
        states.add(new State("Hawaii", "HI"));
        stateDao.deleteAll();
    }

    @AfterClass
    public static void clearTable() {
        stateDao.deleteAll();
    }

    @Test
    public void testInsertMany() {
        Assert.assertEquals(0, stateDao.count());
        Assert.assertTrue(stateDao.insertMany(states));
        Assert.assertEquals(3, stateDao.count());
    }

    @Test
    public void testSelect(){
        State state = stateDao.selectByName("Texas");
        Assert.assertEquals("Texas", state.name);
        Assert.assertEquals("TX", state.abbreviation);
    }

}