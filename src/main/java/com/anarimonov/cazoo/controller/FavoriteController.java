package com.anarimonov.cazoo.controller;

import com.anarimonov.cazoo.dto.FavoriteDto;
import com.anarimonov.cazoo.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;
    @GetMapping("/{userId}")
    public HttpEntity<?> getFavorites(@PathVariable long userId) {
        return favoriteService.getFavoritesByUser(userId);
    }

    @PostMapping
    public HttpEntity<?> addToFavorites(@RequestBody FavoriteDto favoriteDto) {
        return favoriteService.addCarToFavorites(favoriteDto);
    }

    @DeleteMapping("/{favoriteId}")
    public HttpEntity<?> removeFromFavorites(@PathVariable long favoriteId) {
        return favoriteService.deleteFavorite(favoriteId);
    }
}
