package com.minijean.healthmer.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minijean.healthmer.model.dao.TimerDao;
import com.minijean.healthmer.model.dto.HealthCategory;
import com.minijean.healthmer.model.dto.Routine;
import com.minijean.healthmer.model.dto.SearchCondition;
import com.minijean.healthmer.model.dto.Timer;
import com.minijean.healthmer.model.dto.TimerCategory;
import com.minijean.healthmer.model.dto.TimerRequest;

@Service
public class TimerServiceImpl implements TimerService {

	private final TimerDao timerDao;
	
	public TimerServiceImpl(TimerDao timerDao) {
		this.timerDao = timerDao;
	}
	
	@Override
	public List<Timer> getTimerList() {
		return timerDao.selectAll();
	}

	@Override
	public TimerRequest getOneTimer(long id) {
		Timer timer = timerDao.selectOne(id);
		List<Routine> routines = timerDao.selectAllRoutine(id);
		List<TimerCategory> timerCategories = timerDao.selectAllCategory(id);
		
		TimerRequest timerRequest = new TimerRequest(timer, routines, timerCategories);
		return timerRequest;
	}
	
	@Override
	@Transactional
	public TimerRequest modifyOneTimer(long id, TimerRequest timerRequest) {
		
		Timer timer = timerRequest.getTimer();
		List<Routine> routines = timerRequest.getRoutines();
		List<TimerCategory> timerCategories = timerRequest.getTimerCategories();
		
		//타이머 수정
		if(timer == null) {
			throw new IllegalArgumentException("타이머가 존재하지 않습니다.");
		}
		
		Timer dbTimer = timerDao.selectOne(id);
		
		dbTimer.setTitle(timer.getTitle());
		dbTimer.setLevel(timer.getLevel());
		timerDao.updateTimer(dbTimer);
		
		//루틴 수정
		if(routines == null || routines.isEmpty()) {
			throw new IllegalArgumentException("루틴은 하나 이상 등록돼야 합니다.");
		}
		
		List<Routine> dbRoutines = timerDao.selectAllRoutine(id);
		
		for(Routine dbRoutine : dbRoutines) {//삭제
			boolean isFind = false;
			for(Routine routine : routines) {
				if(dbRoutine.getId() == routine.getId()) {
					isFind = true;
					break;
				}
			}
			if(!isFind) {
				timerDao.deleteRoutine(dbRoutine.getId());				
			}
		}
		
		for(Routine routine : routines) {//수정/등록
			boolean isFind = false;
			for(Routine dbRoutine : dbRoutines) {
				if(routine.getId() == dbRoutine.getId()) {
					isFind = true;
					timerDao.updateRoutine(routine);
					break;
				}
			}
			if(!isFind) {
				timerDao.insertRoutine(routine);
			}
		}
		
		//카테고리 수정
		if(timerCategories == null || timerCategories.isEmpty()) {
			throw new IllegalArgumentException("카테고리는 하나 이상 등록돼야 합니다.");
		}
		
		timerDao.deleteAllCategory(id);
		timerDao.insertTimerCategory(timerCategories);
		
		return timerRequest;
	}
	
	@Override
	public List<HealthCategory> getHealthCategoryList(){
		return timerDao.selectHealthCategory();
	}
	
	@Override
	public List<TimerCategory> getCategoryList(long id) {
		return timerDao.selectAllCategory(id);
	}
	
	@Override
	public List<Routine> getRoutineList(long id) {
		return timerDao.selectAllRoutine(id);
	}
	
	@Override
	@Transactional
	public Timer createTimer(TimerRequest timerRequest) {
		Timer timer = timerRequest.getTimer();
		List<Routine> routines = timerRequest.getRoutines();
		List<TimerCategory> timerCategories = timerRequest.getTimerCategories();
		
		//Timer 등록
		timerDao.insertTimer(timer);
		
		//루틴 등록 체크
		if (routines == null || routines.isEmpty()) {
	        throw new IllegalArgumentException("Timer routines cannot be empty.");
	    }
		
		//Timer 루틴 등록
		for(Routine routine : routines) {
			routine.setTimerInfoId(timer.getId());
		}
		timerDao.insertRoutines(routines);
		
		
		//Timer 카테고리 등록
		if (timerCategories != null && !timerCategories.isEmpty()) {
			for(TimerCategory timerCategory : timerCategories) {
				timerCategory.setTimerInfoId(timer.getId());
			}
			timerDao.insertTimerCategory(timerCategories);
	    }
				
		return timer;
	}

	@Override
	@Transactional
	public boolean removeTimer(long id) {
		int result = timerDao.deleteTimer(id);
		return result == 1;
	}


	@Override
	public boolean removeCategoryList(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeRoutineList(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Timer> searchTimer(SearchCondition condition) {
		return timerDao.searchTimer(condition);
	}
	

	

}
