package me.afua.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RunFirst implements CommandLineRunner {

    @Autowired
    RoleRepo roleRepository;

    @Override
    public void run(String... strings) throws Exception {
        UserRole r = new UserRole();
        r.setRole("USER");
        roleRepository.save(r);

        r = new UserRole();
        r.setRole("ADMIN");
        roleRepository.save(r);


    }
}
