package reader;

import model.Customer;

import java.util.List;

public interface Reader {

    public List<Customer> getAllCustomers(String filePath);
}
