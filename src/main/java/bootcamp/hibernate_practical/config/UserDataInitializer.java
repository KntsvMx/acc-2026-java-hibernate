package bootcamp.hibernate_practical.config;

import bootcamp.hibernate_practical.entity.AppUser;
import bootcamp.hibernate_practical.entity.roles.Role;
import bootcamp.hibernate_practical.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findAppUserByUserName("user").isEmpty()) {
            AppUser user = new AppUser();
            user.setUserName("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setRole(Role.USER);
            user.setEnabled(true);

            userRepository.save(user);
        }

        if (userRepository.findAppUserByUserName("admin").isEmpty()) {
            AppUser user = new AppUser();
            user.setUserName("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setRole(Role.ADMIN);
            user.setEnabled(true);

            userRepository.save(user);
        }
    }
}
