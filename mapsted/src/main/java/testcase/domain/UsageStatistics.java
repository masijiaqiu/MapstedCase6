package testcase.domain;


// import javax.persistence.*;
import java.util.*;
import java.lang.*;
import testcase.domain.SessionInfo;

public class UsageStatistics{
	
    private ArrayList<SessionInfo> session_infos;

    public ArrayList<SessionInfo> getsession_infos() { 
    	return this.session_infos; 
    }

    public void setsession_infos(ArrayList<SessionInfo> session_infos) { 
    	this.session_infos = session_infos; 
    }
}