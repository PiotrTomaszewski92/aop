package dao;

import org.springframework.stereotype.Component;

@Component
public class AccountDAO {
    private String name;
    private String serviceCode;

    public void addAccount(boolean vipFlag){
        System.out.println(getClass()+": doing my db work: adding an account");
    }

    public boolean doWork(){
        System.out.println(getClass()+": doWork()");
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
}
