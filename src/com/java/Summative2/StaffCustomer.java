package com.java.Summative2;

public class StaffCustomer extends Customer
{
    private String school;

    public StaffCustomer(String accountID, String customerName, int accBalance, String school) throws InvalidCustomerException {
        super(accountID,customerName,accBalance);
        this.school=school;
    }

    public  StaffCustomer(String accountID, String customerName, String school) throws InvalidCustomerException {
        super(accountID, customerName);
        this.accBalance=0;
        this.school=school;
    }



    @Override
    public int chargeAccount(int productPrice) throws InsufficientBalanceException
    {
        if (school.equals("CMP"))
        {
            return super.chargeAccount((int) Math.round(productPrice*0.9));
        }
        if (school.equals("MTH")||school.equals("BIO"))
        {
            return super.chargeAccount((int) Math.round(productPrice*0.975));
        }
        else
            return super.chargeAccount(productPrice);
    }

    @Override
    public String toString() {
        return "Customer Type: Staff\nCustomerID: "+ accountID+ "\nCustomer Name: "+ customerName
                +"\nStaff School: "+school+"\nAccount Balance: "+accBalance;
    }

    public static void main(String[] args) throws InvalidProductException, InvalidCustomerException {
        Customer cmpTest= new StaffCustomer("A1234","Dr Jeff", 500, "CMP");
        Customer bioTest= new StaffCustomer("B1234", "Prof Bio", 50, "BIO");
        Customer mthTest= new StaffCustomer("C1234", "Mathy Matt", "MTH");
        Customer regTest = new StaffCustomer("O12345","Mr Reg", 100, "");
        Product food1= new Food("F-1234","food1",100, "hot", 0.1);
        Product food2= new Food("F-1235","food2",100, "cold", 0.1);

        //cmp discount test;
        try {
            cmpTest.chargeAccount(food1.calculatePrice());
        } catch (InsufficientBalanceException e){}
        System.out.println(cmpTest.accBalance);
        System.out.println("cmpTest^\n");

        //making sure account cannot go below 0
        System.out.println(bioTest.accBalance);
        try
        {
            bioTest.chargeAccount(food1.calculatePrice());
        } catch (InsufficientBalanceException e){}
        System.out.println(bioTest.accBalance);
        System.out.println("balanceCheck^\n");

        //@addfunds
        System.out.println(mthTest.accBalance);
        mthTest.addFunds(100);
        System.out.println(mthTest.accBalance);
        System.out.println("@addfunds^");

        try
        {
            mthTest.chargeAccount(food2.calculatePrice());
        } catch (InsufficientBalanceException e){}
        System.out.println(mthTest.accBalance);

        //other school test
        try
        {
            regTest.chargeAccount(food2.calculatePrice());
        } catch (InsufficientBalanceException e){}
        System.out.println(regTest.accBalance);


    }
}
