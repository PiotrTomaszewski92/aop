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
        String data = null;
        try {
            boolean tripWire = true;
            data = theFortuneService.getFortune(tripWire);
        }catch(RuntimeException e){
            System.out.println("Exception in main program: "+e.getMessage());
        }
        System.out.println("+~+~+~+~+ My fortune is: "+data);
        System.out.println("+~+~+~+~+ Finished");
        context.close();
    }
}
