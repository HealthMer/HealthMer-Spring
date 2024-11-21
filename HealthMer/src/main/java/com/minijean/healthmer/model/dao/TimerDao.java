package com.minijean.healthmer.model.dao;

import java.util.List;

import com.minijean.healthmer.model.dto.HealthCategory;
import com.minijean.healthmer.model.dto.Routine;
import com.minijean.healthmer.model.dto.SearchCondition;
import com.minijean.healthmer.model.dto.Timer;
import com.minijean.healthmer.model.dto.TimerCategory;

public interface TimerDao {
	
	/** 전체 타이머 조회 */
	public List<Timer> selectAll();
	
	/** 전체 헬스 카테고리 조회 */
	public List<HealthCategory> selectHealthCategory();
	
	/** 타이머 하나의 전체 카테고리 조회 */
	public List<TimerCategory> selectAllCategory(long id);
	
	/** 타이머 하나의 전체 루틴 조회 */
	public List<Routine> selectAllRoutine(long id);
	
	/** 타이머 등록 */
	public int insertTimer(Timer timer);
	
	/** 타이머 카테고리 등록 */
	public int insertTimerCategory(List<TimerCategory> category);
	
	/** 타이머 하위 루틴 등록 */
	public int insertRoutines(List<Routine> routines);
	
	/** 타이머 수정 */
	public int updateTimer(Timer timer);
	
	/** 타이머 하위 루틴 수정 */
	public int updateRoutines(List<Routine> routines);
	
	/** 타이머 한 개 삭제 (하위 루틴도 삭제됨) */
	public int deleteTimer(long id);
	
	/** 타이머 달성치 증가 */
	public void updateCompleteCount(long id);
	
	/** 검색을 통한 타이머 조회*/
	public List<Timer> searchTimer(SearchCondition condition);
	
}
