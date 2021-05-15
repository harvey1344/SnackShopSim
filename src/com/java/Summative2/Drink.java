package com.java.Summative2;

public class Drink extends Product
{
    //additional child instance variable
    private String sugarLevel;

    //constructor
    public Drink(String productID, String productName, int price, String sugarLevel) throws InvalidProductException {
        super(productID,productName,price);
        this.sugarLevel=sugarLevel;

        if (!productID.contains("D-"))
        {
         throw new InvalidProductException();
        }

    }

    //constructor with default 'sugarLevel'
    public Drink(String productID, String productName, int price) throws InvalidProductException {
        super(productID,productName, price);
        sugarLevel="none";

        if (!productID.contains("D-"))
        {
            throw new InvalidProductException();
        }
    }

    //accessor

    public String getSugarLevel()
    {
        return sugarLevel;
    }

    //override on abstract method defined in Product

    @Override
    public int calculatePrice()
    {
        if(sugarLevel.equals("High"))
        {
            return Math.round(price+24);
        }
        if(sugarLevel.equals("Low"))
        {
            return Math.round(price+18);
        }
        return price;
    }

    @Override
    public String toString()
    {
        return "ProductID: "+productID+ "\nName: "+productName+ "\nPrice: "+calculatePrice()
                +"\nSugar level: "+ sugarLevel+"\n";
    }

    public static void main(String[] args) throws InvalidProductException {
        Product drinkTest1= new Drink("D-1234567","drink1",100);
        Product drinkTest2= new Drink("D-1234567","drink2",100,"High");
        Product drinkTest3= new Drink("D-1234567","drink3",100, "Low");

        System.out.println(drinkTest1);
        System.out.println(drinkTest2);
        System.out.println(drinkTest3);

        //try catch block for "D-"
        try
        {
            Product dTest = new Drink("F-1234567","drink1",100);
        } catch (InvalidProductException e)
        {
            System.out.println("Doesnt contain \"D-\"");
        }
        //try catch block for longer ID
        try
        {
            Product sTest = new Drink("D-12345678","long",100);
        }catch (InvalidProductException e)
        {
            System.out.println("ID too long");
        }
        //try catch block for shorter ID
        try
        {
            Product lTest = new Drink("D-123", "short", 100);
        }catch (InvalidProductException e)
        {
            System.out.println("ID too short");
        }

    }
}
