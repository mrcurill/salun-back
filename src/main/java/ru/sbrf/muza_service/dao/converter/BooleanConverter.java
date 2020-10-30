package ru.sbrf.muza_service.dao.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean, Character> {

    @Override
    public Character convertToDatabaseColumn(Boolean bool) {
        if(null == bool)
            return null;

        return bool ? 'Y' : 'N';
    }

    @Override
    public Boolean convertToEntityAttribute(Character character) {
        if( null == character || Character.isSpaceChar(character) )
            return null;

        return character.equals('Y');
    }
}
