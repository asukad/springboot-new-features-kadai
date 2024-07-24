package com.example.samuraitravel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.samuraitravel.entity.Favorite;
import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.User;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
	
	List<Favorite> findByHouse(House house);
	Page<Favorite> findByUser(User user, Pageable pageable);
	boolean existsByHouseIdAndUserId(Integer houseId, Integer userId);
	
	@Transactional
	void deleteByHouseIdAndUserId(Integer houseId, Integer userId);	
	
	public Page<Favorite> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
	
	
}