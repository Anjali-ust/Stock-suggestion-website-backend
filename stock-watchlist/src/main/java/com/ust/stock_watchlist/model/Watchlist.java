package com.ust.stock_watchlist.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Watchlist {
    @Id
    private String watchlistId;
    private String username;
    private String stockId;

    public Watchlist() {
    }

    public Watchlist(String watchlistId, String username, String stockId) {
        this.watchlistId = watchlistId;
        this.username = username;
        this.stockId = stockId;
    }

    public String getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(String watchlistId) {
        this.watchlistId = watchlistId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }
}
