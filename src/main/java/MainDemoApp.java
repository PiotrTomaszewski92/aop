import config.DemoConfig;
import dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {
    public static void main(String[] args) {
        //read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);
        //get the bean from spring container
            AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
        //call the business method
             accountDAO.addAccount(true);
            System.out.println("CALL IT AGAIN!");
            accountDAO.addAccount(true);
            //getters / setters

            System.out.println(" ====== getters / setters ====== ");
            accountDAO.getName();
            accountDAO.getServiceCode();
            accountDAO.setName("Yo");
            accountDAO.setServiceCode("Yo2");
        //close the contextf
            context.close();
    }
}
