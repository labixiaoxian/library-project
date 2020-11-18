package com.wyu.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.wyu.dao.BookMapper;
import com.wyu.dao.BorrowInfoMapper;
import com.wyu.dao.PunishmentMapper;
import com.wyu.entity.BorrowInfo;
import com.wyu.entity.Punishment;

@Configuration
@EnableScheduling
public class ScheduleTask {
	@Autowired
	PunishmentMapper punishmentMapper;

	@Autowired
	BorrowInfoMapper borrowInfoMapper;

	@Autowired
	BookMapper bookMapper;

	@Scheduled(cron = "0 0 0 * * *")
	public void findOverDueUser() {
		System.out.println("定时方法执行");
		List<BorrowInfo> overDueInfo = borrowInfoMapper.getOverDueInfo();
		for (BorrowInfo borrowInfo : overDueInfo) {
			Punishment punishment = new Punishment(null, borrowInfo.getUserId(), 30, "未归还");
		}
	}
}
