package oopEstore;

import oopEstore.exceptions.IncorrectPasswordException;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private List<Customer> customers = new ArrayList<>();
    private int numberOfCustomers;

    public Admin(int id, String name, int age, String emailAddress, Address homeAddress, String password, String phone) {
        super(id, name, age, emailAddress, homeAddress, password, phone);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void registerCustomer(Customer customer) {
        customers.add(customer);
    }

    public void registerCustomer(String name, int age, String emailAddress, Address homeAddress, String password, String phone) {
        Customer newCustomer = new Customer(++numberOfCustomers, name, age, emailAddress, homeAddress, password, phone);
        customers.add(newCustomer);
    }

    public void removeCustomerAccount(int customerId, String password) {
        Customer customer = findCustomer(customerId);
        validate(customer, password);

        customers.remove(customer);
    }

    private static void validate(Customer customer, String password) {
        if (!customer.isCorrect(password)) throw new IncorrectPasswordException("Password provided is not correct");
    }

    private Customer findCustomer(int customerId) {
        for (Customer customer : customers) if (customer.getId() == customerId) return customer;
        throw new IllegalArgumentException("Customer with id " + customerId + " not found");
    }

    public List<Product> getProducts() {
        return Products.view();
    }

    public void addInitialInventory() {
        Product electronic = new Product(Products.generateProductId(), "Pixel", 350_000.0, "Android smartphone",
                ProductCategory.ELECTRONICS);
        Product grocery = new Product(Products.generateProductId(), "Magic", 2_000.0, "Chocolate", ProductCategory.GROCERIES);
        Product utensil = new Product(Products.generateProductId(), "Plate", 1_000.0, "Earthenware", ProductCategory.UTENSILS);
        Product clothing = new Product(Products.generateProductId(), "Levis", 20_000.0, "Jeans", ProductCategory.CLOTHING);

        Products.add(electronic, grocery, utensil, clothing);
    }
}
