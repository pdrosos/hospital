package com.example.hospital.department.web;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.hospital.common.error.EntityNotFoundException;
import com.example.hospital.common.web.Response;
import com.example.hospital.department.model.DepartmentCreateDto;
import com.example.hospital.department.model.DepartmentDto;
import com.example.hospital.department.model.DepartmentUpdateDto;
import com.example.hospital.department.service.DepartmentService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {

    private final DepartmentService departmentService;

    public DepartmentsController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<DepartmentDto> getDepartments() {
        return departmentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends Object> getDepartment(@PathVariable Long id) {
        Optional<DepartmentDto> departmentDto = departmentService.findById(id);

        return Response.of(departmentDto, id);
    }

    @PostMapping
    public ResponseEntity<? extends Object> createDepartment(
            @Valid @RequestBody DepartmentCreateDto departmentCreateDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return Response.unprocessableEntity(bindingResult.getFieldErrors());
        }

        DepartmentDto departmentDto = departmentService.create(departmentCreateDto);

        return Response.created(departmentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<? extends Object> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentUpdateDto departmentUpdateDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return Response.unprocessableEntity(bindingResult.getFieldErrors());
        }

        try {
            DepartmentDto departmentDto = departmentService.update(id, departmentUpdateDto);

            return ResponseEntity.ok(departmentDto);
        } catch (EntityNotFoundException ex) {
            return Response.notFound(id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? extends Object> deleteDepartment(@PathVariable Long id) {
        try {
            DepartmentDto departmentDto = departmentService.delete(id);

            return ResponseEntity.ok(departmentDto);
        } catch (EntityNotFoundException ex) {
            return Response.notFound(id);
        }
    }

}
