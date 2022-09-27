package com.ironlogic.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;


@Data
public class OrderData{
    public String userid;
    public String password;
    @JsonProperty("STOCK SKU")
    public ArrayList<String> stockSKU;
    @JsonProperty("flow-through SKU")
    public ArrayList<String> flowThroghSKU;
}