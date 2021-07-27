package com.caudev.roadmap.restaurant;

import com.caudev.roadmap.place.Place;
import com.caudev.roadmap.place.PlaceDto;
import com.caudev.roadmap.place.PlaceService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/restaurants")
@RequiredArgsConstructor
public class RestaurantAdminController {

    private final RestaurantService restaurantService;

    @GetMapping
    public Page<Restaurant> findAllRestaurants(Pageable pageable){
        return restaurantService.findAllRestaurants(pageable);
    }

    @GetMapping("/{restaurant_name}")
    public Page<Restaurant> findByName(Pageable pageable, @PathVariable String restaurant_name){
        return restaurantService.findRestaurantByName(restaurant_name,pageable);
    }

    @GetMapping("/{restaurant_id}")
    public ResponseEntity findById(@PathVariable Long restaurant_id){
        Restaurant restaurant;
        try{
            restaurant  = restaurantService.findRestaurantById(restaurant_id);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurant);
    }

    @DeleteMapping("/{restaurant_id}")
    public ResponseEntity deleteRestaurant (@PathVariable Long restaurant_id) {
        try{
            restaurantService.deleteRestaurant(restaurant_id);
        } catch(NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity createRestaurant (@RequestBody RestaurantDto restaurantDto){
        RestaurantDto restaurantDto1 = restaurantService.createRestaurant(restaurantDto);
        return ResponseEntity.ok(restaurantDto1);
    }

    @PutMapping("/{restaurant_id}")
    public ResponseEntity modifyRestaurant (@PathVariable Long restaurant_id, @RequestBody RestaurantDto restaurantDto){
        RestaurantDto restaurantDto1;
        try{
            restaurantDto1 = restaurantService.modifyRestaurant(restaurant_id,restaurantDto);
        } catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurantDto1);
    }
}
