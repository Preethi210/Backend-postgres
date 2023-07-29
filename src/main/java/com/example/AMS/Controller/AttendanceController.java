package com.example.AMS.Controller;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.AMS.Entities.AttendanceEntity;
import com.example.AMS.dto.request.AttendanceRequest;
import com.example.AMS.dto.response.AttendanceResponse;
import com.example.AMS.service.AttendanceService;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController  implements AttendanceService{
    private final AttendanceService attendanceService;


    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }



    @GetMapping("/")
    public List<AttendanceResponse> findAllAttendance()
    {
        return attendanceService.findAllAttendance();
    }

    @GetMapping("/{id}")
    public Optional<AttendanceEntity> findById(@PathVariable("id") Long id) {
        return attendanceService.findById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable("id") Long id) {
        attendanceService.deleteAttendance(id);
    }

    @PostMapping()
    public AttendanceResponse saveAttendance(@RequestBody AttendanceRequest attendanceRequest) {
        return attendanceService.saveAttendance(attendanceRequest);

    }
    @PutMapping("/{id}")
    public AttendanceResponse updateAttendance(@RequestBody AttendanceRequest attendanceRequest, @PathVariable("id") Long id) {
        return attendanceService.updateAttendance(attendanceRequest, id);
    }
    @PostMapping("/")
    @Override
    public List<AttendanceResponse> saveAllAttendance(@RequestBody List<AttendanceRequest> attendanceRequestList) {
        return attendanceService.saveAllAttendance(attendanceRequestList);
    }

    /*@Override
    public List<AttendanceEntity> getBystudentidindaterangeusingJpql(int studentId, LocalDate startdate, LocalDate enddate) {
        return null;
    }

    @Override
    public List<AttendanceEntity> getByclassIDindaterangeusingJpql(String classId, LocalDate startdate, LocalDate enddate) {
        return null;
    }*/
    //-----------------

    @GetMapping("/students/by-id-DateRange/{studentId}/{startdate}/{enddate}")
    public List<AttendanceResponse> getBystudentidindaterange(@PathVariable int studentId, @PathVariable LocalDate startdate, @PathVariable LocalDate enddate){
        return attendanceService.getBystudentidindaterange(studentId, startdate, enddate);
    }

    @GetMapping("/class/by-id-DateRange/{classId}/{startdate}/{enddate}")
    public List<AttendanceResponse> getByclassIDindaterange(@PathVariable String classId, @PathVariable LocalDate startdate, @PathVariable LocalDate enddate) {
        return attendanceService.getByclassIDindaterange(classId, startdate, enddate);
    }

    @GetMapping("/presentcount")
    //total no of days presented
    public long getpresentcount(@RequestParam int studentId, @RequestParam LocalDate startdate, @RequestParam LocalDate enddate){
        return attendanceService.getpresentcount(studentId,startdate,enddate);
    }
    @GetMapping("/absentcount")
    //total no of days presented
    public int gettotalabcent (@RequestParam int studentId, @RequestParam LocalDate startdate, @RequestParam LocalDate enddate){
        return attendanceService.gettotalabcent(studentId,startdate,enddate);
    }

    @GetMapping("/totalcount")
    //total no of days presented
    public int gettotalcount (@RequestParam int studentId, @RequestParam LocalDate startdate, @RequestParam LocalDate enddate){
        return attendanceService.gettotalcount(studentId,startdate,enddate);
    }

    @GetMapping("/percentage")
    //total no of days presented
    public int getpercentage (@RequestParam int studentId, @RequestParam LocalDate startdate, @RequestParam LocalDate enddate){
        return (int) attendanceService.getpercentage(studentId,startdate,enddate);
    }

}