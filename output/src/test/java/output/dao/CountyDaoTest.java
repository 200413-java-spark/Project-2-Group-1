package output.dao;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import output.tables.County;

import java.util.ArrayList;
import java.util.List;

import static output.dao.CountyDao.countyDao;

@Ignore
public class CountyDaoTest {

    public static List<County> counties = new ArrayList<County>();

    @BeforeClass
    public static void setup() {
        counties.add(new County("Autauga County", "AL"));
        counties.add(new County("Baldwin County", "AL"));
        counties.add(new County("Barbour County", "AL"));
        countyDao.deleteAll();
    }

    @AfterClass
    public static void clearTable() {
        countyDao.deleteAll();
    }

    @Test
    public void testInsertMany() {
        Assert.assertEquals(0, countyDao.count());
        Assert.assertTrue(countyDao.insertMany(counties));
        Assert.assertEquals(3, countyDao.count());
    }

    @Test
    public void testSelect(){
        County county = countyDao.selectByName("Baldwin County");
        Assert.assertEquals("Baldwin County", county.name);
        Assert.assertEquals("AL", county.state);
    }
    
}