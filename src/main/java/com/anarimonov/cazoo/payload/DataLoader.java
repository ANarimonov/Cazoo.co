package com.anarimonov.cazoo.payload;

import com.anarimonov.cazoo.entity.Car;
import com.anarimonov.cazoo.entity.enums.BodyType;
import com.anarimonov.cazoo.entity.enums.Color;
import com.anarimonov.cazoo.entity.enums.FuelType;
import com.anarimonov.cazoo.entity.enums.Gearbox;
import com.anarimonov.cazoo.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final CarRepository carRepository;
    @Override
    public void run(String... args) {
        carRepository.save(new Car(
                null,
                null,
                5000000L,
                2014,
                1.5,
                FuelType.PETROL,
                100000,
                Gearbox.MANUAL,
                Color.WHITE,
                BodyType.COUPE,
                null,
                null
        ));
    }
}
