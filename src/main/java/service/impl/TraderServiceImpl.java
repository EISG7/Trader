package service.impl;

import util.AESUtil;
import util.PasswordUtil;
import util.TokenUtil;
import dao.TraderDao;
import entity.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import service.TraderService;

import java.util.*;

public class TraderServiceImpl implements TraderService {

    @Autowired
    private TraderDao traderDao;
    @Autowired
    private HashMap<String, String> tokenMap;

    @Override
    public List<Trader> getAllTraders() {
        Iterable<Trader> it = traderDao.findAll();
        List<Trader> list = new ArrayList<>();
        it.forEach(list::add);
        return list;
    }

    @Override
    public Map<String, Object> login(String name, String password) {
        Map<String, Object> result = new HashMap<>();
        Optional<Trader> ot = traderDao.findByName(name);
        if (ot.isPresent()) {
            Trader t = ot.get();
            if (PasswordUtil.checkPassword(password, t.getPassword())) {
                String token = TokenUtil.getToken(name);
                tokenMap.put(name, token);
                result.put("success", true);
                result.put("trader", name);
                result.put("company", t.getCompany());
                result.put("token", token);
                return result;
            }
        }
        result.put("success", false);
        return result;
    }

    @Override
    public Map<String, Object> register(Trader trader) {
        Map<String, Object> result = new HashMap<>();
        trader.setPassword(PasswordUtil.getEncryptedPassword(trader.getPassword()));
        traderDao.save(trader);
        String token = TokenUtil.getToken(trader.getId());
        tokenMap.put(trader.getName(), token);
        result.put("success", true);
        result.put("trader", trader.getName());
        result.put("token", token);
        return result;
    }

    @Override
    public boolean checkToken(String token) {
        try {
            if (token == null) return false;
            String name = AESUtil.decode(token);
            return name != null &&
                    tokenMap.containsKey(name) &&
                    tokenMap.get(name).equals(token);
        }
        catch (Exception e) {
            return false;
        }
    }
}
