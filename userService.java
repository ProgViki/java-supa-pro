package com.example.auth.service;

import com.example.auth.entity.AppUser;
import com.example.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public Optional<AppUser> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    public AppUser createUser(String username, String rawPassword) {
        AppUser u = AppUser.builder()
                .username(username)
                .password(encoder.encode(rawPassword))
                .role("USER")
                .build();
        return repo.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}

