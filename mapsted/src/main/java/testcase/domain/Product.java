package testcase.domain;


// import javax.persistence.*;
import java.util.*;
import java.lang.*;

import testcase.domain.UsageStatistics;

public class Product{

    private String manufacturer;
    private String market_name;
    private String codename;
    private String model;
    private UsageStatistics usage_statistics;

    public String getmanufacturer() { 
        return this.manufacturer; 
    }

    public void setmanufacturer(String manufacturer) { 
        this.manufacturer = manufacturer; 
    }
    
    public String getmarket_name() { 
        return this.market_name; 
    }

    public void setmarket_name(String market_name) { 
        this.market_name = market_name; 
    }

    public String getcodename() { 
        return this.codename; 
    }

    public void setcodename(String codename) { 
        this.codename = codename; 
    }

    public String getmodel() { 
        return this.model; 
    }

    public void setmodel(String model) { 
        this.model = model; 
    }

    public UsageStatistics getusage_statistics() { 
        return this.usage_statistics; 
    }

    public void setusage_statistics(UsageStatistics usage_statistics) { 
        this.usage_statistics = usage_statistics; 
    }
}