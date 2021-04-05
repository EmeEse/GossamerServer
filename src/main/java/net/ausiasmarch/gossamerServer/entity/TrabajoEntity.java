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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author mauricio
 */
@Entity
@Table(name = "trabajo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TrabajoEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Long imagen;
    private Integer descuento;  

    //@JsonManagedReference
    //@JsonBackReference(value="hacerTrabajo")
    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY,mappedBy="trabajo", cascade={CascadeType.REFRESH})
    private List<HacerEntity> tareas = new ArrayList<>(); 
    /*
    //@JsonBackReference
    @JsonManagedReference(value="hacerTrabajo")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_hacer")
    private HacerEntity trabajo;
    */
    
    public TrabajoEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public Long getImagen() {
        return imagen;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public List<HacerEntity> getTareas() {
        return tareas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setImagen(Long imagen) {
        this.imagen = imagen;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public void setTareas(List<HacerEntity> tareas) {
        this.tareas = tareas;
    }

    @Override
    public String toString() {
        return "TrabajoEntity{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + ", imagen=" + imagen + ", descuento=" + descuento + ", tareas=" + tareas + '}';
    }
    
    
    
    
    
    
}
