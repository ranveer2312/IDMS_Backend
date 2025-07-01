package com.example.storemanagementbackend.controller;
 
import com.example.storemanagementbackend.model.Leave;
import com.example.storemanagementbackend.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
import org.springframework.http.HttpStatus;
 
@RestController
@RequestMapping("/api/leaves")
@CrossOrigin(origins = "https://idmsproject.vercel.app") // Allow React frontend
public class LeaveController {
 
    @Autowired
    private LeaveService leaveService;
 
    @GetMapping
    public ResponseEntity<List<Leave>> getAllLeaves() {
        return ResponseEntity.ok(leaveService.getAllLeaves());
    }
 
    @PostMapping
    public ResponseEntity<Leave> createLeave(@RequestBody Leave leave) {
        Leave savedLeave = leaveService.createLeave(leave);
        return new ResponseEntity<>(savedLeave, HttpStatus.CREATED);
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
        return ResponseEntity.noContent().build();
    }
}
 
 