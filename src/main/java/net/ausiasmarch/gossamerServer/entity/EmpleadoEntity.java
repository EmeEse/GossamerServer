/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.gossamerServer.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.lang.Nullable;

/**
 *
 * @author mauricio
 */
@Entity
@Table(name = "empleado")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class EmpleadoEntity implements Serializable{
   
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
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    private String email;
    private String token;
    @JsonProperty(access = Access.WRITE_ONLY)
    private boolean validado;
    @JsonProperty(access = Access.WRITE_ONLY)
    private boolean activo;
    
    /*
    @JsonBackReference
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.REFRESH})
    @JoinColumn(name="id_local")
    private LocalEntity local;
    */
    private Long id_local;
    
    
    @JsonIgnore
    //@JsonBackReference(value="citasEmpleado")
    @OneToMany(fetch=FetchType.LAZY,mappedBy="empleado", cascade={CascadeType.REFRESH})
    private List<CitaEntity> citas = new ArrayList<>(); 

    public EmpleadoEntity() {
    }

    public Long getId_local() {
        return id_local;
    }

    public void setId_local(Long id_local) {
        this.id_local = id_local;
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

    public List<CitaEntity> getCitas() {
        return citas;
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

    public void setCitas(List<CitaEntity> citas) {
        this.citas = citas;
    }

    @Override
    public String toString() {
        return "EmpleadoEntity{" + "id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", login=" + login + ", telefono=" + telefono + ", password=" + password + ", email=" + email + ", token=" + token + ", validado=" + validado + ", activo=" + activo + ", citas=" + citas + '}';
    }

   

    
    
}
