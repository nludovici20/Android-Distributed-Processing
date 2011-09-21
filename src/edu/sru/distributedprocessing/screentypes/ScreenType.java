package edu.sru.distributedprocessing.screentypes;

import edu.sru.distributedprocessing.optionslist.FieldOption;
import edu.sru.distributedprocessing.tools.Type;

public abstract class ScreenType 
{
	
	public abstract void Initialize() ;
	
	public abstract void Update();
	
	public abstract void Finalize();
	
}
