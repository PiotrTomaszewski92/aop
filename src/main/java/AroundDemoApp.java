import config.DemoConfig;
import dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.TrafficFortuneService;

import java.util.List;

public class AroundDemoApp {
    public static void main(String[] args) {
        //read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        TrafficFortuneService theFortuneService =
                context.getBean("trafficFortuneService",TrafficFortuneService.class);

        System.out.println("+++++++++ Main program: Around Demo App");
        System.out.println("+++++++++ Calling getFortune");
        String data = theFortuneService.getFortune();
        System.out.println("+++++++++ My fortune is: "+data);
        System.out.println("+++++++++ Finished");
        context.close();
    }
}
