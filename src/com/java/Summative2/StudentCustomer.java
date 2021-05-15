package com.java.Summative2;

public class StudentCustomer extends Customer
{

    //constructors
    public StudentCustomer (String accountID, String customerName, int accBalance) throws InvalidCustomerException {
        super(accountID,customerName,accBalance);
    }
    public StudentCustomer(String customerID,String customerName) throws InvalidCustomerException {
        super(customerID,customerName);
        this.accBalance=0;
    }

    @Override
    public int chargeAccount(int productPrice) throws InsufficientBalanceException
    {
        System.out.println("@chargeAccount");
        final int limit=-500;
        final int stuPrice= (int) Math.round(productPrice*0.95);
        int newBal= accBalance-stuPrice;
        if (newBal<limit)throw new InsufficientBalanceException();
        else
            this.accBalance=newBal;
            return newBal;
    }

    @Override
    public String toString() {
        return "Customer Type: Student\nCustomerID: "+ accountID+ "\nCustomer Name: "+ customerName
                +"\nAccount Balance: "+accBalance;
    }

    public static void main(String[] args) throws InsufficientBalanceException, InvalidProductException, InvalidCustomerException {
        Customer student1= new StudentCustomer("S12345","HarveyT",150);
        Product food1 =new Food("F-1234567", "Food1", 100, "cold",0.1);
        Product drink1= new Drink("D-1234567", "Drink1", 500);

        //@toString test
        System.out.println(student1);
        System.out.println("toString^");
        //works

        //@chargeAccount test
        System.out.println(student1.accBalance);
        System.out.println(food1.calculatePrice());
        try
        {
            student1.chargeAccount(food1.calculatePrice());
        } catch (InsufficientBalanceException e){};
        System.out.println(student1.accBalance);
        System.out.println("chargeTest^");

        //negativeBalanceTest
        try
        {
            student1.chargeAccount(food1.calculatePrice());
        }catch (InsufficientBalanceException e){}
        System.out.println(student1.accBalance); // check bal
        System.out.println("negbal Test^");

        //Insuffient test
        try
        {
            student1.chargeAccount(drink1.calculatePrice());
        }catch (InsufficientBalanceException e){}
        System.out.println(student1.accBalance);

        //try catch block for alphanumeric
        try
        {
            Customer charTest = new StudentCustomer("A151@1","alphanumeric");
        }catch (InvalidCustomerException e)
        {
            System.out.println("Use alphanumeric characters only");
        }
        //try catch block for ID length
        try
        {
            Customer lenTest = new Customer("12345a1", "length",100);
        }catch (InvalidCustomerException e)
        {
            System.out.println("Customer ID must be 6");
        }



    }
}
