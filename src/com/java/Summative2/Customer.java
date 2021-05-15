package com.java.Summative2;



public class Customer
{
    protected String accountID;
    protected String customerName;
    protected int accBalance;

    //constructor for Customer
    Customer(String accountID, String customerName, int accBalance) throws InvalidCustomerException {
        this.accountID=accountID;
        this.customerName=customerName;
        this.accBalance= accBalance;

        if (accBalance<0||accountID.length()!=6)
        {
            throw new InvalidCustomerException();
        }
        for(int i=0; i<accountID.length();i++)
        {
            if(!Character.isLetter(accountID.charAt(i)) &&
            !Character.isDigit(accountID.charAt(i)))
                throw new InvalidCustomerException();
        }

    }

    Customer(String accountID, String customerName) throws InvalidCustomerException {
        this.accountID=accountID;
        this.customerName=customerName;
        this.accBalance=0;

        if (accountID.length()!=6)
        {
            throw new InvalidCustomerException();
        }

        for(int i=0; i<accountID.length();i++)
        {
            if(!Character.isLetter(accountID.charAt(i)) &&
                    !Character.isDigit(accountID.charAt(i)))
                throw new InvalidCustomerException();
        }
    }

    //accessor methods
    public String getAccountID()
    {
        return accountID;
    }
    public String getCustomerName()
    {
        return customerName;
    }
    public int getAccBalance()
    {
        return accBalance;
    }

    public void setAccBalance(int accBalance) {
        this.accBalance = accBalance;
    }

    //method to add funds to account
    public int addFunds(int funds)
    {

        System.out.println("@addFunds");
        if(funds<0)
        {
            System.out.println("Cannot add negative funds");
            return this.accBalance;
        }
        this.accBalance+=funds;
        return this.accBalance+funds;
    }

    //method to simulate charging an account
    //if remaining balance < 0 then exception thrown
    public int chargeAccount(int productPrice)throws InsufficientBalanceException
    {
        System.out.println("@chargeAccount");
        int newBal= accBalance-productPrice;
        if (newBal<0)throw new InsufficientBalanceException();
        else
            accBalance=newBal;
            return newBal;

    }

    @Override
    public String toString()
    {
        return "Customer Type: Other\nAccountID: "+accountID+"\nCustomer Name: "+customerName+
                "\nCurrent Balance: "+accBalance;
    }

    public static void main(String[] args) throws InsufficientBalanceException, InvalidProductException, InvalidCustomerException {
        Customer testC1 = new Customer("A1A2A3", "Harvey Thompson",99);
        Customer testC2 = new Customer("B1B2B3", "Maddie BJ", 200);
        Product testF1 = new Food("F-1234567", "Food1", 100, "cold",0.1);
        Product testD1= new Drink("D-1234567", "Drink1", 150);

        //@toString
        System.out.println(testC1);
        System.out.println("toString Test^\n");

        //@addFunds
        System.out.println(testC1.accBalance); //org balance
        testC1.addFunds(500);
        System.out.println(testC1.accBalance); //new balance
        System.out.println("addfunds test^\n");

        //negBalance
        System.out.println(testC1.accBalance);
        testC1.addFunds(-1);
        System.out.println(testC1.accBalance);
        //balance stays same
        System.out.println("negbal test\n");

        //@chargeAccount
        System.out.println(testC2.accBalance);
        System.out.println(testF1.calculatePrice());
        try
        {
            testC2.chargeAccount(testF1.calculatePrice());
        } catch (InsufficientBalanceException e){};
        System.out.println(testC2.accBalance);
        //balance updates

        //exception test neg bal
        System.out.println(testD1.calculatePrice());
        try
        {
         testC2.chargeAccount(testD1.calculatePrice());
        }catch (InsufficientBalanceException e){};
        System.out.println(testC2.accBalance);
        //works

        //try catch for negbal in constructor
        try
        {
            Customer negbalTest = new StaffCustomer("A1A2A3", "Mr Man", -1, "CMP");
        }catch (InvalidCustomerException e)
        {
            System.out.println("Starting balance cannot be negative");
        }
    }
}
