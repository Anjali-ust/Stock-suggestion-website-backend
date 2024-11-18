package com.ust.stock_watchlist.service;

import com.ust.stock_watchlist.model.Watchlist;
import com.ust.stock_watchlist.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.HttpHeaders;
//import org.springframework.web.client.RequestCallback;
//import org.springframework.web.client.RestTemplate;
import java.util.List;
//import java.util.Map;
import java.util.Optional;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

//    private final RestTemplate restTemplate = new RestTemplate();
//    private final String trendingApiUrl = "https://api.coingecko.com/api/v3/search/trending";
//    private final String apiToken = "CG-y1GGhURGBtELwoPE88Xk7Vvc";

    public Watchlist saveWatchlist(Watchlist watchlist){
        return watchlistRepository.save(watchlist);
    }

    public List<Watchlist> getAllWatchlist(){
        return watchlistRepository.findAll();
    }

    public Optional<Watchlist> getWatchlistById(String id){
        return watchlistRepository.findById(id);
    }

    public String deleteWatchlistById(String id){
        Optional<Watchlist> watchlist = watchlistRepository.findById(id);
        if(watchlist.isPresent()){
            watchlistRepository.deleteById(id);
            return "watchlist deleted";
        }
        else{
            return "Cannot find watchlist";
        }
    }

    public Watchlist updateWatchlist(String id, Watchlist watchlist){
        Optional<Watchlist> watchlistOptional = watchlistRepository.findById(id);
        if(watchlistOptional.isPresent()){
            Watchlist existingWatchlist = watchlistOptional.get();
            existingWatchlist.setWatchlistId(watchlist.getWatchlistId());
            existingWatchlist.setUsername(watchlist.getUsername());
            existingWatchlist.setStockId(watchlist.getStockId());
            return watchlistRepository.save(existingWatchlist);
        }
        return null;
    }
    
    public List<Watchlist> getWatchlistByUsername(String username) {
    	List<Watchlist> watchlist = watchlistRepository.findByUsername(username);
    	return watchlist;
    }
   
}
