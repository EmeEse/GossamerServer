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
import net.ausiasmarch.gossamerServer.repository.ClienteRepository;
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
@RequestMapping("/clientsession")
public class ClientSessionController {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    ClienteRepository oClienteRepository;

    @PostMapping("/")
    public ResponseEntity<?> login(@RequestBody UsuarioBean oUsuarioBean) {
        ClienteEntity oClienteEntity = oClienteRepository.findByLoginAndPassword(oUsuarioBean.getLogin(), oUsuarioBean.getPassword().toLowerCase());
        if (oClienteEntity != null) {
            oHttpSession.setAttribute("usuario", oClienteEntity);
            return new ResponseEntity<ClienteEntity>(oClienteEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> check() {
        ClienteEntity oSessionUsuarioEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        if (oSessionUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<ClienteEntity>(oSessionUsuarioEntity, HttpStatus.OK);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> logout() {
        oHttpSession.invalidate();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        if (oSessionEmpleadoEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<List<ClienteEntity>>(oClienteRepository.findAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        ClienteEntity oSessionClienteEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");

        if (oSessionEmpleadoEntity != null) {
            if (oClienteRepository.existsById(id)) {
                return new ResponseEntity<ClienteEntity>(oClienteRepository.getOne(id), HttpStatus.OK);
            } else {
                return new ResponseEntity<ClienteEntity>(oClienteRepository.getOne(id), HttpStatus.NOT_FOUND);
            }

        } else {
            if (oSessionClienteEntity != null) {
                if (oSessionClienteEntity.getId().equals(id)) {
                    return new ResponseEntity<ClienteEntity>(oClienteRepository.getOne(id), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }

    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {

        return new ResponseEntity<Long>(oClienteRepository.count(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody ClienteEntity oClienteEntity) {
        if (oClienteEntity.getId() == null) {
            return new ResponseEntity<ClienteEntity>(oClienteRepository.save(oClienteEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ClienteEntity oClienteEntity) {

        ClienteEntity oSessionClienteEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        //Si es empleado modifica cualquier cliente
        if (oSessionEmpleadoEntity != null) {
            if (oClienteRepository.existsById(id)) {
                return new ResponseEntity<ClienteEntity>(oClienteRepository.save(oClienteEntity), HttpStatus.OK);
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
            }

        } else {//Si es cliente, solo puede modificar el propio
            if (oSessionClienteEntity != null) {
                if (oSessionClienteEntity.getId().equals(id)) {
                    if (oClienteRepository.existsById(id)) {
                        return new ResponseEntity<ClienteEntity>(oClienteRepository.save(oClienteEntity), HttpStatus.OK);
                    } else {
                        return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                    }
                } else {
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                }
            } else {//Si no hay session no esta autorizado
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        ClienteEntity oSessionClienteEntity = (ClienteEntity) oHttpSession.getAttribute("usuario");
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");

        if (oSessionEmpleadoEntity != null) {
            if (oClienteRepository.existsById(id)) {
                oClienteRepository.deleteById(id);
                if (oClienteRepository.existsById(id)) {
                    return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<ClienteEntity>(oClienteRepository.getOne(id), HttpStatus.NOT_FOUND);
            }

        } else {
            if (oSessionClienteEntity != null) {
                if (oSessionClienteEntity.getId().equals(id)) {
                    oClienteRepository.deleteById(id);
                    if (oClienteRepository.existsById(id)) {
                        return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
                    } else {
                        return new ResponseEntity<Long>(0L, HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }

    }

    @GetMapping("/page")
    public ResponseEntity<?> getPage(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable) {
        EmpleadoEntity oSessionEmpleadoEntity = (EmpleadoEntity) oHttpSession.getAttribute("empleado");
        if (oSessionEmpleadoEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<Page<ClienteEntity>>(oClienteRepository.findAll(oPageable), HttpStatus.OK);
        }
    }
}
