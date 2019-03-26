package aspect;

import dao.AccountDAO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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

    @After("execution(* dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("~~~~~~>> Executing @After (finally) on method: "+method);
    }

    @AfterThrowing(pointcut = "execution(* dao.AccountDAO.findAccounts(..))", throwing = "excecut")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable excecut){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("=-=-=-=-=>> Executing @AfterThrowing on method "+method);

        System.out.println("=-=-=-=-=>> The exception is: "+excecut);
    }

    @Around("execution(* service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("+++++++++>> Executing @Around on method: "+method);

        Object result = null;

        long begin = System.currentTimeMillis();
            try {
                result = proceedingJoinPoint.proceed();
            }catch (RuntimeException exc){
                //rethrow exception:
                throw exc;
            }
        long end = System.currentTimeMillis();
        System.out.println("+++++++++>> Duration: "+(end-begin)/1000.0+ " seconds");
        return result;
    }
}
