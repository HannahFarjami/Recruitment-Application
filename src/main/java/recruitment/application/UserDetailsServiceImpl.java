package recruitment.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import recruitment.domain.FieldAlreadyExistException;
import recruitment.domain.Person;
import recruitment.domain.PersonDTO;
import recruitment.domain.Role;
import recruitment.repository.PersonRepository;
import recruitment.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * This class defines all operations that can be done in the domain layer regarding a user
 */
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Gets the currently logged in user
     * @return email of the current logged in user, if there are no currently logged in user null will be returned
     */
    public String findLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }
        return null;
    }

    /**
     * Creates a new applicant
     * @param firstName of the applicant
     * @param lastName of the applicant
     * @param ssn the applicants ssn
     * @param mail the applicants mail
     * @param password the applicants password
     * @return newly created Person
     * @throws FieldAlreadyExistException if the mail or ssn already exist in the database
     */
    public PersonDTO createPerson(String firstName, String lastName, String ssn, String mail, String password) throws FieldAlreadyExistException {
        if(isMailRegistered(mail)) {
            throw new FieldAlreadyExistException("Mail already exist");
        }
        if(isSsnRegistered(ssn)) {
            throw new FieldAlreadyExistException("Ssn already exist");
        }
        Person person = new Person(firstName, lastName, ssn, mail, passwordEncoder.encode(password));
        Role role = roleRepository.findById(1);
        person.setRole(role);
        return personRepository.save(person);
    }

    /**
     * Checks if the mail address is already registered to a user
     * @param mail the mail of the applicant trying to register
     * @return true if the mail already is registered otherwise false
     */
    private boolean isMailRegistered(String mail) {
        PersonDTO person = personRepository.findByMail(mail);
        if(person == null)
            return false;
        return true;
    }

    /**
     * Checks if the ssn address is already registered to a user
     * @param ssn the ssn of the applicant trying to register
     * @return true if the ssn already is registered otherwise false
     */
    private boolean isSsnRegistered(String ssn) {
        PersonDTO person = personRepository.findBySsn(ssn);
        if(person == null)
            return false;
        return true;
    }

    /**
     * Locates the user by the username
     * @param username the users mail
     * @return User
     * @throws UsernameNotFoundException if the user can't be find.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByMail(username);

        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        grantedAuthoritySet.add(new SimpleGrantedAuthority(person.getRole().getName()));

        return new User(person.getMail(),person.getPassword(),grantedAuthoritySet);
    }
}
