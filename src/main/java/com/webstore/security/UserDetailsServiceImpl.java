package com.webstore.security;

import com.webstore.dao.UserDao;
import com.webstore.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private String userPassword;
    private Integer userRole;
    private Integer userStatus;

    @Autowired
    UserDao userDao;



    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        User user = userDao.getUserByUsername(username);

        if (user != null) {
            userPassword = user.getPassword();
            userRole = user.getRole();
            userStatus = user.getStatus();

            if (userStatus == 1){
                if (userRole == 1) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                    return new UserLogin(username, userPassword, authorities, user.getId());
                }
                if (userRole == 2) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                    return new UserLogin(username, userPassword, authorities, user.getId());
                }
            }
            throw new UsernameNotFoundException("Account is inactive!");
        }
        throw new UsernameNotFoundException("User not found!");
    }
}
