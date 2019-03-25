package aspect;

import dao.AccountDAO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
    @Before("LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint){ //can be any method name
        //add our custom code
        System.out.println("====>> Executing @Before advice - MyDemoLoggingAspect");

        //display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: "+methodSignature);

        //display method arguments
        Object[] args = theJoinPoint.getArgs();
        for (Object tempArg : args){
            System.out.println(tempArg);
            if(tempArg instanceof AccountDAO){
                AccountDAO theAccount = (AccountDAO) tempArg;
                System.out.println("Account name: "+theAccount.getName()+", level: "+theAccount.getServiceCode());
            }
        }
    }

    @AfterReturning(pointcut = "execution(* dao.AccountDAO.findAccounts(..))", returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<AccountDAO> result){
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("====>> Executing @AfterReturning on method: "+method);
        System.out.println("====>> result is: "+result);

        // let's modify data!'
        convertAccountNamesToUpperCase(result);
        System.out.println("====>> result is: "+result);
    }

    private void convertAccountNamesToUpperCase(List<AccountDAO> result) {
        for(AccountDAO account : result){
            String theUpperName = account.getName().toUpperCase();
            account.setName(theUpperName);
        }
    }
}
