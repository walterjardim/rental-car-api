package br.org.rentalcarapi.infra.gateways;

import br.org.rentalcarapi.domain.entity.Car;
import br.org.rentalcarapi.infra.persistence.entity.CarEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CarEntityMapperTest {

    @InjectMocks
    private CarEntityMapper carEntityMapper;

    @Test
    public void testToEntityWithSuccess() {
        Car carDomain = new Car(2023, "112", "model", "color");
        CarEntity carEntity = this.carEntityMapper.toEntity(carDomain);
        Assertions.assertNotNull(carEntity);
        Assertions.assertEquals(carEntity.getManufactureYear(), carDomain.getManufactureYear());
    }

    @Test
    public void testToEntityListWithSuccess() {
        List<Car> cars = Collections.singletonList(
                new Car(2023, "112", "model", "color"));

        List<CarEntity> carEntities = this.carEntityMapper.toEntityList(cars);

        Assertions.assertFalse(carEntities.isEmpty());
    }

    @Test
    public void testToDomainObjectWithSuccess() {
        CarEntity carEntity = CarEntity
                .builder()
                .id(1L)
                .color("White")
                .model("model")
                .licensePlate("11")
                .manufactureYear(2023)
                .build();
        Car car = this.carEntityMapper.toDomainObject(carEntity);

        Assertions.assertNotNull(car);
        Assertions.assertEquals(car.getManufactureYear(), carEntity.getManufactureYear());
    }

    @Test
    public void testToDomainObjectListWithSuccess() {
        List<CarEntity> carEntities = Collections.singletonList(
                CarEntity
                        .builder()
                        .id(1L)
                        .color("White")
                        .model("model")
                        .licensePlate("11")
                        .manufactureYear(2023)
                        .build()
        );
        List<Car> cars = this.carEntityMapper.toDomainObjectList(carEntities);
        Assertions.assertFalse(cars.isEmpty());
    }
}
