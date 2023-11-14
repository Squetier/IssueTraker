package geiffel.da4.issuetracker.user;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Qualifier("jpa")
public class UserJPAService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResourceNotFoundException("User", id);
        }
    }

    @Override
    public User create(User newUser) throws ResourceAlreadyExistsException {
       /* Pattern p = Pattern.compile("<script>[\\s\\S]*?<\\/script>");
        Matcher m = p.matcher(newUser.getNom());
        if (m.find()){
            throw new IllegalArgumentException();
        }*/
        Long id = newUser.getId();
        if(userRepository.existsById(id)){
            throw new ResourceAlreadyExistsException("User",id);
        }else{
            return userRepository.save(newUser);
        }
    }
    @Override
    public void update(Long id, User updatedUser) throws ResourceNotFoundException {
        Optional<User> updateuser = userRepository.findById(id);
        if (updateuser.isPresent()){
            User user = updateuser.get();
            userRepository.delete(user);
            userRepository.save(updatedUser);
        }else {
            throw new ResourceNotFoundException("User",id);
        }
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("User",id);
        }
    }
}
