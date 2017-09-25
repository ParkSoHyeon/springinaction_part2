package spittr.data;

import spittr.domain.Spitter;

import java.util.List;

public interface SpitterRepository {
    Spitter save(Spitter unsaved);

    Spitter findByUsername(String username);

    public Spitter findOne(long id);

    public List<Spitter> findAll();

}
