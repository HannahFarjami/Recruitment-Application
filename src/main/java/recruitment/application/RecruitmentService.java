package recruitment.application;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

/**
 * This class defines the operations that can be done in the domain layer regarding
 */
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class RecruitmentService {

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private RoleRepository roleRepository;


}
