package br.com.ifba.giovaneneves.sms.user.service;

//============================================{ IMPORTS }============================================//
import br.com.ifba.giovaneneves.sms.infrastructure.exceptions.user.UserNotFoundException;
import br.com.ifba.giovaneneves.sms.user.model.User;

import java.util.List;
//============================================{ END IMPORTS }============================================//

public interface IUserService {

    /**
     *
     * Inserts a user in the database
     * @param user The user to be added to the database.
     */
    void save(User user);

    /**
     * Deletes the user passed by parameter.
     * @param user The user to be removed from the database.
     */
    void delete(User user);

    /**
     *
     * @param user The user to be updated.
     */
    void update(User user);

    /**
     *
     * Search a user in the database
     * @param id The ID of the student to be searched.
     * @return user with the specified ID, null otherwise.
     */
    User findUserById(long id) throws UserNotFoundException;

    /**
     * List all users.
     * @return a list with all users in the database.
     */
    List<User> findAll();
}
