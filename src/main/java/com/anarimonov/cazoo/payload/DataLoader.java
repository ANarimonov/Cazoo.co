package com.anarimonov.cazoo.payload;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
//    private final CarRepository carRepository;
    @Override
    public void run(String... args) {
//        carRepository.save(new Car(
//                null,
//                null,
//                5000000L,
//                2014,
//                1.5,
//                FuelType.PETROL,
//                100000,
//                Gearbox.MANUAL,
//                Color.WHITE,
//                BodyType.COUPE,
//                null,
//                null
//        ));
    }
}
