import cn.zq.service.activiti.ActProcessService;
import cn.zq.utils.BeanUtils;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = cn.zq.ServiceApp.class)
@RunWith(SpringRunner.class)
public class NewTest {
    @Test
    public void deploy(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(111);
    }
}
