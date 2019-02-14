package recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import recruitment.domain.Person;

/**
 * Contains all database access regarding Persons
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface PersonRepository extends JpaRepository<Person, String> {

    /**
     * Find person by mail address
     * @param mail for the person
     * @return Person if found otherwise null
     */

    Person findByMail(String mail);

    /**
     * Find person by ssn
     * @param ssn for the person
     * @return Person if found otherwise null
     */
    Person findBySsn(String ssn);

    @Override
    Person save(Person person);


}
