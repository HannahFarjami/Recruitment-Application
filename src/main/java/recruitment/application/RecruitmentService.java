package recruitment.application;

import org.springframework.security.crypto.password.PasswordEncoder;
import recruitment.domain.*;

import recruitment.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import recruitment.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class RecruitmentService {

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PersonDTO createPerson(String firstName, String lastName, String ssn, String mail, String password) throws FieldAlreadyExistException{
        if(isMailRegistered(mail)) {
            throw new FieldAlreadyExistException("Mail already exist");
        }
        if(isSsnRegistered(ssn)) {
            throw new FieldAlreadyExistException("Ssn already exist");
        }
        Person person = new Person(firstName, lastName, ssn, mail, passwordEncoder.encode(password));
        Role role = roleRepository.findById(1);
        person.setRole(role);
        return personRepo.save(person);
    }

    private boolean isMailRegistered(String mail) {
        PersonDTO person = personRepo.findByMail(mail);
        if(person == null)
            return false;
        return true;
    }

    private boolean isSsnRegistered(String ssn) {
        PersonDTO person = personRepo.findBySsn(ssn);
        if(person == null)
            return false;
        return true;
    }


}
