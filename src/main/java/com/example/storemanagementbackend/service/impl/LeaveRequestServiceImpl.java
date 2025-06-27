package com.example.storemanagementbackend.service.impl;
 
import com.example.storemanagementbackend.dto.LeaveRequestDTO;
import com.example.storemanagementbackend.model.LeaveRequest;
import com.example.storemanagementbackend.repository.LeaveRequestRepository;
import com.example.storemanagementbackend.service.LeaveRequestService;
import com.example.storemanagementbackend.model.Employee;
import com.example.storemanagementbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
 
@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
 
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;
 
    @Autowired
    private EmployeeRepository employeeRepository;
 
    @Override
    public LeaveRequestDTO submitLeaveRequest(LeaveRequestDTO dto) {
        LeaveRequest request = new LeaveRequest();
        request.setEmployeeId(dto.getEmployeeId());
        request.setEmployeeName(dto.getEmployeeName());
        request.setLeaveType(dto.getLeaveType());
        request.setStartDate(dto.getStartDate());
        request.setEndDate(dto.getEndDate());
        request.setReason(dto.getReason());
        request.setStatus(dto.getStatus());
        request.setNumberOfDays(calculateDays(dto.getStartDate(), dto.getEndDate()));
        request.setRequestDate(LocalDate.now());
        request.setHolidayName(dto.getHolidayName());
        request.setDay(dto.getDay());
        request.setType(dto.getType());
        request.setCoverage(dto.getCoverage());
        LeaveRequest saved = leaveRequestRepository.save(request);
        return mapToDTO(saved);
    }
 
    @Override
    public LeaveRequestDTO getLeaveRequestById(Long id) {
        Optional<LeaveRequest> optional = leaveRequestRepository.findById(id);
        return optional.map(this::mapToDTO).orElse(null);
    }
 
    @Override
    public int calculateDays(LocalDate startDate, LocalDate endDate) {
        return (int) (java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1);
    }
 
    @Override
    public void deleteLeaveRequest(Long id) {
        leaveRequestRepository.deleteById(id);
    }
 
    @Override
    public LeaveRequestDTO rejectLeaveRequest(Long id, String hrComments) {
        Optional<LeaveRequest> optional = leaveRequestRepository.findById(id);
        if (optional.isPresent()) {
            LeaveRequest request = optional.get();
            request.setStatus("REJECTED");
            request.setHrComments(hrComments);
            LeaveRequest saved = leaveRequestRepository.save(request);
            return mapToDTO(saved);
        }
        return null;
    }
 
    @Override
    public LeaveRequestDTO approveLeaveRequest(Long id, String hrComments) {
        Optional<LeaveRequest> optional = leaveRequestRepository.findById(id);
        if (optional.isPresent()) {
            LeaveRequest request = optional.get();
            request.setStatus("APPROVED");
            request.setHrComments(hrComments);
            LeaveRequest saved = leaveRequestRepository.save(request);
            return mapToDTO(saved);
        }
        return null;
    }
 
    @Override
    public List<LeaveRequestDTO> getAllLeaveRequests() {
        return leaveRequestRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }
 
    @Override
    public List<LeaveRequestDTO> getLeaveRequestsByEmployeeId(String employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
 
    @Override
    public List<LeaveRequestDTO> getLeaveRequestsByStatus(String status) {
        return leaveRequestRepository.findByStatus(status).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
 
    @Override
    public List<LeaveRequestDTO> getNonApprovedLeaveRequests() {
        return leaveRequestRepository.findByStatusNot("APPROVED").stream().map(this::mapToDTO).collect(Collectors.toList());
    }
 
    @Override
    public LeaveRequestDTO updateHoliday(Long id, LeaveRequestDTO dto) {
        Optional<LeaveRequest> optional = leaveRequestRepository.findById(id);
        if (optional.isPresent()) {
            LeaveRequest holiday = optional.get();
            holiday.setHolidayName(dto.getHolidayName());
            holiday.setStartDate(dto.getStartDate());
            holiday.setEndDate(dto.getEndDate());
            holiday.setDay(dto.getDay());
            holiday.setType(dto.getType());
            holiday.setStatus("HOLIDAY");
            holiday.setCoverage(dto.getCoverage());
            LeaveRequest saved = leaveRequestRepository.save(holiday);
            return mapToDTO(saved);
        }
        return null;
    }
 
    // Helper method to map LeaveRequest to LeaveRequestDTO
    private LeaveRequestDTO mapToDTO(LeaveRequest request) {
        LeaveRequestDTO dto = new LeaveRequestDTO();
        dto.setId(request.getId());
        dto.setEmployeeId(request.getEmployeeId());
        // Fetch and map employee details
        Employee emp = employeeRepository.findByEmployeeId(request.getEmployeeId()).orElse(null);
        if (emp != null) {
            dto.setEmployeeName(emp.getEmployeeName());
            dto.setDepartment(emp.getDepartment());
            dto.setEmail(emp.getEmail());
            dto.setPhoneNumber(emp.getPhoneNumber());
            dto.setBloodGroup(emp.getBloodGroup());
            dto.setProfilePhotoUrl(emp.getProfilePhotoUrl());
            dto.setCurrentAddress(emp.getCurrentAddress());
            dto.setPermanentAddress(emp.getPermanentAddress());
            dto.setPosition(emp.getPosition());
            dto.setJoiningDate(emp.getJoiningDate());
            dto.setRelievingDate(emp.getRelievingDate());
            dto.setStatus(emp.getStatus());
        } else {
            dto.setEmployeeName(request.getEmployeeName());
        }
        dto.setLeaveType(request.getLeaveType());
        dto.setStartDate(request.getStartDate());
        dto.setEndDate(request.getEndDate());
        dto.setReason(request.getReason());
        dto.setStatus(request.getStatus());
        dto.setNumberOfDays(request.getNumberOfDays());
        dto.setRequestDate(request.getRequestDate());
        dto.setHrComments(request.getHrComments());
        dto.setHolidayName(request.getHolidayName());
        dto.setDay(request.getDay());
        dto.setType(request.getType());
        dto.setCoverage(request.getCoverage());
        return dto;
    }
}
 
 