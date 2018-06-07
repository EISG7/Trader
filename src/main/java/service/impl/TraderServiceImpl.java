package service.impl;

import util.AESUtil;
import util.PasswordUtil;
import util.TokenUtil;
import dao.TraderDao;
import entity.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import service.TraderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class TraderServiceImpl implements TraderService {

    @Autowired
    private TraderDao traderDao;
    @Autowired
    private HashMap<Integer, String> tokenMap;

    @Override
    public List<Trader> getAllTraders() {
        Iterable<Trader> it = traderDao.findAll();
        List<Trader> list = new ArrayList<>();
        it.forEach(list::add);
        return list;
    }

    @Override
    public String login(String name, String password) {
        Optional<Trader> ot = traderDao.findByName(name);
        if (ot.isPresent()) {
            Trader t = ot.get();
            if (PasswordUtil.checkPassword(password, t.getPassword())) {
                String token = TokenUtil.getToken(t.getId());
                tokenMap.put(t.getId(), token);
                return token;
            }
        }
        return null;
    }

    @Override
    public String register(String name, String password) {
        Trader t = new Trader(name, PasswordUtil.getEncryptedPassword(password));
        traderDao.save(t);
        String token = TokenUtil.getToken(t.getId());
        tokenMap.put(t.getId(), token);
        return token;
    }

    @Override
    public boolean checkToken(String token) {
        try {
            if (token == null) return false;
            String id = AESUtil.decode(token);
            return id != null &&
                    tokenMap.containsKey(Integer.valueOf(id)) &&
                    tokenMap.get(Integer.valueOf(id)).equals(token);
        }
        catch (Exception e) {
            return false;
        }
    }
}
