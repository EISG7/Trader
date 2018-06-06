package dao;

import entity.Trader;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TraderDao extends CrudRepository<Trader, Integer> {
    Optional<Trader> findByName(String name);
}
