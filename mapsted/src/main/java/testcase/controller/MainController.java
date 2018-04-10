package testcase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.lang.Object;
import java.lang.Math; 

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import testcase.domain.Building;
import testcase.domain.Product;
import testcase.domain.UsageStatistics;
import testcase.domain.SessionInfo;
import testcase.domain.Purchase;



@Controller
public class MainController {

	@GetMapping("/home")
    public String home(Model model) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Building>> buildingReference = new TypeReference<List<Building>>(){};
        TypeReference<List<Product>> productReference = new TypeReference<List<Product>>(){};

        try {
            URL urlBuilding = new URL("http://jobs.mapsted.com/api/Values/GetBuildingData"); 
            URL urlProduct = new URL("http://jobs.mapsted.com/api/Values/GetAnalyticsData");
            // InputStream inputStream = TypeReference.class.getResourceAsStream("/buildings.json");
            List<Building> buildings = mapper.readValue(urlBuilding, buildingReference);
            List<Product> products = mapper.readValue(urlProduct, productReference);

            // Total purchase costs for Samsung manufacture devices
            String manufacturer = "Samsung";
            Double purchaseCostsForManufacture = getPurchaseCostsForManufacturer(manufacturer, products);
            model.addAttribute("answer1", roundAvoid(purchaseCostsForManufacture, 2));

            // Total number of times item (item_id = 47) was purchased
            Long item = 47L;
            Long itemPurchasedTimes = getItemPurchasedTimes(item, products);
            model.addAttribute("answer2", itemPurchasedTimes);

            // Total purchase costs for item’s in the category (item_category_id = 7)
            Long item_category = 7L;
            Double purchaseCostsForItemCategory = getPurchaseCostsForItemCategory(item_category, products);
            model.addAttribute("answer3", roundAvoid(purchaseCostsForItemCategory, 2));

            // Total purchase costs in Ontario
            String state = "Ontario";
            Double purchaseCostsInState = getPurchaseCostsInState(state, buildings, products);
            model.addAttribute("answer4", roundAvoid(purchaseCostsInState, 2));

            // Total purchase costs in the United States
            String country = "United States";
            Double purchaseCostsInCountry = getPurchaseCostsInCountry(country, buildings, products);
            model.addAttribute("answer5", roundAvoid(purchaseCostsInCountry, 2));

            // Which building (name or id) has the most total purchase costs?
            Long maxPurchaseCostsBuilding = getMaxPurchaseCostsBuilding(buildings, products);
            model.addAttribute("answer6", maxPurchaseCostsBuilding);

        } catch (IOException e){
            System.out.println("Wrong Input Data: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Errors: " + e.getMessage());
        }

            return "home";
        }

    // calculate the total cost of purchases for certain manufacturer
    public Double getPurchaseCostsForManufacturer(String manufacturer, List<Product> products){
        Double totalCosts = 0.0;

        for (Product product: products){
            if (product.getmanufacturer().equals(manufacturer)){
                UsageStatistics usageStatistics = product.getusage_statistics();
                ArrayList<SessionInfo> sessionInfos = usageStatistics.getsession_infos();
                for (SessionInfo sessionInfo: sessionInfos){
                    ArrayList<Purchase> purchases = sessionInfo.getpurchases();
                    for (Purchase purchase: purchases){
                        totalCosts += purchase.getcost();
                    }
                }
            }
        }

        return totalCosts;
    }

    // calculate total number of times item (item_id) was purchased
    public Long getItemPurchasedTimes(Long item_id, List<Product> products){
        Long totalTimes = 0L;

        for (Product product: products){
            UsageStatistics usageStatistics = product.getusage_statistics();
            ArrayList<SessionInfo> sessionInfos = usageStatistics.getsession_infos();
            for (SessionInfo sessionInfo: sessionInfos){
                ArrayList<Purchase> purchases = sessionInfo.getpurchases();
                for (Purchase purchase: purchases){
                    if (purchase.getitem_id() == item_id){
                        totalTimes += 1;
                    }
                }
            }
        }

        return totalTimes;
    }

    // calculate total purchase costs for item’s in the category (item_category_id)
    public Double getPurchaseCostsForItemCategory(Long item_category_id, List<Product> products){
        Double totalCosts = 0.0;

        for (Product product: products){
            UsageStatistics usageStatistics = product.getusage_statistics();
            ArrayList<SessionInfo> sessionInfos = usageStatistics.getsession_infos();
            for (SessionInfo sessionInfo: sessionInfos){
                ArrayList<Purchase> purchases = sessionInfo.getpurchases();
                for (Purchase purchase: purchases){
                    if (purchase.getitem_category_id() == item_category_id){
                        totalCosts += purchase.getcost();
                    }
                }
            }
        }

        return totalCosts;
    }

    // calculate total purchase costs in state
    public Double getPurchaseCostsInState(String state, List<Building> buildings, List<Product> products){
        Double totalCosts = 0.0;
        ArrayList<Long> buildingIds = new ArrayList<Long>(buildings.size());

        for (Building building: buildings){
            if (building.getstate().equals(state)){
                buildingIds.add(building.getbuilding_id());
            }
        }

        for (Product product: products){
            UsageStatistics usageStatistics = product.getusage_statistics();
            ArrayList<SessionInfo> sessionInfos = usageStatistics.getsession_infos();
            for (SessionInfo sessionInfo: sessionInfos){
                if (buildingIds.contains(sessionInfo.getbuilding_id())){
                    ArrayList<Purchase> purchases = sessionInfo.getpurchases();
                    for (Purchase purchase: purchases){
                        totalCosts += purchase.getcost();
                    }
                }
            }
        }

        return totalCosts;
    }

    // calculate total purchase costs in country
    public Double getPurchaseCostsInCountry(String country, List<Building> buildings, List<Product> products){
        Double totalCosts = 0.0;
        ArrayList<Long> buildingIds = new ArrayList<Long>(buildings.size());

        for (Building building: buildings){
            if (building.getcountry().equals(country)){
                buildingIds.add(building.getbuilding_id());
            }
        }

        for (Product product: products){
            UsageStatistics usageStatistics = product.getusage_statistics();
            ArrayList<SessionInfo> sessionInfos = usageStatistics.getsession_infos();
            for (SessionInfo sessionInfo: sessionInfos){
                if (buildingIds.contains(sessionInfo.getbuilding_id())){
                    ArrayList<Purchase> purchases = sessionInfo.getpurchases();
                    for (Purchase purchase: purchases){
                        totalCosts += purchase.getcost();
                    }
                }
            }
        }

        return totalCosts;
    }

    // find the building which has the most total purchase costs
    public Long getMaxPurchaseCostsBuilding(List<Building> buildings, List<Product> products){
        Map<Long, Double> buildingsCosts = new HashMap<Long, Double>();

        for (Building building: buildings){
            buildingsCosts.put(building.getbuilding_id(), 0.0);
        }

        for (Product product: products){
            UsageStatistics usageStatistics = product.getusage_statistics();
            ArrayList<SessionInfo> sessionInfos = usageStatistics.getsession_infos();
            for (SessionInfo sessionInfo: sessionInfos){
                if (buildingsCosts.containsKey(sessionInfo.getbuilding_id())){
                    Double costs = 0.0;
                    ArrayList<Purchase> purchases = sessionInfo.getpurchases();
                    for (Purchase purchase: purchases){
                        costs += purchase.getcost();
                    }
                    buildingsCosts.put(sessionInfo.getbuilding_id(), buildingsCosts.get(sessionInfo.getbuilding_id()) + costs);   
                }
            }
        }

        Long maxBuildingId = getMaxEntryKey(buildingsCosts);
        return maxBuildingId;
    }

    public Long getMaxEntryKey(Map<Long, Double> map) {        
        Map.Entry<Long, Double> maxEntry = null;
        Double max = Collections.max(map.values());

        for(Map.Entry<Long, Double> entry : map.entrySet()) {
            Double value = entry.getValue();
            if(null != value && max == value) {
                maxEntry = entry;
            }
        }

        return maxEntry.getKey();
    }

    public static Double roundAvoid(Double value, int places) {
        Double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

}