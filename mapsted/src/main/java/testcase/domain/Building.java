package testcase.domain;


import javax.persistence.*;
import java.util.*;
import java.lang.*;


public class Building{

    private Long building_id;
    private String building_name;
    private String city;
    private String state;
    private String country;
    
    public Long getbuilding_id() { 
        return this.building_id; 
    }

    public void setbuilding_id(Long building_id) { 
        this.building_id = building_id; 
    }

    public String getbuilding_name() { 
        return this.building_name; 
    }

    public void setbuilding_name(String building_name) { 
        this.building_name = building_name; 
    }

    public String getcity() { 
        return this.city; 
    }
    public void setcity(String city) { 
        this.city = city; 
    }

    public String getstate() { 
        return this.state; 
    }

    public void setstate(String state) { 
        this.state = state; 
    }


    public String getcountry() { 
        return this.country; 
    }

    public void setcountry(String country) { 
        this.country = country; 
    }
}