package output.dao;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import output.tables.Region;

import java.util.ArrayList;
import java.util.List;

import static output.dao.RegionDao.regionDao;

@Ignore
public class RegionDaoTest {

    public static List<Region> regions = new ArrayList<Region>();

    @BeforeClass
    public static void setup() {
        regions.add(new Region("West", "W"));
        regions.add(new Region("Midwest", "MW"));
        regions.add(new Region("Northeast", "NE"));
        regions.add(new Region("South", "S"));
        regionDao.deleteAll();
    }

    @AfterClass
    public static void clearTable() {
        regionDao.deleteAll();
    }

    @Test
    public void testInsertMany() {
        Assert.assertEquals(0, regionDao.count());
        Assert.assertTrue(regionDao.insertMany(regions));
        Assert.assertEquals(4, regionDao.count());
    }

    @Test
    public void testSelect(){
        Region region = regionDao.selectByName("South");
        Assert.assertEquals("South", region.name);
        Assert.assertEquals("S", region.abbreviation);
    }
    
}