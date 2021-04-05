/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.gossamerServer.api;

import java.util.List;
import javax.servlet.http.HttpSession;
import net.ausiasmarch.gossamerServer.bean.UsuarioBean;
import net.ausiasmarch.gossamerServer.entity.CitaEntity;
import net.ausiasmarch.gossamerServer.entity.ClienteEntity;
import net.ausiasmarch.gossamerServer.entity.EmpleadoEntity;

import net.ausiasmarch.gossamerServer.repository.EmpleadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/employeesession")
public class EmployeeSessionController {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    EmpleadoRepository oEmpleadoRepository;

    /*
    @Autowired
     LocalRepository oLocalRepository;
     */
    @PostMapping("/")
    public ResponseEntity<?> login(@RequestBody UsuarioBean oUsuarioBean) {
        EmpleadoEntity oEmpleadoEntity = oEmpleadoRepository.findByLoginAndPassword(oUsuarioBean.getLogin(), oUsuarioBean.getPassword().toLowerCase());
        if (oEmpleadoEntity != null) {
            oHttpSession.setAttribute("empleado", oEmpleadoEntity);
            return new ResponseEntity<EmpleadoEntity>(oEmpleadoEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> check() {
        EmpleadoEntity oSessionUsuarioEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        if (oSessionUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<EmpleadoEntity>(oSessionUsuarioEntity, HttpStatus.OK);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> logout() {
        oHttpSession.invalidate();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {

        if (false) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<List<EmpleadoEntity>>(oEmpleadoRepository.findAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        if (oEmpleadoRepository.existsById(id)) {
            return new ResponseEntity<EmpleadoEntity>(oEmpleadoRepository.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<EmpleadoEntity>(oEmpleadoRepository.getOne(id), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {

        return new ResponseEntity<Long>(oEmpleadoRepository.count(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody EmpleadoEntity oEmpleadoEntity) {

        ClienteEntity oSessionClienteEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        //Si es empleado puede agregar un empleado
        if (oSessionEmpleadoEntity != null) {
            if (oEmpleadoEntity.getId() == null) {

                return new ResponseEntity<EmpleadoEntity>(oEmpleadoRepository.save(oEmpleadoEntity), HttpStatus.OK);
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);

            }
        } else {//Si es cliente, No esta autorizado
            if (oSessionClienteEntity != null) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            } else {//Si no hay session no esta autorizado
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody EmpleadoEntity oEmpleadoEntity) {
        ClienteEntity oSessionClienteEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        //Si es empleado puede agregar un empleado
        if (oSessionEmpleadoEntity != null) {
            if (oEmpleadoRepository.existsById(id)) {
                return new ResponseEntity<EmpleadoEntity>(oEmpleadoRepository.save(oEmpleadoEntity), HttpStatus.OK);
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
            }
        } else {//Si es cliente, No esta autorizado
            if (oSessionClienteEntity != null) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            } else {//Si no hay session no esta autorizado
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

        ClienteEntity oSessionClienteEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        //Si es empleado modifica cualquier cliente
        if (oSessionEmpleadoEntity != null) {
            if (oEmpleadoRepository.existsById(id)) {
                oEmpleadoRepository.deleteById(id);
                if (oEmpleadoRepository.existsById(id)) {
                    return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
            }
        } else {//Si es cliente, No esta autorizado
            if (oSessionClienteEntity != null) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            } else {//Si no hay session no esta autorizado
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }

    }

    @GetMapping("/page")
    public ResponseEntity<?> getPage(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable) {

        return new ResponseEntity<Page<EmpleadoEntity>>(oEmpleadoRepository.findAll(oPageable), HttpStatus.OK);

    }
    /*
    @GetMapping("/page/local/{id}")
    public ResponseEntity<?> getPageXLocal(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable, @PathVariable(value = "id") Long id) {


        if (oLocalRepository.existsById(id)) {
            LocalEntity oLocalEntity = oLocalRepository.getOne(id);
            Page<EmpleadoEntity> oPage = oEmpleadoRepository.findByLocal(oLocalEntity, oPageable);
            //Page<EmpleadoEntity> oPage = oEmpleadoRepository.findByLocal(oLocalEntity, oPageable);
            return new ResponseEntity<Page<EmpleadoEntity>>(oPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }
     */
}
