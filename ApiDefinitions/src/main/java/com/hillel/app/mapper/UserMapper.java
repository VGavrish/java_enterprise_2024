package com.hillel.app.mapper;

import com.hillel.app.entity.User;
import openapitools.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "userName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    UserDto userToUserDto(User user);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "userName", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User userDtoToUser(UserDto userDto);
}