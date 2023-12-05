package com.ghar.ghar.service;

import com.ghar.ghar.entity.Favorite;
import com.ghar.ghar.entity.Property;
import com.ghar.ghar.entity.User;
import com.ghar.ghar.exception.CustomException;
import com.ghar.ghar.repository.FavoriteRepository;
import com.ghar.ghar.repository.PropertyRepository;
import com.ghar.ghar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    public List<Favorite> getUserFavorites(Long userId) {
        if(userRepository.existsById(userId)){
            return favoriteRepository.findByUserId(userId);
        }else{
            throw new CustomException("User not found with ID: "+userId);
        }
    }

    public Favorite addPropertyToFavorites(Long userId, Long propertyId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found with ID: " + userId));

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new CustomException("Property not found with ID: " + propertyId));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProperty(property);
        favorite.setDateAdded(new Date());
        return favoriteRepository.save(favorite);
    }

    public void removePropertyFromFavorites(Long userId, Long propertyId) {
        Favorite favorite = favoriteRepository.findByUserIdAndPropertyId(userId, propertyId)
                .orElseThrow(() -> new CustomException("Favorite not found"));

        favoriteRepository.delete(favorite);
    }
}
