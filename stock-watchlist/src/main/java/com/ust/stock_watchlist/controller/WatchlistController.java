package com.ust.stock_watchlist.controller;

import com.ust.stock_watchlist.model.Watchlist;
import com.ust.stock_watchlist.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/watchlist")
public class WatchlistController {
    @Autowired
    private WatchlistService watchlistService;

    @PostMapping("/save")
    public ResponseEntity<Watchlist> saveWatchlist(@RequestBody Watchlist watchlist){
        return ResponseEntity.ok().body(watchlistService.saveWatchlist(watchlist));
    }

    @GetMapping("/get-all-watchlist")
    public ResponseEntity<List<Watchlist>> getAllWatchlist(){
        return ResponseEntity.ok().body(watchlistService.getAllWatchlist());
    }

    @GetMapping("/get-watchlist-by-id/{id}")
    public ResponseEntity<Watchlist> getWatchlistById(@PathVariable(name="id") String id){
        Optional<Watchlist> watchlistOptional = watchlistService.getWatchlistById(id);
        if(watchlistOptional.isPresent()){
            return ResponseEntity.ok().body(watchlistOptional.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete-watchlist-by-id/{id}")
    public ResponseEntity<String> deleteWatchlistById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok().body(watchlistService.deleteWatchlistById(id));
    }
    @PutMapping("/update-watchlist/{id}")
    public ResponseEntity<?> updateWatchlist(@PathVariable(name="id") String id,@RequestBody Watchlist watchlist){
        Watchlist updatedWatchlist = watchlistService.updateWatchlist(id,watchlist);
        if(updatedWatchlist == null){
            return ResponseEntity.ok().body("watchlist not found");
        }
        else{
            return ResponseEntity.ok().body(updatedWatchlist);
        }
    }

    //Endpoint to fetch stockdata from external api corresponding to a user
    @GetMapping("/get-watchlist-stock/{username}")
    public List<Map<String, Object>> getWatchlistStocksByUsername(@PathVariable String username) {
        return watchlistService.getWatchlistStocksByUsername(username);
    }
}
