/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.gossamerServer.repository;

import net.ausiasmarch.gossamerServer.entity.CitaEntity;
import net.ausiasmarch.gossamerServer.entity.ClienteEntity;
import net.ausiasmarch.gossamerServer.entity.EmpleadoEntity;
import net.ausiasmarch.gossamerServer.entity.FacturaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mauricio
 */
@Repository
public interface FacturaRepository extends JpaRepository<FacturaEntity, Long>{
    
    Page<FacturaEntity> findByClienteFactura(ClienteEntity oClienteEntity, Pageable oPageable);
    
     Page<FacturaEntity> findByCitas(CitaEntity oCitaEntity, Pageable oPageable);
}
