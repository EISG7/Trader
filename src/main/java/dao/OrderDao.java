package dao;

import entity.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderDao extends CrudRepository<Orders, Integer> {
}
