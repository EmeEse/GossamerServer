/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.gossamerServer.api;

import java.util.List;
import javax.servlet.http.HttpSession;
import net.ausiasmarch.gossamerServer.entity.CitaEntity;
import net.ausiasmarch.gossamerServer.entity.ClienteEntity;
import net.ausiasmarch.gossamerServer.entity.EmpleadoEntity;
import net.ausiasmarch.gossamerServer.entity.HacerEntity;
import net.ausiasmarch.gossamerServer.repository.CitaRepository;
import net.ausiasmarch.gossamerServer.repository.HacerRepository;
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
@RequestMapping("/hacer")
public class HacerController {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    HacerRepository oHacerRepository;

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        if (oSessionEmpleadoEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<List<HacerEntity>>(oHacerRepository.findAll(), HttpStatus.OK);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        if (oSessionEmpleadoEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oHacerRepository.existsById(id)) {
                return new ResponseEntity<HacerEntity>(oHacerRepository.findById(id).get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<HacerEntity>(oHacerRepository.getOne(id), HttpStatus.NOT_FOUND);
            }
        }

    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {

        return new ResponseEntity<Long>(oHacerRepository.count(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody HacerEntity oHacerEntity) {
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        if (oSessionEmpleadoEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oHacerEntity.getId() == null) {
                return new ResponseEntity<HacerEntity>(oHacerRepository.save(oHacerEntity), HttpStatus.OK);
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
            }
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody HacerEntity oHacerEntity) {
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        if (oSessionEmpleadoEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            if (oHacerRepository.existsById(id)) {

                return new ResponseEntity<HacerEntity>(oHacerRepository.save(oHacerEntity), HttpStatus.OK);
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
            oHacerRepository.deleteById(id);
            if (oHacerRepository.existsById(id)) {
                return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.OK);
            }

        }

    }

}
