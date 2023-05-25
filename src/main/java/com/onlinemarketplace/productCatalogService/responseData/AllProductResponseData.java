package com.onlinemarketplace.productCatalogService.responseData;
import java.util.List;

public class AllProductResponseData {

    private List<ProductResponseData> productResponseDataList ;

    public List<ProductResponseData> getProductResponseDataList() {
        return productResponseDataList;
    }

    public void setProductResponseDataList(List<ProductResponseData> productResponseDataList) {
        this.productResponseDataList = productResponseDataList;
    }
}
