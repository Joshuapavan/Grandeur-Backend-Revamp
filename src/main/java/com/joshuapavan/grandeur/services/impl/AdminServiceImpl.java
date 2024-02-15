package com.joshuapavan.grandeur.services.impl;

import com.joshuapavan.grandeur.models.Car;
import com.joshuapavan.grandeur.models.User;
import com.joshuapavan.grandeur.repositories.CarRepo;
import com.joshuapavan.grandeur.repositories.UserRepo;
import com.joshuapavan.grandeur.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepo userRepo;
    private final CarRepo carRepo;

    @Override
    public Page<User> getAllUsers(int pageNo, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        return userRepo.findAll(pageRequest);
    }
    @Override
    public Page<Car> getAllCars(int pageNo, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        return carRepo.findAll(pageRequest);
    }
}
