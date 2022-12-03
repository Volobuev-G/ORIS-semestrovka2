package ru.kpfu.itis.repositories;

import ru.kpfu.itis.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    List<Comment> findAll();

    void save(Comment comment);

    Optional<Comment> findById(Long id);

    void update(Comment commentNew, Long id);

    void delete(Long id);
}
