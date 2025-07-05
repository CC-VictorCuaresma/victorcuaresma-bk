package pe.edu.vallegrande.victorcuaresma.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "ruc_data")
public class Ruc {
    
    @Id
    private String id;
    
    @Field("ruc")
    private String ruc;
    
    @Field("razonSocial")
    private String razonSocial;
    
    @Field("nombreComercial")
    private String nombreComercial;
    
    @Field("telefonos")
    private List<String> telefonos;
    
    @Field("tipo")
    private String tipo;
    
    @Field("estado")
    private String estado;
    
    @Field("condicion")
    private String condicion;
    
    @Field("direccion")
    private String direccion;
    
    @Field("departamento")
    private String departamento;
    
    @Field("provincia")
    private String provincia;
    
    @Field("distrito")
    private String distrito;
    
    @Field("fechaInscripcion")
    private String fechaInscripcion;
    
    @Field("sistEmsion")
    private String sistEmsion;
    
    @Field("sistContabilidad")
    private String sistContabilidad;
    
    @Field("actExterior")
    private String actExterior;
    
    @Field("actEconomicas")
    private List<String> actEconomicas;
    
    @Field("cpPago")
    private List<String> cpPago;
    
    @Field("sistElectronica")
    private List<String> sistElectronica;
    
    @Field("fechaEmisorFe")
    private String fechaEmisorFe;
    
    @Field("cpeElectronico")
    private List<String> cpeElectronico;
    
    @Field("fechaPle")
    private String fechaPle;
    
    @Field("padrones")
    private List<String> padrones;
    
    @Field("fechaBaja")
    private String fechaBaja;
    
    @Field("profesion")
    private String profesion;
    
    @Field("ubigeo")
    private String ubigeo;
    
    @Field("capital")
    private String capital;
    
    // Campos para borrado lógico y auditoria
    @Field("isDelete")
    private Boolean isDelete = false;
    
    @Field("createdAt")
    private LocalDateTime createdAt;
    
    @Field("updatedAt")
    private LocalDateTime updatedAt;
    
    // Constructors
    public Ruc() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isDelete = false;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getRuc() {
        return ruc;
    }
    
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    
    public String getRazonSocial() {
        return razonSocial;
    }
    
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    
    public String getNombreComercial() {
        return nombreComercial;
    }
    
    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }
    
    public List<String> getTelefonos() {
        return telefonos;
    }
    
    public void setTelefonos(List<String> telefonos) {
        this.telefonos = telefonos;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getCondicion() {
        return condicion;
    }
    
    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getDepartamento() {
        return departamento;
    }
    
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    public String getProvincia() {
        return provincia;
    }
    
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    public String getDistrito() {
        return distrito;
    }
    
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
    
    public String getFechaInscripcion() {
        return fechaInscripcion;
    }
    
    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
    
    public String getSistEmsion() {
        return sistEmsion;
    }
    
    public void setSistEmsion(String sistEmsion) {
        this.sistEmsion = sistEmsion;
    }
    
    public String getSistContabilidad() {
        return sistContabilidad;
    }
    
    public void setSistContabilidad(String sistContabilidad) {
        this.sistContabilidad = sistContabilidad;
    }
    
    public String getActExterior() {
        return actExterior;
    }
    
    public void setActExterior(String actExterior) {
        this.actExterior = actExterior;
    }
    
    public List<String> getActEconomicas() {
        return actEconomicas;
    }
    
    public void setActEconomicas(List<String> actEconomicas) {
        this.actEconomicas = actEconomicas;
    }
    
    public List<String> getCpPago() {
        return cpPago;
    }
    
    public void setCpPago(List<String> cpPago) {
        this.cpPago = cpPago;
    }
    
    public List<String> getSistElectronica() {
        return sistElectronica;
    }
    
    public void setSistElectronica(List<String> sistElectronica) {
        this.sistElectronica = sistElectronica;
    }
    
    public String getFechaEmisorFe() {
        return fechaEmisorFe;
    }
    
    public void setFechaEmisorFe(String fechaEmisorFe) {
        this.fechaEmisorFe = fechaEmisorFe;
    }
    
    public List<String> getCpeElectronico() {
        return cpeElectronico;
    }
    
    public void setCpeElectronico(List<String> cpeElectronico) {
        this.cpeElectronico = cpeElectronico;
    }
    
    public String getFechaPle() {
        return fechaPle;
    }
    
    public void setFechaPle(String fechaPle) {
        this.fechaPle = fechaPle;
    }
    
    public List<String> getPadrones() {
        return padrones;
    }
    
    public void setPadrones(List<String> padrones) {
        this.padrones = padrones;
    }
    
    public String getFechaBaja() {
        return fechaBaja;
    }
    
    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }
    
    public String getProfesion() {
        return profesion;
    }
    
    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
    
    public String getUbigeo() {
        return ubigeo;
    }
    
    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }
    
    public String getCapital() {
        return capital;
    }
    
    public void setCapital(String capital) {
        this.capital = capital;
    }
    
    public Boolean getIsDelete() {
        return isDelete;
    }
    
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
        this.updatedAt = LocalDateTime.now();
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
