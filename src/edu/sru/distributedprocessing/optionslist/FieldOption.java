package edu.sru.distributedprocessing.optionslist;

public class FieldOption {
    public String field = null;
    public boolean state = false;

    public FieldOption( String field,  boolean state ) {
        this.field = field;
        this.state = state;
    }

    public String getField() {
	    return this.field;
    }

    public boolean getState() {
	    return state;
    }

}
