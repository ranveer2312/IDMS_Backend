package com.example.storemanagementbackend.repository;
 
import com.example.storemanagementbackend.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
 
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByEmployeeIdAndDate(String employeeId, LocalDate date);
 
    List<Attendance> findByEmployeeIdOrderByDateDesc(String employeeId); // Add this for history fetch
}
 