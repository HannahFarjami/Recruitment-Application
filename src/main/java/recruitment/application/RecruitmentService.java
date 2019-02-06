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

    public PersonDTO createPerson(String firstName, String lastName, String ssn, String mail, String password) {
        return personRepo.save(new Person(firstName, lastName, ssn, mail, password));
    }

    /*public ConversionDTO findConversion(String fromto) {
        return converterRepo.findConversionByFromto(fromto);
    }

    public void setNewRate(double value, String fromto) {
        converterRepo.setNewRate(value, fromto);

    }
    public void setNewCount(int count, String fromto) {
        converterRepo.setNewCount(count, fromto);
    }

    public int countSum() {
        return converterRepo.countSum();
    } */


}
