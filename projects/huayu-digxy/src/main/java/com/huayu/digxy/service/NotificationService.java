package com.huayu.digxy.service;

import java.util.List;

import com.huayu.digxy.bo.Notification;
import com.huayu.platform.service.BasicService;

public interface NotificationService extends BasicService<Notification, Long> {
	int addNotification(Notification notify);
	
	List<Notification> query(Long busId);
	
	Notification queryMainNotice(Long busId);
}