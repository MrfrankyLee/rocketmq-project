import com.lxl.trade.entity.User;
import com.lxl.trade.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author lixiaole
 * @date 2018/9/7
 * @Description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:xml/spring-dao.xml")
public class DataTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUser(){
        User user = new User();
        user.setUserName("lixiaole");
        user.setUserPassword("123456");
        int i = userMapper.insert(user);
        System.out.println("i="+i);
        System.out.println(user.getUserId());

    }
}
