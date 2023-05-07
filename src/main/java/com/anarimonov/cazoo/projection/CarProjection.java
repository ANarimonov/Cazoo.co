package com.anarimonov.cazoo.projection;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.anarimonov.cazoo.entity.Car;
import com.anarimonov.cazoo.entity.Maker;
import com.anarimonov.cazoo.entity.Model;
import com.anarimonov.cazoo.entity.enums.BodyType;
import com.anarimonov.cazoo.entity.enums.Color;
import com.anarimonov.cazoo.entity.enums.FuelType;
import com.anarimonov.cazoo.entity.enums.Gearbox;

@Projection(types = Car.class)
public interface CarProjection {
    Long getId();
    Maker getMaker();
    Model getModel();
    Long getPrice();
    Integer getManufacturedYear();
    Double getEngine();
    FuelType getFuelType();
    Integer getMileage();
    Gearbox getGearbox();
    Color getColor();
    BodyType getBodyType();
    Timestamp getRegistrationDate();
    String getFeatures();
    @Value("#{@attachmentRepository.getByCarId(target.id)}")
    List<Long> getAttachmentsIds();
}
