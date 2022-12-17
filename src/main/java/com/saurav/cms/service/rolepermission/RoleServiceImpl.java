package com.saurav.cms.service.rolepermission;

import com.saurav.cms.entity.Role;
import com.saurav.cms.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(Long roleId){
        return roleRepository.findById(roleId).get();
    }
}
