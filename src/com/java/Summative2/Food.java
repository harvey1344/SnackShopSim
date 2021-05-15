package com.java.Summative2;

import java.sql.SQLOutput;

public class Food extends Product
{
    //child instance variables
    private String heat;
    final  double surCharge= 1.1;

    //constructor
    public Food(String productID, String productName, int price, String heat, double surCharge)
    throws InvalidProductException
    {
        //calling constructor from superclass
        super(productID, productName, price);
        this.heat=heat;

        if (!productID.contains("F-"))
        {
            throw new InvalidProductException();
        }

    }

    public boolean isHot(String heat)
    {
        if(heat.equals("hot"))
        {
            return true;
        }
        return false ;

    }


    //override on abtract method defined in product class
    //applies default surcharge if product is hot
    @Override
    public int calculatePrice()
    {
        if(heat.equals("hot"))
        {
            return (int) Math.round(price*surCharge);
        }
        return price;
    }

    //accessors


    public String getHeat()
    {
        return heat;
    }

    public double getSurCharge()
    {
        return surCharge;
    }

    @Override
    public String toString()
    {
        return "ProductID: "+productID+"\nName: "+productName+"\nPrice(p): "+calculatePrice()
                +"\n"+ heat;
    }

    public static void main(String[] args) throws InvalidProductException {
        Product testHot = new Food("F-1234567", "Food1", 100, "hot",0.1);
        Product testCold = new Food("F-1234567", "Food2", 100, "cold", 0.1);

        //calculate price test
        System.out.println(testHot);
        System.out.println(testCold);

        //throws exception for negative base price
        try
        {
            Product priceTest= new Food("F-1234567", "Food1", -100, "hot",0.1);

        } catch (InvalidProductException e){}
        //throws exception ID not starting with "F-"
        try
        {
            Product priceTest= new Food("D-1234567", "Food1", 100, "hot",0.1);

        } catch (InvalidProductException e){}

    }

}
