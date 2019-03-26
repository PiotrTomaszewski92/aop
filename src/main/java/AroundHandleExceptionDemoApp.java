import config.DemoConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.TrafficFortuneService;

public class AroundHandleExceptionDemoApp {
    public static void main(String[] args) {
        //read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        TrafficFortuneService theFortuneService =
                context.getBean("trafficFortuneService",TrafficFortuneService.class);

        System.out.println("+~+~+~+~+ Main program: Around  Handle Exception Demo App");
        System.out.println("+~+~+~+~+ Calling getFortune");
        boolean tripWire = true;
        String data = theFortuneService.getFortune(tripWire);
        System.out.println("+~+~+~+~+ My fortune is: "+data);
        System.out.println("+~+~+~+~+ Finished");
        context.close();
    }
}
