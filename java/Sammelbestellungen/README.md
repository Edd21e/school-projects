# Sammelbestellungen — Group Order Management

A Java console application developed as part of my school-based IT education at BKGuT Aachen. The project practices object-oriented programming by modeling a group order with participant accounts, products, stock and discounts.

## Features

- Deposit, withdraw and transfer money between accounts.
- Place orders after checking the account, product, quantity, available stock and balance.
- Store up to 100 accepted orders in an array.
- Deduct the regular purchase price and reduce stock when an order is accepted.
- Calculate the total quantity ordered for each product.
- Finalize the group order, apply the lowest eligible unit price and refund the difference.
- Reject new orders after finalization and prevent repeated settlement.

## Discount rules

Each product defines a discounted unit price and a minimum combined order quantity. During settlement, that price is compared with discounts based on the individual order: 10% for at least 3 units and 25% for at least 5 units. The lowest eligible unit price is used; discounts are not added together.

## Classes

| Class | Responsibility |
| --- | --- |
| `Konto` | Account owner, balance, deposits, withdrawals and transfers. |
| `Ware` | Product name, regular and discounted prices, quantity threshold and stock. |
| `Bestellung` | Associates an account with a product and an ordered quantity. |
| `Bestellungverwaltung` | Validates and stores orders, totals quantities and settles payments. |
| `Main` | Runs a predefined demonstration with participants, products and orders. |

## Run the project

The included NetBeans configuration targets **Java 21**. Use a JDK 21 installation for the original project settings. No external libraries are required.

### NetBeans

1. Open this directory as an existing project in Apache NetBeans.
2. Select JDK 21 as the Java platform if prompted.
3. Run the project. The entry point is `Main`.

### Command line

From this project directory:

```sh
mkdir out
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

The application runs a fixed demonstration and prints the accepted or rejected orders, combined quantities, settlement details, remaining stock and final balances. It does not require interactive input.

## Learning focus

Classes and objects, encapsulation, object references, arrays, loops, conditions, input validation and cooperation between classes.

## Scope

This is an educational console project. Data is held in memory and is reset when the program exits. Monetary amounts use `double`, as in the original coursework implementation.

The original project files, German identifiers, comments and console messages have been preserved without modification. Only repository documentation and ignore rules have been added.
