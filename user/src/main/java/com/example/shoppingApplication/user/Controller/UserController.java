package com.example.shoppingApplication.user.Controller;

import com.example.shoppingApplication.user.Model.User;
import com.example.shoppingApplication.user.Response.OrderResponse;
import com.example.shoppingApplication.user.Service.UserService;
import org.hibernate.query.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequestMapping("/api/user")
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId){
        Optional<User> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody String userDetails){
        System.out.println(userDetails);
        HttpStatus status =  userService.addUser(userDetails) ? HttpStatus.OK : HttpStatus.FORBIDDEN;
        return new ResponseEntity<>(status);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody String userDetails){
        HttpStatus status =  userService.updateUser(userDetails) ? HttpStatus.OK : HttpStatus.FORBIDDEN;
        return new ResponseEntity<>(status);
    }

    @GetMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
        HttpStatus status =  userService.deleteUser(userId) ? HttpStatus.OK : HttpStatus.FORBIDDEN;
        return new ResponseEntity<>(status);
    }

    @GetMapping("/checkUserExists/{userId}")
    public ResponseEntity<Boolean> checkUserExists(@PathVariable Integer userId){
        boolean user = userService.checkUserExists(userId);
        HttpStatus status =  user? HttpStatus.OK : HttpStatus.FORBIDDEN;
        return new ResponseEntity<>(user, status);
    }

    @GetMapping("/getOrders/{customerId}")
    public ResponseEntity<List<OrderResponse>> getOrders(@PathVariable Integer customerId){
        List<OrderResponse> orderResponse = userService.getOrders(customerId);
        HttpStatus status = orderResponse == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(orderResponse, status);
    }

}
