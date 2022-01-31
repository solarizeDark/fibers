package ru.fedusiv.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.fedusiv.repositories.AdminsRepository;

public class AdminsServiceImpl implements AdminsService {

    private AdminsRepository adminsRepository;
    private PasswordEncoder passwordEncoder;

    public AdminsServiceImpl(AdminsRepository adminsRepository, PasswordEncoder passwordEncoder) {
        this.adminsRepository = adminsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean check(String login, String password) {
        return passwordEncoder.matches(password, adminsRepository.getPassword(login));
    }

}
