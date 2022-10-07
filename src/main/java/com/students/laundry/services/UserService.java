package com.students.laundry.services;

import com.students.laundry.entities.Role;
import com.students.laundry.entities.User;
import com.students.laundry.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    public Optional<User> findByPassNumber(String passNumber) {
        return userRepository.findByPassNumber(passNumber);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String passNumber) throws UsernameNotFoundException {
        User user = findByPassNumber(passNumber).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", passNumber)));
        return new org.springframework.security.core.userdetails.User(user.getPassNumber(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }

    public void deleteByPassNumber(String passNumber) {
        userRepository.deleteByPassNumber(passNumber);
    }

}