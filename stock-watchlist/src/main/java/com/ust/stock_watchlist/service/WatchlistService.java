package com.ust.stock_watchlist.service;

import com.ust.stock_watchlist.model.Watchlist;
import com.ust.stock_watchlist.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String stockDataApiUrl = "https://api.stockdata.org/v1/data/quote";
    private final String apiToken = "8vyKyKlReSMrrEzF7vOv5RwZ7PgkEyLDGBIa1Pns";

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

    public Watchlist updateWatchlist(String id,Watchlist watchlist){
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

    public List<Map<String, Object>> getWatchlistStocksByUsername(String username) {
        List<Watchlist> watchlist = watchlistRepository.findByUsername(username);
        if (watchlist.isEmpty()) {
            return List.of();
        }

        // Collect stock tickers for the user's watchlist
        String tickers = watchlist.stream()
                .map(Watchlist::getStockId)
                .collect(Collectors.joining(","));

        // Fetch stock data from StockData.org API
        String url = stockDataApiUrl + "?symbols=" + tickers + "&api_token=" + apiToken;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        // Extract relevant stock data from API response
        List<Map<String, Object>> stockData = (List<Map<String, Object>>) response.get("data");
        return stockData;
    }
}
