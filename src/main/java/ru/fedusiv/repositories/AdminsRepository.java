package ru.fedusiv.repositories;

import ru.fedusiv.models.Admin;

public interface AdminsRepository {

    String getPassword(String login);

}
