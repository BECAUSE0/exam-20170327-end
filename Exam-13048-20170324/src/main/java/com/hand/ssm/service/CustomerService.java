package com.hand.ssm.service;

import java.util.List;
import com.hand.ssm.dto.Address;
import com.hand.ssm.dto.Customer;
import com.hand.ssm.dto.Page;
import com.hand.ssm.dto.PageResult;

public interface CustomerService {
	
	public List<Customer> select(Customer cust);
	public List<Customer> login(String firstName);
    public void insert(Customer cust);
    public void delete(int custId);
    public void update(Customer cust);
    public Customer edit(int custId);
    public List<Address> site();
    public PageResult selectByPage(Page page);
}
