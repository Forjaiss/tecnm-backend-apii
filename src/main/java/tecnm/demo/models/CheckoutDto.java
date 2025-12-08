package tecnm.demo.dtos;

public class CheckoutDto {
    private Long usuariosId;
    private Long metodosPagoId;
    private Double importeEnvio;

    public Long getUsuariosId() { return usuariosId; }
    public void setUsuariosId(Long usuariosId) { this.usuariosId = usuariosId; }
    public Long getMetodosPagoId() { return metodosPagoId; }
    public void setMetodosPagoId(Long metodosPagoId) { this.metodosPagoId = metodosPagoId; }
    public Double getImporteEnvio() { return importeEnvio; }
    public void setImporteEnvio(Double importeEnvio) { this.importeEnvio = importeEnvio; }
}