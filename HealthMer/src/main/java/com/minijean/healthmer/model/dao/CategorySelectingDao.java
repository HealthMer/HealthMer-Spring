package com.minijean.healthmer.model.dao;

import org.springframework.stereotype.Repository;

import com.minijean.healthmer.model.dto.Timer;

import java.util.List;
import java.util.Map;

@Repository
public interface CategorySelectingDao {
	List<Timer> selectTimersByUserIdAndCategory(Map<String, Object> params);
}