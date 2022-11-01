package com.students.laundry.services;

import com.students.laundry.entities.Role;
import com.students.laundry.entities.User;
import com.students.laundry.repositories.RoleRepository;
import com.students.laundry.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public Optional<User> findByPassNumber(String passNumber) {
        return userRepository.findByPassNumber(passNumber);
    }

    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
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

    @Transactional
    public void saveOrUpdate(String passNumber, String name, String surname, String room) {
        User user = new User();
        user.setPassNumber(passNumber);
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(null);
        user.setRoom(room);
        Role role = findByName("ROLE_USER").get();
        Collection<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Transactional
    public void deleteByPassNumber(String passNumber) {
        userRepository.deleteByPassNumber(passNumber);
    }

}