package com.java.Summative2;


import java.util.ArrayList;

public class SnackShop
{
    private String shopName;
    private ArrayList<Customer> customers;
    private ArrayList<Product> products;
    private double turnover;

    //constructor
    SnackShop(String shopName)
    {
        this.shopName=shopName;

    }

    //accessor methods
    public String getShopName()
    {
        return shopName;
    }

    public double getTurnover()
    {
        return turnover;
    }




    public static void main(String[] args) throws InvalidCustomerException {
        Customer testC1 = new Customer("A1A2A3", "Harvey Thompson",99);
        customers.add(testC1);
        System.out.println(customers.size());



    }
}


