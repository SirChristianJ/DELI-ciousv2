#**Table of Contents**
1. [Introduction](#introduction)
2. [Lets start this walk through!](#start)
   - [Accounts CSV Contents](#accounts)
3. [Let us build a sandwich!](#sandwich)
   - [Bread](#bread)
   - [Meat](#meat)
   - [Cheese](#cheese)
   - [Regular Toppings](#toppings)
5. [Let us add more to this order!](#ordermenu)
   - [Drinks](#drinks)
6. [Lets checkout](#checkout)
7. [Final Result](#result)
8. [Interesting Code Snippet](#snippet)


## Introduction
**What is this and why did I go this route?**
For this capstone, we were tasked with creating a fully Object-Oriented program for a sandwich shop that will allow users to customize sandwiches and order chips and drinks.

I challenged myself to incorporate interfaces and streams as those were the latest concepts we covered in class, and I wanted to demonstrate a clear understanding of those topics. I also wanted to incorporate interfaces to introduce some decoupling of data. Additionally, I implemented an idea I had during my prior capstone: creating a CSV file that holds account information, allowing a user to "log in," verify their account, and make an order under that account. After checkout, a timestamped receipt is created as a CSV file and is then added to a directory named after the current active user's ID.

![Capstone 2 diagram](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2diagram%20(1).jpg)
## Start
**Let's create a simple order together!**

To start things off, we will be prompted whether or not we have an account. If we do, then we will be prompted to confirm credentials. Otherwise, we will be taken to an account creation screen.
![intro screen](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2pic1.jpg)

Let us use an existing account located in `Accounts.csv` and create an order as that user!
### Accounts
**Here is the Accounts CSV contents**
![accounts csv](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2pic2.jpg)

We will be using the following user:
![example user](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2pic15.jpg)

Once we are verified, we are greeted by our first-level entry menu:
![logging in](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2pic3.jpg)
![account verified](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2pic4.jpg)

Afterward, we will then be forwarded to our order screen upon pressing 1:
![order screen](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2pic5.jpg)

**Here, we can pair any of the following options. We can also order a respective item individually without pairing it with anything else:**
### Sandwich
**Let's build a sandwich!**
We are prompted to enter the size of our sandwich:
![sizeScreen](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2pic6.jpg)

Let us enter our sandwich contents, shall we ? 
### Bread
**Type of Bread**
![breadScreen](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2pic7.jpg)
### Meat
**Type of Meat**
![meatType](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2pic8.jpg)
**Do you want Extra Meat**
![extraMeat](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2pic9.jpg)
### Cheese
**Type of Cheese**
![cheeseType](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2pic10.jpg)
### Toppings
**Lets Stack our Regular Toppings!**
![toppingsType](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2pic11.jpg)

## Ordermenu
**Let us confirm our sandwich is correct and return back to our Order Screen**
![orderScreen](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2pic12.jpg)
**Drink Time**
![drink screen](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2pic13.jpg)
## Checkout
**Let's check this order out!**
![checkout](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2pic14.jpg)
## Result
**A receipt is produced, written as a CSV to the directory named after the user's ID**
![folder structure](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2pic16.jpg)
![receipt](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2pic17.jpg)

## Snippet
**A code snippet I'd like to show is fairly simple but fairly effecient and effective**
Here we have a `private final static String dataDirectory = "CustomerInformation/";`, which then appends current users ID `account.getId()` to our `dataDirectory` string. Our string is then converted to a Path and a new directory is created with that path. We then iterate through the active users orders via `for(Order order: account.getCustomer().getOrders())` and write a csv file representing the receipt of the order named after the timestamp the order was placed.

```java
public static void writeToCSV(Account account){
        try{
            String accountIDFolderPath = dataDirectory + account.getId() + "/";
            Path path = Paths.get(accountIDFolderPath);
            Files.createDirectories(path);
            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");
            String datePosted = date.format(formatter);
            for(Order order: account.getCustomer().getOrders()){
                BufferedWriter bfw = new BufferedWriter(new FileWriter(accountIDFolderPath + datePosted));
                bfw.write(order.encodedString());
                bfw.close();
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage() + "<---File write error when producing order history.");
        }
    }
