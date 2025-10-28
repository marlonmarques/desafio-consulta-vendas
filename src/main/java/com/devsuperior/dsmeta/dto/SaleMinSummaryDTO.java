package com.devsuperior.dsmeta.dto;

public class SaleMinSummaryDTO {

    private String sellerName;
    private Double total;

    public SaleMinSummaryDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public String getName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }


    @Override
    public String toString() {
        return "{" +
            ", sellerName='" + getName() + "'" +
            ", total='" + getTotal() + "'" +
            "}";
    }

}
