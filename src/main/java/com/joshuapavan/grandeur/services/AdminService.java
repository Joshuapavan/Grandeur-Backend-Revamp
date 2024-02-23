package com.joshuapavan.grandeur.services;

import com.joshuapavan.grandeur.models.Car;
import com.joshuapavan.grandeur.models.User;
import org.springframework.data.domain.Page;

public interface AdminService {
    Page<User> getAllUsers(int pageNo, int pageSize);
    Page<Car> getAllCars(int pageNo, int pageSize);
}
