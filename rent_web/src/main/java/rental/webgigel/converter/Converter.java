package rental.webgigel.converter;

import rental.webgigel.dto.BaseDto;
import rental.core.module.BaseEntity;

public interface Converter<Model extends BaseEntity<Long>,Dto extends BaseDto> {
    Model convertDtoToModel(Dto dto);
    Dto convertModelToDto(Model model);
}
