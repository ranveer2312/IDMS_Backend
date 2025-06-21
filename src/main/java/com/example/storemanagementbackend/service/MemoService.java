package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.Memo;
import com.example.storemanagementbackend.model.Employee;
import com.example.storemanagementbackend.repository.MemoRepository;
import com.example.storemanagementbackend.repository.EmployeeRepository;
import com.example.storemanagementbackend.dto.MemoRequestDTO;
import com.example.storemanagementbackend.dto.MemoResponseDTO;
import com.example.storemanagementbackend.dto.EmployeeInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
public class MemoService {

    @Autowired
    private MemoRepository memoRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public MemoResponseDTO createMemo(MemoRequestDTO memoRequestDTO) {
        Memo memo = new Memo();
        memo.setTitle(memoRequestDTO.getTitle());
        memo.setMeetingType(memoRequestDTO.getMeetingType());
        memo.setMeetingDate(memoRequestDTO.getMeetingDate());
        memo.setPriority(memoRequestDTO.getPriority());
        memo.setContent(memoRequestDTO.getContent());
        memo.setSentBy(memoRequestDTO.getSentBy());
        memo.setSentByName(memoRequestDTO.getSentByName());
        memo.setRecipientEmployeeIds(memoRequestDTO.getRecipientEmployeeIds());
        memo.setRecipientDepartments(memoRequestDTO.getRecipientDepartments());
        memo.setSentToAll(memoRequestDTO.isSentToAll());

        Memo savedMemo = memoRepository.save(memo);
        return convertToResponseDTO(savedMemo);
    }

    public List<MemoResponseDTO> getAllMemos() {
        List<Memo> memos = memoRepository.findAllByOrderBySentAtDesc();
        return memos.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<MemoResponseDTO> getMemosByEmployee(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        String department = employee.getDepartment();

        List<Memo> memos = memoRepository.findMemosForEmployee(employeeId, department);
        return memos.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<MemoResponseDTO> getMemosByDepartment(String department) {
        List<Memo> memos = memoRepository.findMemosForDepartment(department);
        return memos.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<MemoResponseDTO> getMemosByAdmin(String adminId) {
        List<Memo> memos = memoRepository.findBySentByOrderBySentAtDesc(adminId);
        return memos.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public MemoResponseDTO getMemoById(Long id) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Memo not found with id: " + id));
        return convertToResponseDTO(memo);
    }

    public MemoResponseDTO updateMemo(Long id, MemoRequestDTO memoRequestDTO) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Memo not found with id: " + id));

        memo.setTitle(memoRequestDTO.getTitle());
        memo.setMeetingType(memoRequestDTO.getMeetingType());
        memo.setMeetingDate(memoRequestDTO.getMeetingDate());
        memo.setPriority(memoRequestDTO.getPriority());
        memo.setContent(memoRequestDTO.getContent());
        memo.setRecipientEmployeeIds(memoRequestDTO.getRecipientEmployeeIds());
        memo.setRecipientDepartments(memoRequestDTO.getRecipientDepartments());
        memo.setSentToAll(memoRequestDTO.isSentToAll());
        // sentBy and sentByName are not updated as they are historical data

        Memo updatedMemo = memoRepository.save(memo);
        return convertToResponseDTO(updatedMemo);
    }

    public void deleteMemo(Long id) {
        if (!memoRepository.existsById(id)) {
            throw new RuntimeException("Memo not found with id: " + id);
        }
        memoRepository.deleteById(id);
    }

    private MemoResponseDTO convertToResponseDTO(Memo memo) {
        MemoResponseDTO responseDTO = new MemoResponseDTO();
        responseDTO.setId(memo.getId());
        responseDTO.setTitle(memo.getTitle());
        responseDTO.setMeetingType(memo.getMeetingType());
        responseDTO.setMeetingDate(memo.getMeetingDate());
        responseDTO.setPriority(memo.getPriority());
        responseDTO.setContent(memo.getContent());
        responseDTO.setSentBy(memo.getSentBy());
        responseDTO.setSentByName(memo.getSentByName());
        responseDTO.setRecipientEmployeeIds(memo.getRecipientEmployeeIds());
        responseDTO.setRecipientDepartments(memo.getRecipientDepartments());
        responseDTO.setSentAt(memo.getSentAt());
        responseDTO.setSentToAll(memo.isSentToAll());

        // Get detailed recipient information
        List<Employee> recipients = getRecipients(memo);
        List<EmployeeInfoDTO> recipientDTOs = recipients.stream()
                .map(this::convertToEmployeeInfoDTO)
                .collect(Collectors.toList());
        
        responseDTO.setRecipients(recipientDTOs);
        responseDTO.setTotalRecipients(recipients.size());

        return responseDTO;
    }

    private List<Employee> getRecipients(Memo memo) {
        List<Employee> recipients = new ArrayList<>();

        if (memo.isSentToAll()) {
            // If sent to all, get all employees
            recipients = employeeRepository.findAll();
        } else {
            // Get specific employees
            if (memo.getRecipientEmployeeIds() != null && !memo.getRecipientEmployeeIds().isEmpty()) {
                for (String employeeId : memo.getRecipientEmployeeIds()) {
                    employeeRepository.findByEmployeeId(employeeId).ifPresent(recipients::add);
                }
            }

            // Get employees by department
            if (memo.getRecipientDepartments() != null && !memo.getRecipientDepartments().isEmpty()) {
                for (String department : memo.getRecipientDepartments()) {
                    List<Employee> deptEmployees = employeeRepository.findByDepartment(department);
                    recipients.addAll(deptEmployees);
                }
            }
        }

        // Remove duplicates based on employee ID
        return recipients.stream()
                .collect(Collectors.toMap(Employee::getEmployeeId, emp -> emp, (existing, replacement) -> existing))
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    private EmployeeInfoDTO convertToEmployeeInfoDTO(Employee employee) {
        EmployeeInfoDTO dto = new EmployeeInfoDTO();
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setEmployeeName(employee.getEmployeeName());
        dto.setDepartment(employee.getDepartment());
        dto.setEmail(employee.getEmail());
        return dto;
    }
} 