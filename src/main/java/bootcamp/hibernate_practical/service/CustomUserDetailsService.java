package bootcamp.hibernate_practical.service;

import bootcamp.hibernate_practical.entity.AppUser;
import bootcamp.hibernate_practical.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {

        AppUser user = userRepository.findAppUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found" + username));

        return User
                .withUsername(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .disabled(!user.isEnabled())
                .build();
    }
}
