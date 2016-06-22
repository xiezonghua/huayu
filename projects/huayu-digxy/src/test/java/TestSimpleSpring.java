import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
//		SimpleUserService userService = (SimpleUserService) ac
//				.getBean("userService");
		

	
	}

	// @Test
	// public void test1() {
	// SimpleUser user = simpleUserService.getUserById(1);
	// // System.out.println(user.getUserName());
	// // logger.info("值："+user.getUserName());
	// logger.info((user));
	// }

}
