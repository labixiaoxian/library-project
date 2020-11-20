package com.wyu.config;

import java.util.List;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.wyu.dao.BookMapper;
import com.wyu.dao.BorrowInfoMapper;
import com.wyu.dao.PunishmentMapper;
import com.wyu.dao.UserInfoMapper;
import com.wyu.dao.UserMapper;
import com.wyu.entity.BorrowInfo;
import com.wyu.entity.Punishment;
import com.wyu.entity.User;

/**
 * 
 * @author 李达成
 * @since 2020/11/18
 *
 */
@Configuration
@EnableScheduling
public class ScheduleTask {
	@Autowired
	PunishmentMapper punishmentMapper;

	@Autowired
	BorrowInfoMapper borrowInfoMapper;

	@Autowired
	BookMapper bookMapper;

	@Autowired
	UserInfoMapper UserInfoMapper;

	@Autowired
	UserMapper UserMapper;

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Value("${spring.mail.username}")
	private String mailUserName;

	/**
	 * 定时任务，每天0点进行一次，更新扔逾期的用户的罚金金额
	 */
	@Scheduled(cron = "0 0 0 * * *")
	public void updateFine() {
		Executors.newSingleThreadExecutor().execute(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					List<Punishment> list = punishmentMapper.list();
					for (Punishment punishment : list) {
						User user = UserMapper.findUserById(punishment.getUserId());
						user.setCredit((user.getCredit() - 5) <= 0 ? 0 : user.getCredit() - 5);
						UserMapper.updateUser(user);
						punishment.setFine(punishment.getFine() + 1);
						punishmentMapper.updateFine(punishment);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 定时任务，每天0点进行一次，将当天逾期的人加入惩罚表
	 */
	@Scheduled(cron = "20 0 0 * * *")
	public void findOverDueUser() {
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				List<BorrowInfo> overDueInfo = borrowInfoMapper.getOverDueInfo();
				try {
					for (BorrowInfo borrowInfo : overDueInfo) {
						User user = UserMapper.findUserById(borrowInfo.getUserId());
						user.setCredit((user.getCredit() - 10) <= 0 ? 0 : user.getCredit() - 10);
						UserMapper.updateUser(user);
						Punishment punishment = new Punishment(null, borrowInfo.getUserId(), 30,
								bookMapper.queryById(borrowInfo.getBookId()) + "未归还");
						punishmentMapper.insert(punishment);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 定时任务，每天0点执行一次，发送邮件提醒逾期用户还书，并告知罚款金额
	 * 
	 * @throws MessagingException
	 */
	@Scheduled(cron = "40 0 0 * * *")
	public void sendEmail() throws MessagingException {
		Executors.newSingleThreadExecutor().execute(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				List<Punishment> list = punishmentMapper.list();
				for (Punishment punishment : list) {
					try {
						MimeMessage mimeMessage = mailSender.createMimeMessage();
						MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
						mimeMessageHelper.setSubject("图书逾期信息");
						mimeMessageHelper.setText(
								"<h1 style='color:red;text-align:center'>您有图书未归还</h1><br/>" + "<p>"
										+ punishment.getReason() + "</p>" + "<p>罚款" + punishment.getFine() + "元</p>",
								true);
						mimeMessageHelper.setTo(UserInfoMapper.findUserInfoById(punishment.getUserId()).getEmail());
						mimeMessageHelper.setFrom(mailUserName);
						mailSender.send(mimeMessage);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				System.gc();
			}
		});
	}
}
