package reader;

import model.Customer;

import java.util.List;

public interface Reader {

    List<Customer> getAllCustomers(String filePath);
}
