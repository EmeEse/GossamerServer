

package net.ausiasmarch.gossamerServer.api;

import java.util.List;
import javax.servlet.http.HttpSession;
import net.ausiasmarch.gossamerServer.entity.ClienteEntity;
import net.ausiasmarch.gossamerServer.entity.EmpleadoEntity;

import net.ausiasmarch.gossamerServer.repository.EmpleadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
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
        
        /*
@RestController
@RequestMapping("/local")

public class LocalController {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    LocalRepository oLocalRepository;
    
    @Autowired
    EmpleadoRepository oEmpleadoRepository;
            
    @GetMapping("/all")
    public ResponseEntity<?> all() {

        if (false) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<List<LocalEntity>>(oLocalRepository.findAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        if (oLocalRepository.existsById(id)) {
            return new ResponseEntity<LocalEntity>(oLocalRepository.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<LocalEntity>(oLocalRepository.getOne(id), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {

        return new ResponseEntity<Long>(oLocalRepository.count(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody LocalEntity oLocalEntity) {
        if (oLocalEntity.getId() == null) {
            return new ResponseEntity<LocalEntity>(oLocalRepository.save(oLocalEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody LocalEntity oLocalEntity) {
        if (oLocalRepository.existsById(id)) {
            return new ResponseEntity<LocalEntity>(oLocalRepository.save(oLocalEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        oLocalRepository.deleteById(id);
        if (oLocalRepository.existsById(id)) {
            return new ResponseEntity<Long>(id, HttpStatus.NOT_MODIFIED);
        } else {
            return new ResponseEntity<Long>(0L, HttpStatus.OK);
        }
    }
     @GetMapping("/page/empleado/{id}")
    public ResponseEntity<?> getPageXEmpleado(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable oPageable, @PathVariable(value = "id") Long id) {

//        Page<CompraEntity> oPage = oCompraRepository.findByCompraXProducto(id, oPageable);
//        return new ResponseEntity<Page<CompraEntity>>(oPage, HttpStatus.OK);
        if (oEmpleadoRepository.existsById(id)) {
            EmpleadoEntity oEmpleadoEntity = oEmpleadoRepository.getOne(id);
            Page<LocalEntity> oPage = oLocalRepository.findByEmpleados(oEmpleadoEntity, oPageable);
            return new ResponseEntity<Page<LocalEntity>>(oPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }
    
    @GetMapping("/page")
    public ResponseEntity<?> getPage(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable oPageable) {

  
                return new ResponseEntity<Page<LocalEntity>>(oLocalRepository.findAll(oPageable), HttpStatus.OK);

            
        }

    }
    
*/
