package com.ip737.transportcompany.transportcompany.web.controllers;

import com.ip737.transportcompany.transportcompany.request.InsertOrderRequest;
import com.ip737.transportcompany.transportcompany.services.OrderService;
import com.ip737.transportcompany.transportcompany.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/profile")
public class OrderController {

    final private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/insert-vehicle")
    public ResponseEntity<?> addVehicle(@RequestBody InsertOrderRequest order) {
        orderService.insertOrder(order.toOrder());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> profile() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
