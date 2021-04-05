/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.gossamerServer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author mauricio
 */
@Entity
@Table(name = "cliente")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class ClienteEntity implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String login;
    private Integer telefono;
    
    @JsonIgnore
    private String password;

    private String email;
    private String token;
    
    @JsonIgnore
    private boolean validado;
    
    @JsonIgnore
    private boolean activo;
    private Integer descuento;
    
    @JsonBackReference(value="citasCliente")
    @OneToMany(fetch=FetchType.LAZY,mappedBy="cliente", cascade={CascadeType.REFRESH})
    private List<CitaEntity> citas = new ArrayList<>(); 
    @JsonBackReference(value="clienteFactura")
    @OneToMany(fetch=FetchType.LAZY,mappedBy="clienteFactura", cascade={CascadeType.REFRESH})
    private List<FacturaEntity> facturas = new ArrayList<>(); 

    public ClienteEntity() {
    }
    
    
    
    public Long getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getLogin() {
        return login;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public boolean isValidado() {
        return validado;
    }

    public boolean isActivo() {
        return activo;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public List<CitaEntity> getCitas() {
        return citas;
    }

    public List<FacturaEntity> getFacturas() {
        return facturas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public void setCitas(List<CitaEntity> citas) {
        this.citas = citas;
    }

    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }

    @Override
    public String toString() {
        return "ClienteEntity{" + "id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", login=" + login + ", telefono=" + telefono + ", password=" + password + ", email=" + email + ", token=" + token + ", validado=" + validado + ", activo=" + activo + ", descuento=" + descuento + ", citas=" + citas + ", facturas=" + facturas + '}';
    }
    
}
