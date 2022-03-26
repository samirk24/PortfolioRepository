package com.hackathon.hcldemo.service;

import com.hackathon.hcldemo.model.Asset;
import com.hackathon.hcldemo.model.OrderRequest;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ValidateOrder {
    public static List<String> validate(OrderRequest request){
        List<String> list = new ArrayList<>();

        if(request.getTransactionType()==null ||
                !(request.getTransactionType().equals("BUY") || request.getTransactionType().equals("BUY"))) {
            list.add("Invalid Transaction Type");
        }
        if(request.getQuantity()<1){
            list.add("Invalid Quantity");
        }

        if(request.getOrderValue()<0){
            list.add("Invalid Order Value");
        }

        if(request.getSymbol()==null) {
            list.add("Invalid Security Name");
        }else{
            List<Asset> assetList = new ArrayList<>();



            if(assetList==null || assetList.isEmpty()){
                list.add("Invalid Security Name");
            }else {
                List<Asset> assetFoundList = assetList.stream()
                        .filter(asset -> asset.getSymbol().equals(request.getSymbol()))
                        .collect(Collectors.toList());
                if (assetFoundList == null || assetFoundList.isEmpty()) {
                    list.add("Invalid Security Name");
                }
            }
        }

        return list;
    }
}
