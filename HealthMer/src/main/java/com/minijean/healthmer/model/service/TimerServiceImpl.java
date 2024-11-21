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
	public boolean removeTimer(long id) {
		int result = timerDao.deleteTimer(id);
		return result == 1;
	}

	@Override
	public int modifyTimerWithRoutines(TimerRequest timerRequest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Timer> searchTimer(SearchCondition condition) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
