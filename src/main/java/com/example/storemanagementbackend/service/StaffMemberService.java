package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.StaffMember;
import com.example.storemanagementbackend.repository.StaffMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffMemberService {

    @Autowired
    private StaffMemberRepository staffMemberRepository;

    public List<StaffMember> getAllStaff() {
        return staffMemberRepository.findAll();
    }

    public StaffMember createStaff(StaffMember staffMember) {
        return staffMemberRepository.save(staffMember);
    }

    public StaffMember getStaffById(Long id) {
        return staffMemberRepository.findById(id)
                .orElse(null);
    }

    public StaffMember updateStaff(Long id, StaffMember staffMemberDetails) {
        StaffMember staffMember = staffMemberRepository.findById(id)
                .orElse(null);
        
        if (staffMember != null) {
            staffMember.setName(staffMemberDetails.getName());
            staffMember.setEmail(staffMemberDetails.getEmail());
            staffMember.setPhone(staffMemberDetails.getPhone());
            staffMember.setPosition(staffMemberDetails.getPosition());
            staffMember.setDepartment(staffMemberDetails.getDepartment());
            
            return staffMemberRepository.save(staffMember);
        }
        return null;
    }

    public void deleteStaff(Long id) {
        staffMemberRepository.deleteById(id);
    }
} 