import config.DemoConfig;
import dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterFinallyDemoApp {
    public static void main(String[] args) {
        //read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO theAccountDAO = context.getBean("accountDAO",AccountDAO.class);
        List<AccountDAO> theAccounts = null;
        try{
            //add a boolean flag to simulate exceptions
            boolean tripWare = false;
            theAccountDAO.findAccounts(tripWare);
        }catch (Exception exc){
            System.out.println("#####Main programm --- caught exception: "+exc);
        }


        System.out.println("\nMain program: after throwing demo app");
        System.out.println("================");
        System.out.println(theAccounts);

        context.close();
    }
}
