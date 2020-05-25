package output.load;

import java.util.ArrayList;
import java.util.List;

import output.tables.Region;
import static output.dao.RegionDao.regionDao;

import static output.transform.TransformRegionData.west;
import static output.transform.TransformRegionData.midwest;
import static output.transform.TransformRegionData.northeast;
import static output.transform.TransformRegionData.south;

public class LoadRegionData {

	public static void execute() {
		List<Region> regions = new ArrayList<Region>();
		regions.add(west);
		regions.add(midwest);
		regions.add(northeast);
		regions.add(south);
		regionDao.insertMany(regions);
	}

}