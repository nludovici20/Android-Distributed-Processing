package edu.sru.distributedprocessing.screentypes;

import edu.sru.distributedprocessing.optionslist.FieldOption;

public abstract class ScreenType {
	
	public abstract void Initialize() ;
	
	public abstract void Update();
	
	public abstract void Finalize();
}
