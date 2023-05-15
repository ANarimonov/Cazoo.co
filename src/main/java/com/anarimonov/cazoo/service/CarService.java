package com.anarimonov.cazoo.service;

import com.anarimonov.cazoo.dto.CarDto;
import com.anarimonov.cazoo.dto.CarSearchDto;
import com.anarimonov.cazoo.entity.Car;
import com.anarimonov.cazoo.entity.enums.*;
import com.anarimonov.cazoo.projection.CarProjection;
import com.anarimonov.cazoo.repository.AttachmentRepository;
import com.anarimonov.cazoo.repository.CarRepository;
import com.anarimonov.cazoo.repository.MakerRepository;
import com.anarimonov.cazoo.repository.ModelRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    public final CarRepository carRepository;
    private final MakerRepository makerRepository;
    private final AttachmentRepository attachmentRepository;
    private final EntityManager entityManager;
    private final ModelRepository modelRepository;

    public HttpEntity<?> getCars() {
        List<CarProjection> cars = carRepository.getAll();
        return ResponseEntity.ok(cars);
    }

    public HttpEntity<?> getCarById(long id) {
        return ResponseEntity.ok(carRepository.getById(id));
    }

    public HttpEntity<?> addCar(CarDto carDto) {
        Car car = new Car();
        try {
            car.setId(carDto.getId());
            car.setColor(Color.valueOf(carDto.getColor()));
            car.setEngine(carDto.getEngine());
            List<Feature> features = new ArrayList<>();
            for (String feature : carDto.getFeatures())
                features.add(Feature.valueOf(feature));
            car.setFeatures(features);
            car.setGearbox(Gearbox.valueOf(carDto.getGearbox()));
            car.setBodyType(BodyType.valueOf(carDto.getBodyType()));
            car.setFuelType(FuelType.valueOf(carDto.getFuelType()));
            car.setManufacturedYear(carDto.getManufacturedYear());
            car.setMaker(makerRepository.findById(carDto.getMakerId()).orElse(null));
            car.setMileage(carDto.getMileage());
            car.setModel(modelRepository.findById(carDto.getModelId()).orElse(null));
            car.setPrice(carDto.getPrice());
            car.setAttachments(attachmentRepository.findAllById(carDto.getPhotosIds()));
            carRepository.save(car);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.valueOf(201)).body("success");
    }

    public HttpEntity<?> deleteCar(String id) {
        carRepository.deleteById(Long.parseLong(id));
        return ResponseEntity.ok("success");
    }

    public HttpEntity<?> getCars(CarSearchDto carSearchDto, boolean count) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = builder.createQuery(Car.class);
        Root<Car> root = criteriaQuery.from(Car.class);
        List<Predicate> searchCriteria = new ArrayList<>();
        Long makerId = carSearchDto.getMakerId();
        Long modelId = carSearchDto.getModelId();
        Long maxPrice = carSearchDto.getMaxPrice();
        Long minPrice = carSearchDto.getMinPrice();
        Double minEngine = carSearchDto.getMinEngine();
        Double maxEngine = carSearchDto.getMaxEngine();
        Integer minMileage = carSearchDto.getMinMileage();
        Integer maxMileage = carSearchDto.getMaxMileage();
        Integer minManufacturedYear = carSearchDto.getMinManufacturedYear();
        Integer maxManufacturedYear = carSearchDto.getMaxManufacturedYear();
        List<String> gearbox = carSearchDto.getGearbox();
        List<String> bodyType = carSearchDto.getBodyType();
        List<String> color = carSearchDto.getColor();
//        List<String> features = carSearchDto.getFeatures();
        List<String> fuelType = carSearchDto.getFuelType();
        if (makerId != null)
            searchCriteria.add(builder.equal(root.get("maker"), makerRepository.findById(makerId).get()));
        if (modelId != null)
            searchCriteria.add(builder.equal(root.get("model"), modelRepository.findById(modelId).get()));
        if (minPrice != null)
            searchCriteria
                    .add(builder.between(root.get("price"), minPrice, maxPrice == null ? Long.MAX_VALUE : maxPrice));
        if (minPrice == null && maxPrice != null)
            searchCriteria.add(builder.between(root.get("price"), 0L, maxPrice));
        if (minEngine != null)
            searchCriteria.add(
                    builder.between(root.get("engine"), minEngine, maxEngine == null ? Long.MAX_VALUE : maxEngine));
        if (minEngine == null && maxEngine != null)
            searchCriteria.add(builder.between(root.get("engine"), 0.0, maxEngine));
        if (minMileage != null)
            searchCriteria.add(builder.between(root.get("mileage"), minMileage,
                    maxMileage == null ? Integer.MAX_VALUE : maxMileage));
        if (minMileage == null && maxMileage != null)
            searchCriteria.add(builder.between(root.get("mileage"), 0, maxMileage));
        if (minManufacturedYear != null)
            searchCriteria.add(builder.between(root.get("manufacturedYear"), minManufacturedYear,
                    maxManufacturedYear == null ? Integer.MAX_VALUE : maxManufacturedYear));
        if (minManufacturedYear == null && maxManufacturedYear != null)
            searchCriteria.add(builder.between(root.get("manufacturedYear"), 0, maxManufacturedYear));
        if (gearbox != null && !gearbox.isEmpty())
            searchCriteria.add(root.get("gearbox").in(gearbox.stream().map(Gearbox::valueOf).toList()));
        if (bodyType != null && !bodyType.isEmpty())
            searchCriteria.add(root.get("bodyType").in(bodyType.stream().map(BodyType::valueOf).toList()));
        if (color != null && !color.isEmpty())
            searchCriteria.add(root.get("color").in(color.stream().map(Color::valueOf).toList()));

//        if (features != null && !features.isEmpty())
//            searchCriteria.add(root.get("features").in(features.stream().map(Feature::valueOf).toList()));

        if (fuelType != null && !fuelType.isEmpty())
            searchCriteria.add(root.get("fuelType").in(fuelType.stream().map(FuelType::valueOf).toList()));

        Predicate[] array = searchCriteria.toArray(new Predicate[searchCriteria.size()]);
        criteriaQuery.select(root).where(builder.and(array));
        List<Car> result = entityManager.createQuery(criteriaQuery).getResultList();

        return ResponseEntity.ok(count ? result.size() : result);
    }
}
