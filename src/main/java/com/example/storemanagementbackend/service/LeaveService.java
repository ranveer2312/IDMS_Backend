package com.example.storemanagementbackend.service;
 
import com.example.storemanagementbackend.model.Leave;
import com.example.storemanagementbackend.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
 
@Service
public class LeaveService {
 
    @Autowired
    private LeaveRepository leaveRepository;
 
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }
 
    public Leave createLeave(Leave leave) {
        return leaveRepository.save(leave);
    }
 
    public void deleteLeave(Long id) {
        leaveRepository.deleteById(id);
    }
}
 
 