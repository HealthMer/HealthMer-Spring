package com.minijean.healthmer.model.service;

import java.util.List;

import com.minijean.healthmer.model.dto.HealthCategory;
import com.minijean.healthmer.model.dto.Routine;
import com.minijean.healthmer.model.dto.SearchCondition;
import com.minijean.healthmer.model.dto.Timer;
import com.minijean.healthmer.model.dto.TimerCategory;
import com.minijean.healthmer.model.dto.TimerRequest;

public interface TimerService {
	
	/** 전체 타이머 조회 */
	public List<Timer> getTimerList(long userId);
	
	/** 타이머 한 개 조회 (카테고리와 하위 루틴도 함께 조회함) */
	public TimerRequest getOneTimer(long id);
	
	/** 타이머 한 개 수정 (카테고리와 하위 루틴도 함께 수정함) */
	public TimerRequest modifyOneTimer(long id, TimerRequest timerRequest);
	
	/** 전체 헬스 카테고리 조회 */
	public List<HealthCategory> getHealthCategoryList();
	
	/**타이머 하나의 전체 카테고리 조회*/
	public List<TimerCategory> getCategoryList(long id);
	
	/**타이머 하나의 전체 루틴 조회*/
	public List<Routine> getRoutineList(long id);
	
	/** 타이머 등록 (카테고리와 하위 루틴도 함께 등록함) */
	public Timer createTimer(TimerRequest timerRequest);
	
	/** 타이머 한 개 삭제 (카테고리와 하위 루틴도 삭제함)*/
	public boolean removeTimer(long id);
	
	/** 타이머 하나의 전체 카테고리 삭제 */
	public boolean removeCategoryList(long id);
	
	/** 타이머 하나의 전체 루틴 삭제 */
	public boolean removeRoutineList(long id);
	
	/** 검색을 통한 타이머 조회 */
	public List<Timer> searchTimer(SearchCondition condition);
}
