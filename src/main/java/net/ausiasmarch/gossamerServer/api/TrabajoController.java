/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.gossamerServer.api;

import java.util.List;
import javax.servlet.http.HttpSession;
import net.ausiasmarch.gossamerServer.entity.ClienteEntity;
import net.ausiasmarch.gossamerServer.entity.EmpleadoEntity;
import net.ausiasmarch.gossamerServer.entity.TrabajoEntity;
import net.ausiasmarch.gossamerServer.repository.TrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mauricio
 */
@RestController
@RequestMapping("/trabajo")
public class TrabajoController {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    TrabajoRepository oTrabajoRepository;

    @GetMapping("/all")
    public ResponseEntity<?> all() {

        if (false) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<List<TrabajoEntity>>(oTrabajoRepository.findAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        if (oTrabajoRepository.existsById(id)) {
            return new ResponseEntity<TrabajoEntity>(oTrabajoRepository.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<TrabajoEntity>(oTrabajoRepository.getOne(id), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {

        return new ResponseEntity<Long>(oTrabajoRepository.count(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody TrabajoEntity oTrabajoEntity) {
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        if (oSessionEmpleadoEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oTrabajoEntity.getId() == null) {
                return new ResponseEntity<TrabajoEntity>(oTrabajoRepository.save(oTrabajoEntity), HttpStatus.OK);
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
            }
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TrabajoEntity oTrabajoEntity) {

        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        if (oSessionEmpleadoEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oTrabajoRepository.existsById(id)) {
                return new ResponseEntity<TrabajoEntity>(oTrabajoRepository.save(oTrabajoEntity), HttpStatus.OK);
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
            }
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        if (oSessionEmpleadoEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            oTrabajoRepository.deleteById(id);
            if (oTrabajoRepository.existsById(id)) {
                return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.OK);
            }
        }

    }
}
