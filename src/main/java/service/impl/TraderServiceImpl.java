package service.impl;

import dao.TraderDao;
import entity.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import service.TraderService;

import java.util.ArrayList;
import java.util.List;

public class TraderServiceImpl implements TraderService {

    @Autowired
    private TraderDao traderDao;

    @Override
    public List<Trader> getAllTraders() {
        Iterable<Trader> it = traderDao.findAll();
        List<Trader> list = new ArrayList<>();
        it.forEach(list::add);
        return list;
    }
}
