package com.tracker.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tracker.models.SaleFile;

@Repository
public interface SaleFileRepository extends CrudRepository<SaleFile, Long> {

	List<SaleFile> findAll();
	
	@Query(value="SELECT * FROM sale_file ORDER BY sale_date DESC", nativeQuery=true)
	List<SaleFile> findAllByDateDesc();
	
	// All files from user by date descending
	@Query(value="SELECT * FROM sale_file WHERE user_id=?1 ORDER BY sale_date DESC", nativeQuery=true)
	List<SaleFile> findAllByUserDateDesc(Long id);
	
	// Date Filter Query
	@Query(value="SELECT * FROM sale_file WHERE user_id=?3 AND sale_date BETWEEN ?1 AND ?2", nativeQuery=true)
	List<SaleFile> findAllBetweenDates(Date d1, Date d2, Long id);
	
	// Files needing to be reviewed
	@Query(value="SELECT * FROM sale_file WHERE review_complete=false ORDER BY user_id, sale_date DESC", nativeQuery=true)
	List<SaleFile> needsToBeReviewed();
	
}
