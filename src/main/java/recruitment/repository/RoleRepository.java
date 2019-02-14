package recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import recruitment.domain.Role;

/**
 * Contains all database access regarding Roles
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface RoleRepository extends JpaRepository<Role,Integer> {

    /**
     * Find role by id
     * @param id for the role
     * @return Role if found otherwise null
     */
    Role findById(int id);
}
