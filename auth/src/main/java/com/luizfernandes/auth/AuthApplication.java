package com.luizfernandes.auth;

import com.luizfernandes.auth.entity.Permission;
import com.luizfernandes.auth.entity.User;
import com.luizfernandes.auth.repository.PermissionRepository;
import com.luizfernandes.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, PermissionRepository permissionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        return args -> {
            initUsers(userRepository, permissionRepository, bCryptPasswordEncoder);
        };
    }

    private void initUsers(UserRepository userRepository, PermissionRepository permissionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        Permission permission = null;
        Permission findPermission = permissionRepository.findByDescription("Admin");
        if (findPermission == null) {
            permission = new Permission();
            permission.setDescription("Admin");
            permission = permissionRepository.save(permission);
        } else {
            permission = findPermission;
        }

        User admin = new User();
        admin.setUserName("luiz");
        admin.setAccountNonExpired(true);
        admin.setAccountNonLocked(true);
        admin.setCredentialsNonExpired(true);
        admin.setEnabled(true);
        admin.setPassword(bCryptPasswordEncoder.encode("123456"));
        admin.setPermissions(Arrays.asList(permission));

        User find = userRepository.findByUserName("luiz");
        if (find == null) {
            userRepository.save(admin);
        }
    }


}
