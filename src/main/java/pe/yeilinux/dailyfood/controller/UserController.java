package pe.yeilinux.dailyfood.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.yeilinux.dailyfood.domain.request.DaySchemaRequest;
import pe.yeilinux.dailyfood.domain.request.UserRequest;
import pe.yeilinux.dailyfood.domain.response.GeneralResponse;
import pe.yeilinux.dailyfood.entity.DayEntity;
import pe.yeilinux.dailyfood.entity.UserEntity;
import pe.yeilinux.dailyfood.service.DayService;
import pe.yeilinux.dailyfood.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DayService dayService;

    @PostMapping
    @ApiOperation(value = "Insert User and insert initial daySchema")
    public ResponseEntity<?> insertUser(@RequestBody UserRequest userRequest){
        try{
            UserEntity userEntity = this.userService.insertUser(userRequest);
            DaySchemaRequest daySchemaRequest = new DaySchemaRequest();
            daySchemaRequest.setUserId(userEntity.getUserId());
            DayEntity dayEntity = this.dayService.insertDaySchema(daySchemaRequest);
            return new ResponseEntity<>(new GeneralResponse(userEntity,"User inserted correctly"), HttpStatus.CREATED);
        }catch (Exception e){
            throw new Error(e.getMessage());
        }
    }

    @PutMapping("{userId}")
    @ApiOperation(value = "Update DaySchema")
    public ResponseEntity<?> updateUser(@RequestBody UserRequest userRequest,@PathVariable Long userId){
        try{
            this.userService.updateUser(userRequest,userId);
            return new ResponseEntity<>(new GeneralResponse(userId,"User updated correctly"), HttpStatus.OK);
        }catch (Exception e){
            throw new Error(e.getMessage());
        }
    }

    @GetMapping
    @ApiOperation(value = "Get DaySchema by userId")
    public ResponseEntity<?> getUserInformation(@RequestParam String email){
        try{
            return new ResponseEntity<>(new GeneralResponse(this.userService.getUserInformation(email),"User"), HttpStatus.OK);
        }catch (Exception e){
            throw new Error(e.getMessage());
        }
    }
}
