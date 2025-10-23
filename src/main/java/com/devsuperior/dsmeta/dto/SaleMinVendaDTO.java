package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleMinVendaDTO {

    Long id;
    private Double amount;
    private String name;
    private LocalDate date;

    public SaleMinVendaDTO(
        Long id, 
        Double amount, 
        String name,
        LocalDate date
        ) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.date = date;
    }

    public SaleMinVendaDTO(Sale x) {
        //TODO Auto-generated constructor stub
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", amount='" + getAmount() + "'" +
            ", sellerName='" + getName() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }

}
