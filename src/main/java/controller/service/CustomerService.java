package controller.service;

import javafx.collections.ObservableList;
import model.CustomerDto;

import java.time.LocalDate;

public interface CustomerService {

    void addCustomer(String id, String firstName, String lastName, String email,String city, String address, int phone, LocalDate regDate);

    void deleteCustomer(String id);

    void updateCustomer(String id, String firstName, String lastName, String email,String city, String address, Integer phone, LocalDate regDate);

    ObservableList<CustomerDto> getAllCustomers();
}
