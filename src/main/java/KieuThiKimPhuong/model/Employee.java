package KieuThiKimPhuong.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "maNv")
    private String maNv;

    @Column(name = "tenNv")
    private String tenNv;

    @Column(name = "phai")
    private String phai;

    @Column(name = "noiSinh")
    private String noiSinh;

    @Column(name = "luong")
    private int luong;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;

    // Getters và Setters sẽ được tạo bởi Lombok @Data
}
