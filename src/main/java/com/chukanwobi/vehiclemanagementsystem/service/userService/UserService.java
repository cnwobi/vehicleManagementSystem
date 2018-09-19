package com.chukanwobi.vehiclemanagementsystem.service.userService;

import com.chukanwobi.vehiclemanagementsystem.model.Customer;
import com.chukanwobi.vehiclemanagementsystem.model.Staff;
import com.chukanwobi.vehiclemanagementsystem.repository.CustomerRepository;
import com.chukanwobi.vehiclemanagementsystem.repository.StaffRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements UserDetailsService {
    private CustomerRepository customerRepository;
    private StaffRepository staffRepository;

    public UserService(CustomerRepository customerRepository, StaffRepository staffRepository) {
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Staff> optionalStaff = staffRepository.findByUsername(s);
        Optional<Customer> optionalCustomer = customerRepository.findByUsername(s);

        if (optionalStaff.isPresent()){
            Staff staff = optionalStaff.get();
            UserDetails userDetails = new User(staff.getUsername(),staff.getPassword(),staff.isEnabled(),staff.isAccountNonExpired(),staff.isCredentialsNonExpired(),staff.isAccountNonLocked(),staff.getAuthorities());
            return userDetails;
        }
        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            UserDetails userDetails = new User(customer.getUsername(),customer.getPassword(),customer.isEnabled(),customer.isAccountNonExpired(),customer.isCredentialsNonExpired(),customer.isAccountNonLocked(),customer.getAuthorities());
            return userDetails;
        }
        throw new UsernameNotFoundException("Username "+s+" does not exist");

    }

    public boolean isUsernameUnique(String s){
        Optional<Staff> optionalStaff = staffRepository.findByUsername(s);
        Optional<Customer> optionalCustomer = customerRepository.findByUsername(s);
        if(optionalCustomer.isPresent()|| optionalStaff.isPresent()){
            return false;        }
        return true;
    }
}
