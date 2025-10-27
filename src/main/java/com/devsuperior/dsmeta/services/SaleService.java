package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinSummaryDTO;
import com.devsuperior.dsmeta.dto.SaleMinVendaDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

    public Page<SaleMinVendaDTO> findVendasPage(String minDate, String maxDate, String name, Pageable pageable) {
		
		LocalDate min = minDate.equals("") ? null : LocalDate.parse(minDate);
        LocalDate max = maxDate.equals("") ? null : LocalDate.parse(maxDate);
        
        if (min == null) {
            min = LocalDate.now().minusYears(1L);
        }
        if (max == null) {
            max = LocalDate.now();
        }
        
        Page<Sale> page = repository.findSalesPage(min, max, name, pageable);
        return page.map(sale -> new SaleMinVendaDTO(
            sale.getId(), 
            sale.getAmount(), 
            sale.getSeller().getName(), 
            sale.getDate()
        ));
	}

    public List<SaleMinSummaryDTO> findSummin(String minDate, String maxDate) {

        LocalDate min = minDate.equals("") ? null : LocalDate.parse(minDate);
        LocalDate max = maxDate.equals("") ? null : LocalDate.parse(maxDate);

        if (min == null) {
            min = LocalDate.now().minusYears(1L);
        }
        if (max == null) {
            max = LocalDate.now();
        }

        List<Sale> page = repository.findSummin(min, max);
        return page.stream().map(sale -> new SaleMinSummaryDTO(
            sale.getSeller().getName(), 
            sale.getAmount()
        )).toList();

    }

}
