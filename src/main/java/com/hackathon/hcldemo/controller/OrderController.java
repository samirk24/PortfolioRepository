package com.hackathon.hcldemo.controller;

import com.hackathon.hcldemo.model.Asset;
import com.hackathon.hcldemo.model.OrderDetails;
import com.hackathon.hcldemo.model.OrderRequest;
import com.hackathon.hcldemo.model.WebResponse;
import com.hackathon.hcldemo.service.OrderService;
import com.hackathon.hcldemo.service.ValidateOrder;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/portfolio")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private static final String ASSET_URI = "/getAssets";

    @Value("${application.root.uri}")
    private String appUri;

    @Autowired
    private OrderService orderService;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/addOrderEntry/v1")
    @ApiOperation(value="Add Order Entry API", nickname = "addOrderEntry", response = OrderDetails.class, httpMethod = "POST")
    /**@HystrixCommand(fallbackMethod = "addOrderEntryFallback")*/
    public ResponseEntity<WebResponse> addOrderEntry(@RequestBody OrderRequest orderRequest){
        WebResponse<OrderDetails> webResponse = new WebResponse<>();
        if(orderRequest!=null) {
            try {
                List<String> validationList = validate(orderRequest);
                if(!validationList.isEmpty()){
                    webResponse.setValidationMessage(validationList);
                    return new ResponseEntity<WebResponse>(webResponse, HttpStatus.UNPROCESSABLE_ENTITY);
                }
                OrderDetails orderDetails = orderService.addOrderEntry(orderRequest);
                webResponse.setData(orderDetails);
                log.info("Order placed successfully");
            } catch (Exception e) {
                log.error("Error while adding Order Entry: {}", e.getMessage());
                webResponse.setErrorMessage("Failed to place order");
                return new ResponseEntity<WebResponse>(webResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<WebResponse>(webResponse, HttpStatus.OK);
        }else {
            log.error("Invalid Order Request");
            webResponse.setErrorMessage("Invalid Order Request");
            return new ResponseEntity<WebResponse>(webResponse, HttpStatus.NO_CONTENT);
        }
    }

    /**
     *
     * @param request
     * @return List<String>
     */
    private List<String> validate(OrderRequest request){
        List<String> list = new ArrayList<>();

        if(request.getTransactionType()==null ||
                !(request.getTransactionType().equalsIgnoreCase("BUY")
                        || request.getTransactionType().equalsIgnoreCase("SELL"))) {
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
            List<Asset> assetList = null;
            Asset[] assetArray = restTemplate.getForObject(appUri+ASSET_URI, Asset[].class);
            if(assetArray!=null && assetArray.length>0) {
                assetList = Arrays.asList(assetArray);
            }

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

    public ResponseEntity<WebResponse> addOrderEntryFallback(){
        WebResponse<OrderDetails> webResponse = new WebResponse<>();
        webResponse.setErrorMessage("Service Failure. Please use service after sometime");
        return new ResponseEntity<WebResponse>(webResponse, HttpStatus.FORBIDDEN);
    }
}
