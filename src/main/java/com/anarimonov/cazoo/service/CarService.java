package com.anarimonov.cazoo.service;

import com.anarimonov.cazoo.dto.CarDto;
import com.anarimonov.cazoo.dto.CarSearchDto;
import com.anarimonov.cazoo.entity.Car;
import com.anarimonov.cazoo.entity.enums.*;
import com.anarimonov.cazoo.repository.AttachmentRepository;
import com.anarimonov.cazoo.repository.CarRepository;
import com.anarimonov.cazoo.repository.MakerRepository;
import com.anarimonov.cazoo.repository.ModelRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {
    public final CarRepository carRepository;
    private final MakerRepository makerRepository;
    private final AttachmentRepository attachmentRepository;
    private final EntityManager entityManager;
    private final ModelRepository modelRepository;

    public HttpEntity<?> getCars() {
        List<Car> cars = carRepository.findAll();
        return ResponseEntity.ok(cars);
    }

    public HttpEntity<?> getCarById(long id) {
        return ResponseEntity.ok(carRepository.findById(id));
    }

    public HttpEntity<?> getCarsByFilter(CarSearchDto carSearchDto, boolean count) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = builder.createQuery(Car.class);
        Root<Car> root = criteriaQuery.from(Car.class);
        List<Predicate> searchCriteria = new ArrayList<>();

        addEqualCriteria(builder, searchCriteria, root.get("maker"), carSearchDto.getMakerId(), makerRepository);
        addEqualCriteria(builder, searchCriteria, root.get("model"), carSearchDto.getModelId(), modelRepository);

        addRangeCriteria(builder, searchCriteria, root.get("price"), carSearchDto.getMinPrice(), carSearchDto.getMaxPrice());
        addRangeCriteria(builder, searchCriteria, root.get("engine"), carSearchDto.getMinEngine(), carSearchDto.getMaxEngine());
        addRangeCriteria(builder, searchCriteria, root.get("mileage"), carSearchDto.getMinMileage(), carSearchDto.getMaxMileage());
        addRangeCriteria(builder, searchCriteria, root.get("manufacturedYear"), carSearchDto.getMinManufacturedYear(), carSearchDto.getMaxManufacturedYear());

        addInCriteria(searchCriteria, root.get("gearbox"), carSearchDto.getGearbox());
        addInCriteria(searchCriteria, root.get("bodyType"), carSearchDto.getBodyType());
        addInCriteria(searchCriteria, root.get("color"), carSearchDto.getColor());
        addInCriteria(searchCriteria, root.get("fuelType"), carSearchDto.getFuelType());

        Predicate[] array = searchCriteria.toArray(new Predicate[0]);
        criteriaQuery.select(root).where(builder.and(array));
        List<Car> cars = entityManager.createQuery(criteriaQuery).getResultList();
        return ResponseEntity.ok(count ? cars.size() : cars);
    }

    public HttpEntity<?> addCar(CarDto carDto) {
        try {
            Car car = carSetter(null, carDto);
            carRepository.save(car);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.valueOf(201)).body("success");
    }

    public HttpEntity<?> editCar(long id, CarDto carDto) {
        try {
            Car car = carSetter(id, carDto);
            carRepository.save(car);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("success");
    }

    public HttpEntity<?> deleteCar(long id) {
        carRepository.deleteById(id);
        return ResponseEntity.ok("success");
    }

    private Car carSetter(Long id, CarDto carDto) {
        Car car = id == null ? new Car() : carRepository.findById(id).orElseThrow();
        List<Feature> features = carDto.getFeatures().stream()
                .map(Feature::valueOf)
                .collect(Collectors.toList());
        car.setColor(carDto.getColor() != null ? Color.valueOf(carDto.getColor()) : car.getColor());
        car.setEngine(carDto.getEngine() != null ? carDto.getEngine() : car.getEngine());
        car.setFeatures(features.isEmpty() ? car.getFeatures() : features);
        car.setGearbox(carDto.getGearbox() != null ? Gearbox.valueOf(carDto.getGearbox()) : car.getGearbox());
        car.setBodyType(carDto.getBodyType() != null ? BodyType.valueOf(carDto.getBodyType()) : car.getBodyType());
        car.setFuelType(carDto.getFuelType() != null ? FuelType.valueOf(carDto.getFuelType()) : car.getFuelType());
        car.setManufacturedYear(carDto.getManufacturedYear() != null ? carDto.getManufacturedYear() : car.getManufacturedYear());
        car.setMaker(makerRepository.findById(carDto.getMakerId()).orElse(car.getMaker()));
        car.setMileage(carDto.getMileage() != null ? carDto.getMileage() : car.getMileage());
        car.setModel(modelRepository.findById(carDto.getModelId()).orElse(car.getModel()));
        car.setPrice(carDto.getPrice() != null ? carDto.getPrice() : car.getPrice());
        car.setAttachments(carDto.getPhotosIds() != null ? attachmentRepository.findAllById(carDto.getPhotosIds()) : car.getAttachments());
        return car;
    }


    private void addEqualCriteria(CriteriaBuilder builder, List<Predicate> searchCriteria, Path<Object> path, Long value, JpaRepository<?, Long> repository) {
        if (value != null) {
            Object entity = repository.findById(value).orElseThrow();
            searchCriteria.add(builder.equal(path, entity));
        }
    }

    private <T extends Comparable<? super T>> void addRangeCriteria(CriteriaBuilder builder, List<Predicate> searchCriteria, Path<T> path, T minValue, T maxValue) {
        if (minValue != null) {
            searchCriteria.add(builder.greaterThanOrEqualTo(path, minValue));
        }
        if (maxValue != null) {
            searchCriteria.add(builder.lessThanOrEqualTo(path, maxValue));
        }
    }

    private void addInCriteria(List<Predicate> searchCriteria, Path<Object> path, List<String> values) {
        if (values != null && !values.isEmpty()) {
            searchCriteria.add(path.in(values));
        }
    }
}
