package tn.esprit.spring.services.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.interfaces.IRoleService;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repositories.RoleRepository;

@Service
public class RoleService implements IRoleService{
    @Autowired
    private RoleRepository roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}

