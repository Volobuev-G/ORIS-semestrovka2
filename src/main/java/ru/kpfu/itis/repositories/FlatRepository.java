package ru.kpfu.itis.repositories;

import ru.kpfu.itis.models.Flat;

import java.util.List;
import java.util.Optional;

public interface FlatRepository {
    List<Flat> findAll();

    void save(Flat flat);

    Optional<Flat> findById(Long id);

    void update(Flat flatNew, Long id);

    void delete(Long id);

}
