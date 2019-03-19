package aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
    //pointcut expression (run this code BEFORE
    // - target object method: "public void addAccount()"
    @Before("execution(public void addAccount())")
    public void beforeAddAccountAdvice(){ //can be any method name
        //add our custom code
        System.out.println("\n====>> Executing @Before advice on addAccount()");
    }
}
