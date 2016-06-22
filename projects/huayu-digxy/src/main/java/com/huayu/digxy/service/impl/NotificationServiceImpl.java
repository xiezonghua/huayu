package com.huayu.digxy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.huayu.digxy.bo.Notification;
import com.huayu.digxy.dao.NotificationDao;
import com.huayu.platform.dao.BaseDaoMapper;
import com.huayu.digxy.service.NotificationService;
import com.huayu.platform.service.AbstractBasicService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("notificationService")
public class NotificationServiceImpl extends AbstractBasicService<Notification , Long> implements NotificationService{

	private final static Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class.getCanonicalName());

	@Resource
	private NotificationDao notificationDao ;

	@Override
	public BaseDaoMapper<Notification, Long> getDao() {		
		return notificationDao;
	}
	
	@Override
	public int addNotification(Notification notify) {
		notify.setAddDate(new Date());
		notify.setStatus((byte) 1);
		
		if( notify.getIsMain() != null && 1 == notify.getIsMain()){
			logger.info("add a new notice for all. ");
			Map<String ,Object> param = new HashMap<String,Object>();
			param.put("busId", notify.getBusId());
			param.put("isMainParam", 1);
			param.put("isMain", 0);
			notificationDao.updateSectionByBus(param);
		}
		
		return notificationDao.insertSelective(notify);
		
	}

	@Override
	public List<Notification> query(Long busId) {
		Map<String ,Object> query = new HashMap<String,Object>();
		query.put("busId", busId);
		query.put("status" , 1);
		query.put("orderBy", "id");
		query.put("orderType" , "desc");
		
		return notificationDao.selectByCondition(query);
	}

	@Override
	public Notification queryMainNotice(Long busId) {
		Notification notify = new Notification() ;
		notify.setBusId(busId);
		notify.setIsMain((byte)1);
		notify.setStatus((byte)1);
		List<Notification> result = notificationDao.select(notify);
		if(result.size() == 1){
			return result.get(0);
		}else{
			logger.error("bad data in notify , it have null or multi main notify. busid {}" , busId);
			return null;
		}
	}
}
