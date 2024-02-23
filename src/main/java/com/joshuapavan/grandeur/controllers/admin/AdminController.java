package com.joshuapavan.grandeur.controllers.admin;

import com.joshuapavan.grandeur.models.Car;
import com.joshuapavan.grandeur.models.User;
import com.joshuapavan.grandeur.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/cars")
    public ResponseEntity<Page<Car>> getAllCars
            (@RequestParam(defaultValue = "1") int pageNo,
                @RequestParam(defaultValue = "10") int pageSize){
        return new ResponseEntity<>(adminService.getAllCars(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<Page<User>> getAllUsers
            ( @RequestParam(defaultValue = "1") int pageNo,
                @RequestParam(defaultValue = "10") int pageSize){
        return new ResponseEntity<>(adminService.getAllUsers(pageNo,pageSize), HttpStatus.OK);
    }

//    @PutMapping("/car/{id}")
//    public ResponseEntity<Car> updateCar(@RequestBody Ca)


}
