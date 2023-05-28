package com.jsrdxzw.springsecurityawesome.config;

import com.jsrdxzw.springsecurityawesome.model.Authority;
import com.jsrdxzw.springsecurityawesome.model.Customer;
import com.jsrdxzw.springsecurityawesome.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author xuzhiwei
 * @date 2023/5/16 20:51
 */
@Service
public class EazyBankUserDetails implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password;
        List<? extends GrantedAuthority> authorities;
        List<Customer> customers = customerRepository.findByEmail(username);
        if (customers.size() == 0) {
            throw new UsernameNotFoundException("User Details not found for the user:" + username);
        } else {
            Customer customer = customers.get(0);
            userName = customer.getEmail();
            password = customer.getPwd();
            authorities = getGrantedAuthorities(customer.getAuthorities());
        }
        return new User(userName, password, authorities);
    }

    private List<? extends GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
        return authorities.stream().map(it -> new SimpleGrantedAuthority(it.getName())).toList();
    }
}
