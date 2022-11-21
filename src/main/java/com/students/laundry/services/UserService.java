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

//    private final BCryptPasswordEncoder bCryptPasswordEncoder;


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


    public void changeUserRole(String passNumber, String name, String surname, String room, String roleName) {
        User userFromDB = userRepository.findByPassNumber(passNumber).orElseThrow();
        userFromDB.setName(name);
        userFromDB.setSurname(surname);
        userFromDB.setRoom(room);
        if(roleName != null) {
            switch (roleName) {
                case ("2"):
                    roleName = "ROLE_ADMIN";
                    break;
                case ("3"):
                    roleName = "ROLE_MANAGER";
                    break;
                default:
                    roleName = "ROLE_USER";
                    break;
            }

            Role role = findByName(roleName).get();
            Collection<Role> roles = new ArrayList<>();
            roles.add(role);
            userFromDB.setRoles(roles);
        }
        userRepository.save(userFromDB);
    }


    @Transactional
    public void deleteByPassNumber(String passNumber) {
        userRepository.deleteByPassNumber(passNumber);
    }

    @Transactional
    public void savePasswordForUser(String passNumber, String password) throws UsernameNotFoundException {
        User userFromDB = userRepository.findByPassNumber(passNumber).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", passNumber)));

//        userFromDB.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(userFromDB);
    }

}