package me.latanks.ecommerce_api.services.impl;

import jakarta.transaction.Transactional;
import me.latanks.ecommerce_api.exception.impl.UserNotFoundedException;
import me.latanks.ecommerce_api.models.User;
import me.latanks.ecommerce_api.models.dto.mapper.UserMapper;
import me.latanks.ecommerce_api.models.dto.request.CreateUserDTO;
import me.latanks.ecommerce_api.models.dto.request.UpdateUserDTO;
import me.latanks.ecommerce_api.models.dto.response.CreatedUserDTO;
import me.latanks.ecommerce_api.models.enums.Roles;
import me.latanks.ecommerce_api.repositories.IUserRepository;
import me.latanks.ecommerce_api.security.UserDetailsServiceImpl;
import me.latanks.ecommerce_api.services.IUserService;
import me.latanks.ecommerce_api.utils.AuthenticationUtil;
import me.latanks.ecommerce_api.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository  userRepository;
    @Autowired
    private UserDetailsServiceImpl  userDetailsService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public CreatedUserDTO create(CreateUserDTO createUserDTO){
        this.userRepository.findByUsername(createUserDTO.username()).ifPresent(
                user -> {
                    throw new RuntimeException("Username already exists!");
                }
        );

        User user = this.userMapper.toEntity(createUserDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(Roles.USER);

        return this.userMapper.toDTO(this.userRepository.save(user));
    }

    @Transactional
    public CreatedUserDTO update(UpdateUserDTO  updateUserDTO){

        User user = this.userRepository.findByUsername(AuthenticationUtil.getInstance().getUsername()).orElseThrow(
                () -> new UserNotFoundedException("Username not found!")
        );

        PropertiesUtil.copyProperties(user, updateUserDTO);

        return this.userMapper.toDTO(this.userRepository.save(user));
    }

    @Transactional
    public void delete(Long id){
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundedException("User not found!")
        );

        this.userRepository.delete(user);
    }

    @Transactional
    public void delete(){
        this.delete(AuthenticationUtil.getInstance().getUser().getId());
    }


    public CreatedUserDTO getMyProfile(){
        User user = this.userRepository.findByUsername(AuthenticationUtil.getInstance().getUsername()).orElseThrow(
                () -> new UserNotFoundedException("Username not found!")
        );

        return this.userMapper.toDTO(user);
    }

    public CreatedUserDTO findByUsername(String username){
        User user = this.userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundedException("User not found!")
        );

        return  this.userMapper.toDTO(user);
    }

    public CreatedUserDTO findByEmail(String email){
        User user = this.userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundedException("Username not found!")
        );

        return  this.userMapper.toDTO(user);
    }

    public CreatedUserDTO findById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundedException("Username not found!")
        );

        return  this.userMapper.toDTO(user);
    }
}
