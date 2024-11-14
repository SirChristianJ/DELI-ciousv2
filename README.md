For this capstone, we were tasked with creating a fully Object-Oriented program for a sandwich shop that will allow users to customize sandwiches and order chips and drinks.

I challenged myself to incorporate interfaces and streams as those were the latest concepts we covered in class, and I wanted to demonstrate a clear understanding of those topics. I also wanted to incorporate interfaces to introduce some decoupling of data. Additionally, I implemented an idea I had during my prior capstone: creating a CSV file that holds account information, allowing a user to "log in," verify their account, and make an order under that account. After checkout, a timestamped receipt is created as a CSV file and is then added to a directory named after the current active user's ID.

![Capstone 2 diagram](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2diagram%20(1).jpg)

**Let's create a simple order together!**

To start things off, we will be prompted whether or not we have an account. If we do, then we will be prompted to confirm credentials. Otherwise, we will be taken to an account creation screen.
![intro screen](https://github.com/SirChristianJ/DELI-ciousv2/blob/main/capstone2pic1.jpg)

Let us use an existing account located in `Accounts.csv` and create an order as that user!
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

**Let's build a sandwich!**



