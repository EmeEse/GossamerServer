/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.gossamerServer.api;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import net.ausiasmarch.gossamerServer.entity.CitaEntity;
import net.ausiasmarch.gossamerServer.entity.ClienteEntity;
import net.ausiasmarch.gossamerServer.entity.EmpleadoEntity;
import net.ausiasmarch.gossamerServer.entity.FacturaEntity;
import net.ausiasmarch.gossamerServer.repository.CitaRepository;
import net.ausiasmarch.gossamerServer.repository.ClienteRepository;
import net.ausiasmarch.gossamerServer.repository.FacturaRepository;
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
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    FacturaRepository oFacturaRepository;

    /*
    @Autowired
    CitaRepository oCitaRepository;

    @Autowired
    ClienteRepository oClienteRepository;
     */
    @GetMapping("/all")
    public ResponseEntity<?> all() {
        ClienteEntity oSessionClienteEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        //Si es empleado puede ver todas las facturas
        if (oSessionEmpleadoEntity != null) {
            return new ResponseEntity<List<FacturaEntity>>(oFacturaRepository.findAll(), HttpStatus.OK);
        } else {//Si es cliente, No esta autorizado
            if (oSessionClienteEntity != null) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            } else {//Si no hay session no esta autorizado
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        ClienteEntity oSessionClienteEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        
        if (oSessionEmpleadoEntity != null) {
            if (oFacturaRepository.existsById(id)) {
                return new ResponseEntity<FacturaEntity>(oFacturaRepository.findById(id).get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<FacturaEntity>(oFacturaRepository.getOne(id), HttpStatus.NOT_FOUND);
            }

        } else {
            if (oSessionClienteEntity != null) {

                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }

    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {

        return new ResponseEntity<Long>(oFacturaRepository.count(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody FacturaEntity oFacturaEntity) {
        ClienteEntity oSessionClienteEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        //Si es empleado puede crear facturas
        if (oSessionEmpleadoEntity != null) {
            if (oFacturaEntity.getId() == null) {
                return new ResponseEntity<FacturaEntity>(oFacturaRepository.save(oFacturaEntity), HttpStatus.OK);
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
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody FacturaEntity oFacturaEntity) {
        ClienteEntity oSessionClienteEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        //Si es empleado puede crear facturas
        if (oSessionEmpleadoEntity != null) {
            if (oFacturaEntity.getId() == null) {
                if (oFacturaRepository.existsById(id)) {
                    return new ResponseEntity<FacturaEntity>(oFacturaRepository.save(oFacturaEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        ClienteEntity oSessionClienteEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        //Si es empleado puede crear facturas
        if (oSessionEmpleadoEntity != null) {
            oFacturaRepository.deleteById(id);
            if (oFacturaRepository.existsById(id)) {
                return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.OK);
            }
        } else {//Si es cliente, No esta autorizado
            if (oSessionClienteEntity != null) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            } else {//Si no hay session no esta autorizado
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }

    }

    /*
    @GetMapping("/page/cliente/{id}")
    public ResponseEntity<?> getPageXCliente(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable, @PathVariable(value = "id") Long id) {

        if (oClienteRepository.existsById(id)) {
            ClienteEntity oClienteEntity = oClienteRepository.getOne(id);
            Page<FacturaEntity> oPage = oFacturaRepository.findByClienteFactura(oClienteEntity, oPageable);
            return new ResponseEntity<Page<FacturaEntity>>(oPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }

    @GetMapping("/page/cita/{id}")
    public ResponseEntity<?> getPageXCita(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable, @PathVariable(value = "id") Long id) {

        if (oCitaRepository.existsById(id)) {
            CitaEntity oCitaEntity = oCitaRepository.getOne(id);
            Page<FacturaEntity> oPage = oFacturaRepository.findByCitas(oCitaEntity, oPageable);
            return new ResponseEntity<Page<FacturaEntity>>(oPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }
     */
    @GetMapping("/page")
    public ResponseEntity<?> getPage(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable) {
        ClienteEntity oSessionClienteEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        //Si es empleado puede ver todas las facturas
        if (oSessionEmpleadoEntity != null) {
            return new ResponseEntity<Page<FacturaEntity>>(oFacturaRepository.findAll(oPageable), HttpStatus.OK);
        } else {//Si es cliente, No esta autorizado
            if (oSessionClienteEntity != null) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            } else {//Si no hay session no esta autorizado
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }

    }

}
