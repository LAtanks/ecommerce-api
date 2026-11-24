package me.latanks.ecommerce_api.controllers;


import me.latanks.ecommerce_api.models.dto.request.CreateUserDTO;
import me.latanks.ecommerce_api.models.dto.request.UpdateUserDTO;
import me.latanks.ecommerce_api.models.dto.response.CreatedUserDTO;
import me.latanks.ecommerce_api.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<CreatedUserDTO> create(@RequestBody CreateUserDTO obj){
        return ResponseEntity.ok(this.userService.create(obj));
    }

    @PutMapping
    public ResponseEntity<CreatedUserDTO> update(@RequestBody UpdateUserDTO obj){
        return ResponseEntity.ok(this.userService.update(obj));
    }

    @GetMapping("/my")
    public ResponseEntity<CreatedUserDTO> getMyProfile(){
        return ResponseEntity.ok(this.userService.getMyProfile());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreatedUserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(this.userService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        this.userService.delete(id);

        return ResponseEntity.ok().build();
    }
}
