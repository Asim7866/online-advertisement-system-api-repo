package com.cg.onlineadvapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.onlineadvapi.domain.Advertise;
@Repository
public interface AdvertiseRepository extends JpaRepository<Advertise, Integer>{
  @Query("from Advertise where user_id=?1")
	List<Advertise> viewAdvertisementByUser(int userId);


	/**
	 * In this query we search advertise title with 2 status
	 * @param advertiseTitle
	 * @return all advertise that matches the advertise title and whose status is 2
	 */
	@Query("select a from Advertise a where a.advertiseTitle LIKE %?1% AND a.status=2")
	public List<Advertise> findAllOPENAdvertise(String advertiseTitle);
	
	/**
	 * This query is used by admin and user module both to get all 2 status advertise
	 * @return all advertise whose status is 2
	 */
	@Query("select a from Advertise a where a.status = 2")
	public List<Advertise> getAllOPENAdvertise();
	
	/**
	 * This query is used by admin and user module both to get all 1 status advertise
	 * @return all advertise whose status is 1
	 */
	@Query("select a from Advertise a where a.status = 1")
	public List<Advertise> getAllNEWAdvertise();
	
	/**
	 * This query is used by admin and user module both to get all 4 status advertise
	 * @return all advertise whose status is 4
	 */
	@Query("select a from Advertise a where a.status = 4")
	public List<Advertise> getAllREJECTEDAdvertise();
	
	/**
	 * This query is used by admin and user module both to get all 3 status advertise
	 * @return all advertise whose status is 3
	 */
	@Query("select a from Advertise a where a.status = 3")
	public List<Advertise> getAllCLOSEDAdvertise();

}
