package edu.sru.distributedprocessing.optionslist;

/**
 * @author Gustavo Matias
 * url: http://mylifewithandroid.blogspot.com/2010/02/expandable-lists-and-check-boxes.html
 */
public class FieldOption 
{
    public String field = null; //field name
    public boolean state = false; //checkbox state

    public FieldOption( String field,  boolean state ) 
    {
        this.field = field;
        this.state = state;
    }

    //get the name of the field
    public String getField() 
    {
	    return this.field;
    }

    //get the state of the field
    public boolean getState() 
    {
	    return state;
    }

}
