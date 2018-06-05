package dao;

import entity.Trader;
import org.springframework.data.repository.CrudRepository;

public interface TraderDao extends CrudRepository<Trader, Integer> {
}
