import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huayu.example.bo.SimpleUser;
import com.huayu.example.service.SimpleUserService;

//@RunWith(SpringJUnit4ClassRunner.class)
//// 表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestSimpleSpring {

	private static Logger logger = LoggerFactory.getLogger(TestSimpleSpring.class);

	// private ApplicationContext ac = null;
	/*
	 * @Resource private SimpleUserService simpleUserService ;
	 */

	public static void main(String args[]) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
				"conf/applicationContext.xml");
		SimpleUserService userService = (SimpleUserService) ac
				.getBean("userService");
		
		userService.addUser(2l, "kaixin");
		
		Map<String ,Object> query = new HashMap<String,Object>();
		List<SimpleUser> userList = userService.getUserList(query);
		
		logger.info(userList.toString());
		
		logger.info(userService.getUser(userList.get(0).getId()).toString());
		
		
		userService.deleteUser(2l);
	
	}

	// @Test
	// public void test1() {
	// SimpleUser user = simpleUserService.getUserById(1);
	// // System.out.println(user.getUserName());
	// // logger.info("值："+user.getUserName());
	// logger.info((user));
	// }

}
