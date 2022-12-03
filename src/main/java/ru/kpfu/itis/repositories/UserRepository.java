package ru.kpfu.itis.repositories;


import ru.kpfu.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    void save(User user);

    Optional<User> findById(Long id);

    void update(User user,Long id);

    void delete(Long id);

}
