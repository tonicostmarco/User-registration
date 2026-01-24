package com.aquecimento.aquecimento.services;

import com.aquecimento.aquecimento.dto.UserInputDTO;
import com.aquecimento.aquecimento.dto.UserDTO;
import com.aquecimento.aquecimento.dto.UserUpdateDTO;
import com.aquecimento.aquecimento.entities.User;
import com.aquecimento.aquecimento.repositories.UserRepository;

import com.aquecimento.aquecimento.services.exceptions.DataBaseException;
import com.aquecimento.aquecimento.services.exceptions.ResourceNotFoundException;
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
       User entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Esse usuario nao existe. "));
       return new UserDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> list = repository.findAll(pageable);
        return list.map(UserDTO::new);
    }

    @Transactional
    public UserDTO insert(UserInputDTO dto) {
        User user = new User();
        copyDtoToEntityIn(dto, user);

        user = repository.save(user);

        return new UserDTO(user);
    }



    @Transactional
    public UserDTO update(Long id, UserUpdateDTO dto) {
        try {

            User user = repository.getReferenceById(id);
            copyDtoToEntityUpdate(dto, user);

            user = repository.save(user);
            return new UserDTO(user);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Usuário não encontrado. ");
        }



    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("id inválido. ");
        }

        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade referencial. Verifique se existe uma transação pendente.");
        }

    }

    private void copyDtoToEntityIn(UserInputDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());

    }
    private void copyDtoToEntityUpdate(UserUpdateDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());

    }




}
