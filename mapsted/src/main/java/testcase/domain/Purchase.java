package testcase.domain;


// import javax.persistence.*;
import java.util.*;
import java.lang.*;

public class Purchase{

    private Long item_id;
    private Long item_category_id;
    private double cost;

    public Long getitem_id() { 
        return this.item_id; 
    }

    public void setitem_id(Long item_id) { 
        this.item_id = item_id; 
    }
    
    public Long getitem_category_id() { 
        return this.item_category_id; 
    }

    public void setitem_category_id(Long item_category_id) { 
        this.item_category_id = item_category_id; 
    }

    public double getcost() { 
        return this.cost; 
    }

    public void setcost(double cost) { 
        this.cost = cost; 
    }
}