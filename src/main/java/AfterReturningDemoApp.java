import config.DemoConfig;
import dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterReturningDemoApp {
    public static void main(String[] args) {
        //read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO theAccountDAO = context.getBean("accountDAO",AccountDAO.class);

        List<AccountDAO> theAccounts = theAccountDAO.findAccounts();

        System.out.println("\nMain program: after returning demo app");
        System.out.println("================");
        System.out.println(theAccounts);

        context.close();
    }
}
