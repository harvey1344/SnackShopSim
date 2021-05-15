package com.java.Summative2;

import java.util.ArrayList;
import java.util.Scanner;

public class SnackShop
{
    private String shopName;
    private final ArrayList<Customer> customers;
    private final ArrayList<Product> products;
    private double turnover;

    //constructor
    SnackShop(String shopName)
    {
        this.shopName=shopName;
        products= new ArrayList<Product>();
        customers= new ArrayList<Customer>();

    }

    public void addProduct(String productStr) throws InvalidProductException {
        Scanner scan = new Scanner(productStr);
        scan.useDelimiter("@");
        String productID= scan.next().trim();
        String productName= scan.next().trim();

        if (productID.contains("F-"))
        {
            double surcharge=1.1;
            String heat= scan.next().trim();
            int price=Integer.parseInt(scan.next().trim());
            Food newFood= new Food(productID,productName,price,heat,surcharge);
            products.add(newFood);
        }
        else if (productID.contains("D-"))
        {
            String sugar= scan.next().trim();
            int price=Integer.parseInt(scan.next().trim());
            Drink newDrink = new Drink(productID,productName,price,sugar);
            products.add(newDrink);
        }
        else throw new InvalidProductException();
        scan.close();
    }

    public void addCustomer(String customerStr) throws InvalidCustomerException
    {
        //open new scanner using delimiter #
        Scanner scan = new Scanner(customerStr);
        scan.useDelimiter("#");
        //first 3 values are unversal to all customer types
        String customerID= scan.next().trim();
        String customerName= scan.next().trim();
        int balance= Integer.parseInt(scan.next().trim());
        //if scan has another value- the customer type is either staff or student
        if (scan.hasNext())
        {
            String type= scan.next().trim();
            //if type is student create new StudentCustomer object and add to collection
            if (type.equals("STUDENT"))
            {
                Customer addCustomer = new StudentCustomer(customerID,customerName,balance);
                customers.add(addCustomer);
            }
            else if (type.equals("STAFF"))
            {
                String school;
                //if scan has a next value then a school is assigned to the staff
                if (scan.hasNext())
                {
                    school= scan.next().trim();
                }
                //else the school is empty
                else
                {
                    school="";
                }
                //create new StaffCustomer object and add to collection
                Customer addCustomer = new StaffCustomer(customerID,customerName,balance,school);
                customers.add(addCustomer);
            }
        }
        //if customer has no type create new Customer object and add it to collection
        else
        {
            Customer addCustomer= new Customer(customerID,customerName,balance);
            customers.add(addCustomer);

        }
        scan.close();

    }

    public Customer getCustomer(String customerID) throws InvalidCustomerException
    {
        for(int i = 0;i<customers.size();i++)
        {
            if (customers.get(i).getAccountID().equals(customerID))
            {
                return customers.get(i);

            }
        }
        throw new InvalidCustomerException();
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




    public static void main(String[] args) throws InvalidCustomerException, InvalidProductException {
        SnackShop snackShop= new SnackShop("Myshop");
        //add test customers
        snackShop.addCustomer("58R526#John-Paul Clay#400#STAFF#CMP");
        snackShop.addCustomer("203685#Lois Romero#360");
        snackShop.addCustomer("174450#Ottilie Riley#200#STAFF");
        snackShop.addCustomer("889027#Mikel Flinn#200#STUDENT");

        //@getCustomer test
        Customer returnTest1= snackShop.getCustomer("58R526");
        System.out.println(returnTest1);
        Customer returnTest2= snackShop.getCustomer("203685");
        System.out.println(returnTest2);
        Customer returnTest3= snackShop.getCustomer("174450");
        System.out.println(returnTest3);
        Customer returnTest4= snackShop.getCustomer("889027");
        System.out.println(returnTest4);


        //works





    }


}


