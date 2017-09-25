package spittr.data;

import spittr.Spitter;

public interface SpitterRepository {
    Spitter save(Spitter unsaved);

    Spitter findByUsername(String username);

    Spitter findOne(long id);
}
