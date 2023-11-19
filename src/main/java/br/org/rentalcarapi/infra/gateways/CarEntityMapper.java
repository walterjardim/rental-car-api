package br.org.rentalcarapi.infra.gateways;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;

import br.org.rentalcarapi.domain.entity.Car;
import br.org.rentalcarapi.infra.persistence.entity.CarEntity;

public class CarEntityMapper {
    CarEntity toEntity(Car carDomainObject) {
        return CarEntity
        .builder()
        .color(carDomainObject.getColor())
        .licensePlate(carDomainObject.getLicensePlate())
        .model(carDomainObject.getModel())
        .manufactureYear(carDomainObject.getManufactureYear())
        .build();
    }

    List<CarEntity> toEntityList(List<Car> cars) {
        List<CarEntity> entityList = new ArrayList<>();
        for (Car car : cars) {
            entityList.add(this.toEntity(car));
        }

        return entityList;
    }

    Car toDomainObject(CarEntity carEntity) {
        return new Car(carEntity.getManufactureYear(), carEntity.getLicensePlate(), carEntity.getModel(), carEntity.getColor());
    }

    List<Car> toDomainObjectList(List<CarEntity> carEntities) {
        List<Car> domainObjectList = new ArrayList<>();
        for (CarEntity carEntity : carEntities) {
            domainObjectList.add(this.toDomainObject(carEntity));
        }

        return domainObjectList;

    }
}
