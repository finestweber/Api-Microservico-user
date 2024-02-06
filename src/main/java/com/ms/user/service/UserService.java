package com.ms.user.service;

import com.ms.user.models.UserModel;
import com.ms.user.producers.USerProducer;
import com.ms.user.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    final UserRepository userRepository;
    final USerProducer uSerProducer;

    public UserService(UserRepository userRepository, USerProducer uSerProducer) {
        this.userRepository = userRepository;
        this.uSerProducer = uSerProducer;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        userModel = userRepository.save(userModel);
        uSerProducer.publishMessageEmail(userModel);
        return userModel;
    }
}