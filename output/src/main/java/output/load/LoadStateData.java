package output.load;

import static output.dao.StateDao.stateDao;

import static output.transform.TransformStateData.states;

public class LoadStateData {

	public static void execute() {
		stateDao.insertMany(states);
	}
    
}