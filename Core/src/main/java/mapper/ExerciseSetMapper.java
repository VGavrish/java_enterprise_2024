package mapper;

import dto.ExerciseSetDto;
import entity.ExerciseSet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel= "spring")
public interface ExerciseSetMapper {
    ExerciseSetMapper INSTANCE = Mappers.getMapper(ExerciseSetMapper.class);

    @Mapping(source = "exerciseId", target = "excerciseId")
    @Mapping(source = "repetitions", target = "repetitions")
    @Mapping(source = "sets", target = "sets")
    @Mapping(source = "weight", target = "weight")
    @Mapping(source = "completed", target = "completed")
    ExerciseSetDto exerciseSetToExerciseSetDto(ExerciseSet exerciseSet);
}
