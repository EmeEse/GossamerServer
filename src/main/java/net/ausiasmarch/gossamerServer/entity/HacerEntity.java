/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.gossamerServer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
@Entity
@Table(name = "hacer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HacerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id_cita;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id_trabajo;
    
    //@JsonBackReference(value="citaTarea")
    //@JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_cita", insertable = false, updatable = false)
    private HacerEntity cita;
    
    //@JsonBackReference
    
    //@JsonManagedReference(value="hacerTrabajo")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_trabajo", insertable = false, updatable = false)
    private TrabajoEntity trabajo;

    
    public HacerEntity() {
    }

    public Long getId() {
        return id;
    }

    public Long getId_cita() {
        return id_cita;
    }

    public Long getId_trabajo() {
        return id_trabajo;
    }

    public HacerEntity getCita() {
        return cita;
    }

    public TrabajoEntity getTrabajo() {
        return trabajo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setId_cita(Long id_cita) {
        this.id_cita = id_cita;
    }

    public void setId_trabajo(Long id_trabajo) {
        this.id_trabajo = id_trabajo;
    }

    public void setCita(HacerEntity cita) {
        this.cita = cita;
    }

    public void setTrabajo(TrabajoEntity trabajo) {
        this.trabajo = trabajo;
    }

    @Override
    public String toString() {
        return "HacerEntity{" + "id=" + id + ", id_cita=" + id_cita + ", id_trabajo=" + id_trabajo + ", cita=" + cita + ", trabajo=" + trabajo + '}';
    }
    
}
