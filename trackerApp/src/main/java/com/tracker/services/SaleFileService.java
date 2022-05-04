package com.tracker.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.tracker.models.SaleFile;
import com.tracker.repositories.SaleFileRepository;

@Service
public class SaleFileService {
	
	@Autowired
	private SaleFileRepository saleFileRepo;
	
	public List<SaleFile> allSaleFiles(){
		return saleFileRepo.findAll();
	}
	
	public List<SaleFile> allSaleFilesByDateDesc(){
		return saleFileRepo.findAllByDateDesc();
	}
	
	public List<SaleFile> allSaleFilesByUserDateDesc(Long id){
		return saleFileRepo.findAllByUserDateDesc(id);
	}
	
	public List<SaleFile> tenMostRecent(Long id){
		if(saleFileRepo.findAllByUserDateDesc(id).size()<10) {
			return saleFileRepo.findAllByUserDateDesc(id);
		}
		else {
			return saleFileRepo.findAllByUserDateDesc(id).subList(0,10);
		}
	}
	
	public List<SaleFile> twofiftyMostRecent(Long id){
		if(saleFileRepo.findAllByUserDateDesc(id).size()<250) {
			return saleFileRepo.findAllByUserDateDesc(id);
		}
		else {
			return saleFileRepo.findAllByUserDateDesc(id).subList(0,250);
		}
	}
	
	public SaleFile findById(Long id) {
		return saleFileRepo.findById(id).orElse(null);
	}
	
	public SaleFile update(SaleFile editSaleFile, BindingResult result) {
		if(result.hasErrors()) {
			return null;
		}
		return saleFileRepo.save(editSaleFile);
	}
	
	public List<SaleFile> findBetweenDates(Date d1, Date d2, Long id){
		return saleFileRepo.findAllBetweenDates(d1, d2, id);
	}
	
	public List<SaleFile> needsToBeReviewed(){
		return saleFileRepo.needsToBeReviewed();
	}
	
	public SaleFile review(SaleFile reviewFile) {
		reviewFile.setReviewComplete(true);
		return saleFileRepo.save(reviewFile);
	}
	
	public SaleFile create(SaleFile newSaleFile, BindingResult result) {
		if(result.hasErrors()) {
			return null;
		}
		saleFileRepo.save(newSaleFile);
		return null;
	}
	
	public void delete(Long id) {
		saleFileRepo.deleteById(id);
	}

}
