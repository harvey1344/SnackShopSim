package com.java.Summative2;

public  abstract class Product
{
    //instance variables
    protected String productID;
    protected String productName;
    protected int price;

    //constructor
    public Product(String productID, String productName, int price)
    throws InvalidProductException
    {
        this.productID=productID;
        this.productName=productName;
        this.price=price;

        if (productID.length()<9||productID.length()>9 ||price<0)
        {
            throw new InvalidProductException();
        }
    }
    //abstract method for child class
    public abstract int calculatePrice();

    //accessor methods
    public String getProductID()
    {
        return productID;
    }

    public String getProductName()
    {
        return productName;
    }

    public int getPrice()
    {
        return price;
    }
}
