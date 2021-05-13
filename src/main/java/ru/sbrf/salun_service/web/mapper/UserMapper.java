package ru.sbrf.salun_service.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.sbrf.salun_service.dao.entity.EUser;
import ru.sbrf.salun_service.web.dto.UserDto;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mappings({})
    UserDto mapToUserDto(EUser user);
    List<UserDto> mapToListUserDto(List<EUser> users);

}
