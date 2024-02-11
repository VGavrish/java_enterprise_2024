//package mapper;
//
//import dto.WorkoutDto;
//import entity.Workout;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper(componentModel = "spring")
//public interface WorkoutMapper {
//    WorkoutMapper INSTANCE = Mappers.getMapper(WorkoutMapper.class);
//
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "date", target = "date")
//    @Mapping(source = "notes", target = "notes")
//    @Mapping(source = "exerciseSets", target = "exerciseSetsDto")
//    WorkoutDto workoutToWorkoutDto(Workout workout);
//
//}
