/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.gossamerServer.repository;

import net.ausiasmarch.gossamerServer.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mauricio
 */
@Repository
public interface ClienteRepository  extends JpaRepository<ClienteEntity, Long> {
    ClienteEntity findByLoginAndPassword(String login, String password);
}
