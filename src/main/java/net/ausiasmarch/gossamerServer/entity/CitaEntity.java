/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.gossamerServer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author mauricio
 */
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
@Entity
@Table(name = "cita")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CitaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", shape = JsonFormat.Shape.STRING)
    //@JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    //@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime fecha;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id_cliente;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id_empleado;
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id_local;
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id_hacer;
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id_factura;
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean terminado;
    
   @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonManagedReference(value="citasCliente")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
    //@JsonIgnore
    private ClienteEntity cliente;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonManagedReference(value="citasEmpleado")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_empleado", insertable = false, updatable = false)
    //@JsonIgnore
    private EmpleadoEntity empleado;
    /*
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_local")
    private LocalEntity local;
    */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonManagedReference(value="citaFactura")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_factura", insertable = false, updatable = false)
    //@JsonIgnore
    private FacturaEntity factura;
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    //@JsonBackReference
    //@JsonManagedReference(value="citaTarea")
    @OneToMany(fetch=FetchType.LAZY,mappedBy="cita", cascade={CascadeType.REFRESH})
    //@JsonIgnore
    private List<HacerEntity> tareas = new ArrayList<>(); 
    
    

    public CitaEntity() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public Long getId_empleado() {
        return id_empleado;
    }

    public Long getId_local() {
        return id_local;
    }

    public Long getId_hacer() {
        return id_hacer;
    }

    public Long getId_factura() {
        return id_factura;
    }

    public Boolean getTerminado() {
        return terminado;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    public FacturaEntity getFactura() {
        return factura;
    }

    public List<HacerEntity> getTareas() {
        return tareas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setId_empleado(Long id_empleado) {
        this.id_empleado = id_empleado;
    }

    public void setId_local(Long id_local) {
        this.id_local = id_local;
    }

    public void setId_hacer(Long id_hacer) {
        this.id_hacer = id_hacer;
    }

    public void setId_factura(Long id_factura) {
        this.id_factura = id_factura;
    }

    public void setTerminado(Boolean terminado) {
        this.terminado = terminado;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }

    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }

    public void setTareas(List<HacerEntity> tareas) {
        this.tareas = tareas;
    }

    @Override
    public String toString() {
        return "CitaEntity{" + "id=" + id + ", fecha=" + fecha + ", id_cliente=" + id_cliente + ", id_empleado=" + id_empleado + ", id_local=" + id_local + ", id_hacer=" + id_hacer + ", id_factura=" + id_factura + ", terminado=" + terminado + ", cliente=" + cliente + ", empleado=" + empleado + ", factura=" + factura + ", tareas=" + tareas + '}';
    }

    
    
    
    
}
