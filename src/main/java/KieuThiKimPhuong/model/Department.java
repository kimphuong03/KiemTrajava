package KieuThiKimPhuong.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departmentId")
    private Long departmentId;

    @Column(name = "departmentName")
    private String departmentName;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    // Getters và Setters sẽ được tạo bởi Lombok @Data
}
