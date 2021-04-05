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
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
@Entity
@Table(name = "factura")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class FacturaEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime fecha;
    
    
    private Integer iva;
    @JsonBackReference(value="clienteFactura")
    @ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.REFRESH})
    @JoinColumn(name="id_cliente")
    private ClienteEntity clienteFactura;  
     
    private Boolean pagado;
    
   
    @JsonBackReference(value="citaFactura")
    @OneToMany(fetch=FetchType.LAZY,mappedBy="factura", cascade={CascadeType.REFRESH})
    private List<CitaEntity> citas = new ArrayList<>(); 

    public FacturaEntity() {
    }

    
    public Long getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Integer getIva() {
        return iva;
    }

    public ClienteEntity getClienteFactura() {
        return clienteFactura;
    }

    public Boolean getPagado() {
        return pagado;
    }

    public List<CitaEntity> getCitas() {
        return citas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setIva(Integer iva) {
        this.iva = iva;
    }

    public void setClienteFactura(ClienteEntity clienteFactura) {
        this.clienteFactura = clienteFactura;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    public void setCitas(List<CitaEntity> citas) {
        this.citas = citas;
    }

    @Override
    public String toString() {
        return "FacturaEntity{" + "id=" + id + ", fecha=" + fecha + ", iva=" + iva + ", clienteFactura=" + clienteFactura + ", pagado=" + pagado + ", citas=" + citas + '}';
    }
    
    
}
