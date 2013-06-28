package fr.xebia.xke.repository;

import fr.xebia.xke.model.User;

public interface UserRepository extends BaseRepository<User> {

    User findByName(String name);

}
