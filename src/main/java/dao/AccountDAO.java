package dao;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {
    private String name;
    private String serviceCode;

    public AccountDAO() { }

    public AccountDAO(String name, String serviceCode) {
        this.name = name;
        this.serviceCode = serviceCode;
    }

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

    public List<AccountDAO> findAccounts(){
        List<AccountDAO> myAccounts = new ArrayList<>();
        myAccounts.add(new AccountDAO("John", "Silver"));
        myAccounts.add(new AccountDAO("Peter", "Platinum"));
        myAccounts.add(new AccountDAO("Luca", "Gold"));
        return myAccounts;
    }

    @Override
    public String toString() {
        return "AccountDAO: " +
                "name='" + name +
                ", serviceCode='" + serviceCode;
    }
}
