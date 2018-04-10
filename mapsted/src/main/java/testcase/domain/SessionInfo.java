package testcase.domain;


// import javax.persistence.*;
import java.util.*;
import java.lang.*;
import testcase.domain.Purchase;

public class SessionInfo{

    private Long building_id;
    private ArrayList<Purchase> purchases;

    public Long getbuilding_id() { 
    	return this.building_id; 
    }

    public void setbuilding_id(Long building_id) { 
    	this.building_id = building_id; 
    }

  

    public ArrayList<Purchase> getpurchases() { return this.purchases; }

    public void setpurchases(ArrayList<Purchase> purchases) { this.purchases = purchases; }
}