package com.example.springecommerce.mapper;

import com.example.springecommerce.dto.RegistrationDTO;
import com.example.springecommerce.dto.UserLoginDto;
import com.example.springecommerce.model.Product;
import com.example.springecommerce.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    RegistrationDTO toUserRegistrationDto(User user);

    User toUser(RegistrationDTO registrationDTO);

    UserLoginDto toUserLoginDto(User user);

}
