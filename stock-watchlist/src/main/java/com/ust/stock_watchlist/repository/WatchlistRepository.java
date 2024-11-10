package com.ust.stock_watchlist.repository;

import com.ust.stock_watchlist.model.Watchlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchlistRepository extends MongoRepository<Watchlist,String> {
    List<Watchlist> findByUsername(String username);
}
