package com.userregistration.userregistration.services;

import com.userregistration.userregistration.dto.UserInputDTO;
import com.userregistration.userregistration.dto.UserDTO;
import com.userregistration.userregistration.dto.UserUpdateDTO;
import com.userregistration.userregistration.entities.User;
import com.userregistration.userregistration.repositories.UserRepository;

import com.userregistration.userregistration.services.exceptions.BusinessException;
import com.userregistration.userregistration.services.exceptions.DatabaseException;
import com.userregistration.userregistration.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
       User entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found. "));
       return new UserDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> list = repository.findAll(pageable);
        return list.map(UserDTO::new);
    }

    @Transactional
    public UserDTO insert(UserInputDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Email already registered");
        }

        try {
            User user = new User();
            copyDtoToEntityIn(dto, user);
            user = repository.save(user);
            return new UserDTO(user);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Email already registered");
        }

    }



    @Transactional
    public UserDTO update(Long id, UserUpdateDTO dto) {
        try {

            User user = repository.getReferenceById(id);
            copyDtoToEntityUpdate(dto, user);
            user = repository.save(user);
            return new UserDTO(user);

        } catch (EntityNotFoundException e) {

            throw new ResourceNotFoundException("User not found.");

        } catch (DataIntegrityViolationException e) {

            throw new BusinessException("Email already registered");
        }



    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Invalid Id");
        }

        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity failure.\n" +
                    "Check whether there is a pending transaction.");
        }

    }

    private void copyDtoToEntityIn(UserInputDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());

    }
    private void copyDtoToEntityUpdate(UserUpdateDTO dto, User entity) {
        entity.setName(dto.getName());
              entity.setEmail(dto.getEmail());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPassword(dto.getPassword());
        }

    }




}
