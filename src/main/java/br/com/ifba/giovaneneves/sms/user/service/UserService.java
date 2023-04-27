package br.com.ifba.giovaneneves.sms.user.service;

//============================================{ IMPORTS }============================================//
import br.com.ifba.giovaneneves.sms.infrastructure.exceptions.user.UserNotFoundException;
import br.com.ifba.giovaneneves.sms.user.dao.UserRepository;
import br.com.ifba.giovaneneves.sms.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//============================================{ END IMPORTS }============================================//

@Service
public class UserService implements IUserService{

    //============================================{ ATTRIBUTES }============================================//
    public final String USER_NOT_FOUND = "The specified user does not exist in the database.";

    @Autowired
    UserRepository userRepository;

    //============================================{ METHODS }============================================//

    /**
     *
     * Inserts a user in the database
     * @param user The user to be added to the database.
     */
    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

    /**
     * Deletes the user passed by parameter.
     * @param user The user to be removed from the database.
     */
    @Override
    public void delete(User user) {

        userRepository.delete(user);
    }

    /**
     *
     * @param user The user to be updated.
     */
    @Override
    public void update(User user) {

        userRepository.save(user);
    }

    /**
     *
     * Search a user in the database
     * @param id The ID of the student to be searched.
     * @return user with the specified ID, null otherwise.
     */
    @Override
    public User findUserById(long id) throws UserNotFoundException {

        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent())
            throw new UserNotFoundException(USER_NOT_FOUND);

        User user = userOptional.get();

        return user;
    }

    /**
     * List all users.
     * @return a list with all users in the database.
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
