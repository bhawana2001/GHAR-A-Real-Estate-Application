package com.ghar.ghar.service;

import com.ghar.ghar.entity.User;
import com.ghar.ghar.exception.CustomException;
import com.ghar.ghar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
            return userRepository.findById(id).orElseThrow(()-> new CustomException("User not found with ID: "+id));
    }

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User updateUserProfile(Long id, User updatedUser) {
        User existingUser=userRepository.findById(id).orElseThrow(()-> new CustomException("User not found with ID: "+id));
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setProfilePicture(updatedUser.getProfilePicture());
        existingUser.setUsername(updatedUser.getUsername());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }else{
            throw new CustomException("User not found with ID: "+id);
        }
    }
}
