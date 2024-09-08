package ci.digitalacademy.monetab.services.impl;

import ci.digitalacademy.monetab.models.RoleUser;
import ci.digitalacademy.monetab.repositories.RoleUserRepository;
import ci.digitalacademy.monetab.services.RoleUserService;
import ci.digitalacademy.monetab.services.dto.RoleUserDTO;
import ci.digitalacademy.monetab.services.mapper.RoleUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //Permet de créer les constructeurs des propriétés requit
@Slf4j
public class RoleUserServiceImpl implements RoleUserService {

    private final RoleUserRepository roleUserRepository;
    private final RoleUserMapper roleUserMapper;
    @Override
    public RoleUserDTO save(RoleUserDTO roleUserDTO) {
        log.debug("Request to save: {}", roleUserDTO );
        RoleUser roleUser = roleUserMapper.toEntity(roleUserDTO);
        roleUser = roleUserRepository.save(roleUser);
        return roleUserMapper.fromEntity(roleUser);
    }

    @Override
    public RoleUserDTO update(RoleUserDTO roleUserDTO) {
        return findOne(roleUserDTO.getId()).map(existingRoleUser ->{
            existingRoleUser.setNameRole(roleUserDTO.getNameRole());
            return save(roleUserDTO);
        }).orElseThrow(()-> new IllegalArgumentException());
    }

    @Override
    public Optional<RoleUserDTO> findOne(Long id) {
        log.debug("Resquest to get id: {}", id);
        return roleUserRepository.findById(id).map(roleUser -> {
            return roleUserMapper.fromEntity(roleUser);
        });
    }

    @Override
    public List<RoleUserDTO> initRoles(List<RoleUserDTO> roleUsers) {
        log.debug("Request to init roles {}", roleUsers);
        List<RoleUserDTO> roles = findAll();
        if (roles.isEmpty()){
            roleUsers.forEach(role->{
                save(role);
            });
        }
        return findAll();
    }

    @Override
    public List<RoleUserDTO> findAll() {
        return roleUserRepository.findAll().stream().map(roleUser -> {
            return roleUserMapper.fromEntity(roleUser);
        }).toList();
    }

    @Override
    public void delete(Long id) {
        roleUserRepository.deleteById(id);
    }
}
