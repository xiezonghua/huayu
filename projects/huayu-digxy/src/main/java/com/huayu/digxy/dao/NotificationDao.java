package com.huayu.digxy.dao;

import java.util.Map;

import com.huayu.digxy.bo.Notification;
import com.huayu.platform.dao.BaseDaoMapper;

public interface NotificationDao extends BaseDaoMapper<Notification, Long> {
	int updateSectionByBus(Map<String , Object> query);
}