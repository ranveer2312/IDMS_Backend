package com.example.storemanagementbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "memos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "meeting_type", nullable = false)
    private String meetingType; // e.g., "Team Meeting", "Department Meeting", "All Hands", "Emergency"

    @Column(name = "meeting_date")
    private java.time.LocalDate meetingDate; // The date of the scheduled meeting

    @Column(nullable = false)
    private String priority; // e.g., "Low", "Medium", "High", "Urgent"

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "sent_by", nullable = false)
    private String sentBy; // Admin's employee ID

    @Column(name = "sent_by_name")
    private String sentByName; // Admin's name

    @ElementCollection
    @CollectionTable(name = "memo_recipients", joinColumns = @JoinColumn(name = "memo_id"))
    @Column(name = "employee_id")
    private List<String> recipientEmployeeIds; // List of employee IDs who should receive the memo

    @ElementCollection
    @CollectionTable(name = "memo_departments", joinColumns = @JoinColumn(name = "memo_id"))
    @Column(name = "department")
    private List<String> recipientDepartments; // List of departments who should receive the memo

    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;

    @Column(name = "is_sent_to_all")
    private boolean sentToAll; // If true, memo is sent to all employees regardless of specific recipients

    @PrePersist
    protected void onCreate() {
        sentAt = LocalDateTime.now();
    }
} 