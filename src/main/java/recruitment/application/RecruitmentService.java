package recruitment.application;

import recruitment.domain.*;

import recruitment.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class RecruitmentService {

    @Autowired
    private PersonRepository personRepo;

    public PersonDTO createPerson(String firstName, String lastName, String ssn, String mail, String password) throws FieldAlreadyExistException{
        if(isMailRegistered(mail)) {
            throw new FieldAlreadyExistException("Mail already exist");
        }
        if(isSsnRegistered(ssn)) {
            throw new FieldAlreadyExistException("Ssn already exist");
        }
        return personRepo.save(new Person(firstName, lastName, ssn, mail, password));
    }
 //hej
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
