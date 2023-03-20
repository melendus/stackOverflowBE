package com.utcn.demo.service;

import com.utcn.demo.entity.User;
import com.utcn.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<User> retrieveUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(Long cnp) {

        Optional<User> user = userRepository.findById(cnp);

        return user.orElse(null);
    }

    public Optional<User> getUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        return user;
    }

    public long deleteById(Long id) {
        return userRepository.deleteByUserId(id);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void updateUser(User userUpdated) {
        Optional<User> existingUser = userRepository.findByUserId(userUpdated.getUserId());

        existingUser.ifPresent(user -> {
                    User userToUpdate = existingUser.get();
                    if (userUpdated.getFirstName() != null) {
                        userToUpdate.setFirstName(userUpdated.getFirstName());
                    }
                    if (userUpdated.getLastName() != null) {
                        userToUpdate.setLastName(userUpdated.getLastName());
                    }
                    if (userUpdated.getEmail() != null) {
                        userToUpdate.setEmail(userUpdated.getEmail());
                    }
                    if (userUpdated.getRole() != null) {
                        userToUpdate.setRole(userUpdated.getRole());
                    }
                    userRepository.save(userToUpdate);
                }
        );
    }
}
