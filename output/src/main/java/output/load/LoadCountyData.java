package output.load;

import static output.dao.CountyDao.countyDao;

import static output.transform.TransformCountyData.counties;

public class LoadCountyData {

	public static void execute() {
		countyDao.insertMany(counties);
	}
    
}