package yukkurisim;

import java.io.Serializable;

public class UniqueIdManager  implements Serializable {
	double nowid;
	
	public void UniqueIdManager()
	{
		nowid = 0;
	}
	
	public double GetNewId()
	{
		//Žb’è
		return nowid++;
	}
}
