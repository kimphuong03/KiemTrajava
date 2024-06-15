package KieuThiKimPhuong.controller;

import KieuThiKimPhuong.model.Department;
import KieuThiKimPhuong.model.Employee;
import KieuThiKimPhuong.service.DepartmentService;
import KieuThiKimPhuong.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    // Số bản ghi trên mỗi trang
    private static final int PAGE_SIZE = 5;

    @GetMapping
    public String getAllEmployees(@RequestParam(defaultValue = "0") int page, Model model) {
        // Tạo một PageRequest để lấy trang dữ liệu
        PageRequest pageable = PageRequest.of(page, PAGE_SIZE);
        Page<Employee> employeesPage = employeeService.getAllEmployees(pageable);

        // Tổng số trang
        int totalPages = employeesPage.getTotalPages();

        model.addAttribute("employees", employeesPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "employees/employees";
    }

    @GetMapping("/add")
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employees/add-employee";
    }

    @PostMapping
    public String saveEmployee(@ModelAttribute Employee employee, @RequestParam("department.id") Long departmentId) {
        Department department = departmentService.getDepartmentById(departmentId);
        employee.setDepartment(department);
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable String id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            // Handle case where employee is not found by id, e.g., throw an exception
            // Example: throw new EntityNotFoundException("Employee not found with id: " + id);
        }
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employees/edit-employee";
    }


    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable String id, @ModelAttribute("employee") Employee employee, @RequestParam("department.id") Long departmentId) {
        Department department = departmentService.getDepartmentById(departmentId);
        employee.setDepartment(department);
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}
