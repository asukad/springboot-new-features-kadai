package com.example.samuraitravel.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.samuraitravel.entity.Favorite;
import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.repository.FavoriteRepository;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Transactional
    public void add(House house, User user) {
        Favorite favorite = new Favorite();
        favorite.setHouse(house);  // Houseをセット
        favorite.setUser(user);    // Userをセット
        favoriteRepository.save(favorite);
    }

    @Transactional
    public void delete(House house, User user) {
        favoriteRepository.deleteByHouseIdAndUserId(house.getId(), user.getId());
    }

    public boolean favoriteExists(House house, User user) {
        return favoriteRepository.existsByHouseIdAndUserId(house.getId(), user.getId());
    }
}