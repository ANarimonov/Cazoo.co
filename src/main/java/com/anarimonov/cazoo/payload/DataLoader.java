package com.anarimonov.cazoo.payload;

import com.anarimonov.cazoo.entity.Car;
import com.anarimonov.cazoo.entity.Maker;
import com.anarimonov.cazoo.entity.Model;
import com.anarimonov.cazoo.entity.enums.*;
import com.anarimonov.cazoo.repository.CarRepository;
import com.anarimonov.cazoo.repository.MakerRepository;
import com.anarimonov.cazoo.repository.ModelRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final MakerRepository makerRepository;
    private final ModelRepository modelRepository;
    private final CarRepository carRepository;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    String ddlAuto;

    @Override
    public void run(String... args) {
        if (ddlAuto.equalsIgnoreCase("create")) {
            Maker maker1 = makerRepository.save(new Maker("Kia"));
            Maker maker2 = makerRepository.save(new Maker("BYD"));

            Model cerato = modelRepository.save(new Model("Cerato", maker1));
            Model k5 = modelRepository.save(new Model("K5", maker1));
            Model bongo = modelRepository.save(new Model("Bongo", maker1));
            Model chazor = modelRepository.save(new Model("Chazor", maker2));
            Model song = modelRepository.save(new Model("Song Plus", maker2));
            Model tang = modelRepository.save(new Model("Tang", maker2));
            carRepository.save(new Car(maker1, cerato, 290_900_000L, 2014, 1.6, FuelType.PETROL, 90000, Gearbox.MANUAL, Color.RED, BodyType.COUPE, List.of(Feature.ALLOYS, Feature.BLUETOOTH, Feature.AIR_CON), null));
            carRepository.save(new Car(maker1, cerato, 330_900_000L, 2018, 1.6, FuelType.PETROL, 50000, Gearbox.MANUAL, Color.BLACK, BodyType.COUPE, List.of(Feature.PARKING_CAMERA, Feature.PRIVACY_GLASS, Feature.CD_PLAYER), null));
            carRepository.save(new Car(maker1, cerato, 375_900_000L, 2016, 2.0, FuelType.PETROL, 10000, Gearbox.AUTOMATIC, Color.WHITE, BodyType.COUPE, List.of(Feature.PARKING_SENSORS, Feature.PREMIUM_SOUND_SYSTEM, Feature.HEAD_UP_DISPLAY), null));
            carRepository.save(new Car(maker1, k5, 321_000_000L, 2014, 2.0, FuelType.PETROL, 90000, Gearbox.MANUAL, Color.RED, BodyType.COUPE, List.of(Feature.HEATED_SEATS, Feature.HEATED_WINDSCREEN, Feature.AIR_CON), null));
            carRepository.save(new Car(maker1, k5, 370_000_000L, 2018, 2.0, FuelType.PETROL, 50000, Gearbox.MANUAL, Color.BLACK, BodyType.COUPE, List.of(Feature.PARKING_CAMERA, Feature.PRIVACY_GLASS, Feature.CD_PLAYER), null));
            carRepository.save(new Car(maker1, k5, 400_000_000L, 2016, 2.5, FuelType.PETROL, 10000, Gearbox.AUTOMATIC, Color.WHITE, BodyType.COUPE, List.of(Feature.PARKING_SENSORS, Feature.PREMIUM_SOUND_SYSTEM, Feature.HEAD_UP_DISPLAY), null));
            carRepository.save(new Car(maker1, bongo, 264_900_000L, 2016, 1.0, FuelType.DIESEL, 90000, Gearbox.MANUAL, Color.SILVER, BodyType.OTHER, List.of(Feature.PARKING_CAMERA, Feature.ACTIVE_CRUISE_CONTROL), null));
            carRepository.save(new Car(maker1, bongo, 269_900_000L, 2018, 1.0, FuelType.DIESEL, 50000, Gearbox.AUTOMATIC, Color.WHITE, BodyType.OTHER, List.of(Feature.PARKING_CAMERA, Feature.CD_PLAYER), null));
            carRepository.save(new Car(maker2, chazor, 285_736_000L, 2020, 1.5, FuelType.HYBRID, 55000, Gearbox.AUTOMATIC, Color.RED, BodyType.COUPE, List.of(Feature.HEATED_SEATS, Feature.HEATED_WINDSCREEN, Feature.AIR_CON), null));
            carRepository.save(new Car(maker2, chazor, 323_039_000L, 2018, 1.5, FuelType.HYBRID, 90000, Gearbox.AUTOMATIC, Color.BLACK, BodyType.COUPE, List.of(Feature.PARKING_CAMERA, Feature.PRIVACY_GLASS, Feature.CD_PLAYER), null));
            carRepository.save(new Car(maker2, chazor, 341_660_000L, 2022, 1.5, FuelType.HYBRID, 10000, Gearbox.AUTOMATIC, Color.WHITE, BodyType.COUPE, List.of(Feature.PARKING_SENSORS, Feature.PREMIUM_SOUND_SYSTEM, Feature.HEAD_UP_DISPLAY), null));
        }
    }
}
