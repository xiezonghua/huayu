import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huayu.etl.backend.model.ApplicationInfoModel;
import com.huayu.etl.backend.service.AppConfigurationService;

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
		loadAppInfo(ac);
//		createConfigureation(ac);
//		HdfsBaseClient hdfsClient = (HdfsBaseClient) ac.getBean("hdfsClient");
//		try {
//			hdfsClient.showFiles("test/testhive1");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ac.close();
		 
	
	}
	public static void loadAppInfo(ClassPathXmlApplicationContext ac){
		System.out.println("loadAppInfo strating .....");
		AppConfigurationService appConfigurationService =  (AppConfigurationService) ac.getBean("appConfigurationService");
		ApplicationInfoModel appAllInfo = appConfigurationService.queryAppAllInfo("e75aea8e-2742-4ea0-8cff-15ab90b1077e");
		System.out.println("loadAppInfo completed !");
		
	}
	
	// @Test
	// public void test1() {
	// SimpleUser user = simpleUserService.getUserById(1);
	// // System.out.println(user.getUserName());
	// // logger.info("值："+user.getUserName());
	// logger.info((user));
	// }

}
