package com.ghar.ghar.controller;

import com.ghar.ghar.entity.Favorite;
import com.ghar.ghar.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<List<Favorite>> getUserFavorites(@PathVariable Long userId) {
        List<Favorite> favorites = favoriteService.getUserFavorites(userId);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @PostMapping("/{propertyId}")
    public ResponseEntity<Favorite> addPropertyToFavorites(@PathVariable Long userId,
                                                           @PathVariable Long propertyId) {
        Favorite addedFavorite = favoriteService.addPropertyToFavorites(userId, propertyId);
        return new ResponseEntity<>(addedFavorite, HttpStatus.CREATED);
    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<Void> removePropertyFromFavorites(@PathVariable Long userId,
                                                            @PathVariable Long propertyId) {
        favoriteService.removePropertyFromFavorites(userId, propertyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
