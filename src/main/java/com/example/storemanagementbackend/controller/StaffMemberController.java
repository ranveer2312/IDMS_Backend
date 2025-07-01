package com.example.storemanagementbackend.controller;
 
import com.example.storemanagementbackend.model.StaffMember;
import com.example.storemanagementbackend.service.StaffMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/staff")
@CrossOrigin(origins = "https://idmsproject.vercel.app")
public class StaffMemberController {
 
    @Autowired
    private StaffMemberService service;
 
    @GetMapping
    public List<StaffMember> getAllStaff() {
        return service.getAllStaff();
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<StaffMember> getStaffById(@PathVariable Long id) {
        StaffMember staffMember = service.getStaffById(id); // ✅ Fix here
        if (staffMember != null) {
            return new ResponseEntity<>(staffMember, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    @PostMapping
    public StaffMember createStaff(@RequestBody StaffMember staffMember) {
        return service.createStaff(staffMember);
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<StaffMember> updateStaff(@PathVariable Long id, @RequestBody StaffMember updatedStaff) {
        StaffMember staffMember = service.updateStaff(id, updatedStaff);
        if (staffMember != null) {
            return new ResponseEntity<>(staffMember, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    @DeleteMapping("/{id}")
    public void deleteStaff(@PathVariable Long id) {
        service.deleteStaff(id);
    }
}
 
 