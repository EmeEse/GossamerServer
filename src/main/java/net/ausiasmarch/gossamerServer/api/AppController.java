/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.gossamerServer.api;

/**
 *
 * @author mauricio
 */

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AppController {

    @Autowired
    HttpSession oHttpSession;
   
    @GetMapping("/")
    public ResponseEntity<String> info() {
        return new ResponseEntity<String>("Welcome to gossamer", HttpStatus.OK);
    }
   
}
