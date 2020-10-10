package pe.yeilinux.dailyfood.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.yeilinux.dailyfood.domain.DaySchema;
import pe.yeilinux.dailyfood.domain.request.DaySchemaRequest;
import pe.yeilinux.dailyfood.domain.response.GeneralResponse;
import pe.yeilinux.dailyfood.service.DayService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("daysSchema")
public class DayController {
    @Autowired
    private DayService dayService;

    @GetMapping("user/{userId}")
    @ApiOperation(value = "Get DaySchema by userId")
    public ResponseEntity<?> getDaySchemaByUserId(@PathVariable Long userId) {
        try{
            return new ResponseEntity<>( new GeneralResponse(this.dayService.getDaySchemaByUserId(userId),"User daySchema information"),HttpStatus.OK);
        }catch(Exception e) {
            throw new Error(e.getMessage());
        }
    }
    @PostMapping
    @ApiOperation(value = "Insert DaySchema")
    public ResponseEntity<?> insertDaySchema(@RequestBody DaySchemaRequest daySchemaRequest) {
        try{
            return new ResponseEntity<>(new GeneralResponse(this.dayService.insertDaySchema(daySchemaRequest),"DaySchema inserted successful"),HttpStatus.OK);
        }catch(Exception e) {
            throw new Error(e.getMessage());
        }
    }
    @PutMapping("{daySchemaId}")
    @ApiOperation(value = "Update DaySchema")
    public ResponseEntity<?> updateDaySchema(@PathVariable Long daySchemaId,@RequestBody DaySchemaRequest daySchemaRequest) {
        try{
            this.dayService.updateDaySchema(daySchemaId,daySchemaRequest);
            return new ResponseEntity<>(new GeneralResponse(daySchemaId,"DaySchema Updated Successful"),HttpStatus.OK);
        }catch(Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @PutMapping("user/{userId}/week")
    @ApiOperation(value = "Update WeekSchema")
    public ResponseEntity<?> updateWeekSchema(@RequestBody List<DaySchema> weekSchema,@PathVariable Long userId) {
        try{
            Map<String,Long> weekSchemaMap = this.dayService.updateWeekSchema(weekSchema);
            DaySchemaRequest daySchemaRequest = new DaySchemaRequest();
            daySchemaRequest.setWeekSchema(weekSchemaMap);

            this.dayService.updateDaySchemaByUserId(userId,daySchemaRequest);
            return new ResponseEntity<>(new GeneralResponse(weekSchemaMap,"WeekSchema Updated Successful"),HttpStatus.OK);
        }catch(Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @DeleteMapping("{userId}/{day}")
    @ApiOperation(value = "Delete FoodId of WeekSchema by userId")
    public ResponseEntity<?> deleteFoodIdOfWeekSchemaByUserId(@PathVariable Long userId,@PathVariable String day) {
        try{
            this.dayService.removeFoodDetailOfWeekSchema(userId,day);
            return new ResponseEntity<>(new GeneralResponse(day,"WeekSchema Updated Successful"),HttpStatus.OK);
        }catch(Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
