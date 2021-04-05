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
import net.ausiasmarch.gossamerServer.entity.FacturaEntity;
import net.ausiasmarch.gossamerServer.entity.HacerEntity;

import net.ausiasmarch.gossamerServer.entity.TrabajoEntity;
import net.ausiasmarch.gossamerServer.repository.CitaRepository;
import net.ausiasmarch.gossamerServer.repository.ClienteRepository;
import net.ausiasmarch.gossamerServer.repository.EmpleadoRepository;
import net.ausiasmarch.gossamerServer.repository.FacturaRepository;
import net.ausiasmarch.gossamerServer.repository.HacerRepository;

import net.ausiasmarch.gossamerServer.repository.TrabajoRepository;
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
@RequestMapping("/cita")
public class CitaController {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    CitaRepository oCitaRepository;

    /*
     @Autowired
     ClienteRepository oClienteRepository;
     
     @Autowired
     EmpleadoRepository oEmpleadoRepository;
     
     @Autowired
     LocalRepository oLocalRepository;
     
     @Autowired
     HacerRepository oHacerRepository;
     
     @Autowired
     FacturaRepository oFacturaRepository;
     */
    @GetMapping("/all")
    public ResponseEntity<?> all() {
        ClienteEntity oSessionClienteEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");

        if (oSessionEmpleadoEntity != null) {
            return new ResponseEntity<List<CitaEntity>>(oCitaRepository.findAll(), HttpStatus.OK);

        } else {
            if (oSessionClienteEntity != null) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        ClienteEntity oSessionClienteEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");

        if (oSessionEmpleadoEntity != null) {
            if (oCitaRepository.existsById(id)) {
                return new ResponseEntity<CitaEntity>(oCitaRepository.findById(id).get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<CitaEntity>(oCitaRepository.getOne(id), HttpStatus.NOT_FOUND);
            }

        } else {
            if (oSessionClienteEntity != null) {
                if (oCitaRepository.findById(id).get().getCliente().getId().equals(oSessionClienteEntity.getId())) {

                    return new ResponseEntity<CitaEntity>(oCitaRepository.findById(id).get(), HttpStatus.OK);

                } else {
                    return new ResponseEntity<CitaEntity>(oCitaRepository.getOne(id), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }

    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {

        return new ResponseEntity<Long>(oCitaRepository.count(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CitaEntity oCitaEntity) {
        ClienteEntity oSessionClienteEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");

        if (oSessionEmpleadoEntity != null) {
            if (oCitaEntity.getId() == null) {
                return new ResponseEntity<CitaEntity>(oCitaRepository.save(oCitaEntity), HttpStatus.OK);
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
            }

        } else {
            if (oSessionClienteEntity != null) {
                if (oCitaEntity.getId() == null) {
                    return new ResponseEntity<CitaEntity>(oCitaRepository.save(oCitaEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody CitaEntity oCitaEntity) {
        ClienteEntity oSessionClienteEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");

        if (oSessionEmpleadoEntity != null) {
            if (oCitaRepository.existsById(id)) {
                return new ResponseEntity<CitaEntity>(oCitaRepository.save(oCitaEntity), HttpStatus.OK);
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
            }

        } else {
            if (oSessionClienteEntity != null) {
                if (oCitaRepository.existsById(id)) {
                    return new ResponseEntity<CitaEntity>(oCitaRepository.save(oCitaEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        ClienteEntity oSessionClienteEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");

        if (oSessionEmpleadoEntity != null) {
            oCitaRepository.deleteById(id);
            if (oCitaRepository.existsById(id)) {
                return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.OK);
            }

        } else {
            if (oSessionClienteEntity != null) {
                oCitaRepository.deleteById(id);
                if (oCitaRepository.existsById(id)) {
                    return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }

    }

    @GetMapping("/page")
    public ResponseEntity<?> getPage(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable) {
        ClienteEntity oSessionClienteEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");

        if (oSessionEmpleadoEntity != null) {
            return new ResponseEntity<Page<CitaEntity>>(oCitaRepository.findAll(oPageable), HttpStatus.OK);

        } else {
            if (oSessionClienteEntity != null) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }

    }

    /*
    @GetMapping("/page/cliente/{id}")
    public ResponseEntity<?> getPageXCliente(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable, @PathVariable(value = "id") Long id) {

        if (oClienteRepository.existsById(id)) {
            ClienteEntity oClienteEntity = oClienteRepository.getOne(id);
            Page<CitaEntity> oPage = oCitaRepository.findByCliente(oClienteEntity, oPageable);
            return new ResponseEntity<Page<CitaEntity>>(oPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }
    
     @GetMapping("/page/empleado/{id}")
    public ResponseEntity<?> getPageXEmpleado(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable, @PathVariable(value = "id") Long id) {


        if (oEmpleadoRepository.existsById(id)) {
            EmpleadoEntity oEmpleadoEntity = oEmpleadoRepository.getOne(id);
            Page<CitaEntity> oPage = oCitaRepository.findByEmpleado(oEmpleadoEntity, oPageable);
            return new ResponseEntity<Page<CitaEntity>>(oPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }
    
     @GetMapping("/page/local/{id}")
    public ResponseEntity<?> getPageXLocal(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable, @PathVariable(value = "id") Long id) {


        if (oLocalRepository.existsById(id)) {
            LocalEntity oLocalEntity = oLocalRepository.getOne(id);
            Page<CitaEntity> oPage = oCitaRepository.findByLocal(oLocalEntity, oPageable);
            return new ResponseEntity<Page<CitaEntity>>(oPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }
    
     @GetMapping("/page/hacer/{id}")
    public ResponseEntity<?> getPageXHacer(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable, @PathVariable(value = "id") Long id) {


        if (oHacerRepository.existsById(id)) {
            HacerEntity oHacerEntity = oHacerRepository.getOne(id);
            Page<CitaEntity> oPage = oCitaRepository.findByHacer(oHacerEntity, oPageable);
            return new ResponseEntity<Page<CitaEntity>>(oPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }
    
     @GetMapping("/page/factura/{id}")
    public ResponseEntity<?> getPageXFactura(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable, @PathVariable(value = "id") Long id) {


        if (oFacturaRepository.existsById(id)) {
            FacturaEntity oFacturaEntity = oFacturaRepository.getOne(id);
            Page<CitaEntity> oPage = oCitaRepository.findByFactura(oFacturaEntity, oPageable);
            return new ResponseEntity<Page<CitaEntity>>(oPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }
     */
}
