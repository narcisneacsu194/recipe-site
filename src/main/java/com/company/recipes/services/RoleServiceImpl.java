package com.company.recipes.services;

import com.company.recipes.dao.RoleDao;
import com.company.recipes.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findOne(Long id) {
        return roleDao.findOne(id);
    }
}
