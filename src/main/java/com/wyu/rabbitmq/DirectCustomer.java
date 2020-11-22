package com.wyu.rabbitmq;

import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * Created by XiaoXian on 2020/11/19.
 */
@Component
public class DirectCustomer {

	@Value("${spring.mail.username}")
	private String from;

	@Resource
	private JavaMailSender mailSender;

	@RabbitListener(queues = "directQueue")
	public void getMsg5(Map map) {
		// 负责监听消息队列信息,监听到信息,则发送一链接至对方邮箱,点击链接可以激活邮箱

		System.out.println("监听到消息：" + map);
		// 新添加用户 id
		Integer userId = (Integer) map.get("userId");

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo((String) map.get("email"));
			helper.setSubject("激活邮箱");
			// 改成前端页面，前端页面再调用下面的接口
			helper.setText("<a href='http://192.168.3.78:8080/#/waiting?userId=" + userId + "'>激活邮箱</a>", true);
			mailSender.send(message);
		} catch (MessagingException e) {
			System.err.println("邮件发送失败");
			e.printStackTrace();
		}
	}
}
