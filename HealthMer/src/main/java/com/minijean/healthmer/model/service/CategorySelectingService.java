package com.minijean.healthmer.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minijean.healthmer.model.dao.CategorySelectingDao;
import com.minijean.healthmer.model.dto.Timer;

import java.util.List;
import java.util.Map;

@Service
public class CategorySelectingService {

    @Autowired
    private CategorySelectingDao categorySelectingDao;

    public List<Timer> getTimersByCategory(long userId, int categoryId) {
    	List<Timer> tm = categorySelectingDao.selectTimersByUserIdAndCategory(Map.of("userId", userId, "categoryId", categoryId));
        return tm;
    }
}