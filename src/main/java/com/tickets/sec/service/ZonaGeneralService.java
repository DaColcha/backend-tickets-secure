package com.tickets.sec.service;

import com.tickets.sec.dto.CompraGeneral;
import com.tickets.sec.dto.CompraResponse;
import com.tickets.sec.model.Entity.Abonado;
import com.tickets.sec.model.Entity.Venta;
import com.tickets.sec.model.Entity.VentasZonaGeneral;
import com.tickets.sec.model.Entity.ZonaGeneral;
import com.tickets.sec.repository.*;
import com.tickets.sec.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Clase que se encarga de gestionar las ventas de boletos de la zona general
 */
@Service
@Slf4j
public class ZonaGeneralService {
    @Autowired
    private ZonaGeneralRepository zonaGeneralRepository;
    @Autowired
    private PagoRepository pagoRepository;
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private CredencialesSitioRepository credencialesSitioRepository;
    @Autowired
    private AbonadoService abonadoService;
    @Autowired
    private VentasZonaGeneralRepository ventasZonaGeneralRepository;

    /**
     * Método que regresa la cantidad de boletos disponibles en la zona general
     * @param zona
     * @return
     */
    public Integer getDisponibles(String zona) {
        return zonaGeneralRepository.findByLocalidad(zona).getDisponibles();
    }

    /**
     * Método que procesa la compra de boletos de la zona general
     * @param compra
     * @return
     */
    public CompraResponse procesarCompraGeneral(CompraGeneral compra){

        VentasZonaGeneral ventaGeneral = new VentasZonaGeneral();

        if (verificarDisponibilidad(compra.getZona(), compra.getCantidad())){

            if(isAbonado(compra.getTipoCompra()))
                compra.setComprador(abonadoService.guardarAbonado(compra.getComprador()));

            //Almacenamos venta general
            ventaGeneral = ventasZonaGeneralRepository.save(new VentasZonaGeneral(
                    zonaGeneralRepository.findByLocalidad(compra.getZona()),
                    compra.getCantidad()));

            //Almacenamos la venta
            Venta venta = new Venta();
            venta.setVentaZonaGeneral(ventaGeneral);
            venta.setPago(pagoRepository.findById(compra.getIdPago()).get());
            venta.setVendedor(credencialesSitioRepository.findByUsuario(compra.getVendedor()));
            venta.setAbonado(compra.getComprador());
            venta.setFechaVenta(java.time.LocalDate.now());
            venta.setTotalVenta(calcularTotal(compra.getCantidad()));

            ventaRepository.save(venta);

            //Actualizamos la disponibilidad
            ZonaGeneral zona = zonaGeneralRepository.findByLocalidad(compra.getZona());
            zona.setDisponibles(zona.getDisponibles() - compra.getCantidad());
            zonaGeneralRepository.save(zona);

            return new CompraResponse("aprobada", "Compra realizada con éxito", venta.getId());

        } else {
            return new CompraResponse("rechazado", "No hay suficientes boletos", 0);
        }
    }

    /**
     * Método que verifica si hay suficientes boletos en la zona general
     * @param zona
     * @param cantidad
     * @return
     */
    private boolean verificarDisponibilidad(String zona, Integer cantidad){
        return getDisponibles(zona) >= cantidad;
    }

    /**
     * Método que verifica si la compra es de un abonado
     * @param tipoCompra
     * @return
     */
    private boolean isAbonado(String tipoCompra){
        return tipoCompra.equals("A");
    }

    /**
     * Método que calcula el total de la compra
     * @param cantidad
     * @return
     */
    public BigDecimal calcularTotal(Integer cantidad){
        return new BigDecimal(cantidad * Constants.PRECIO_GENERAL);
    }

    /**
     * Método que limpia la zona general
     */
    public void limpiarGeneral(){
        Integer boletosA = this.getTotalAbonadosByZona("A");
        Integer boletosB = this.getTotalAbonadosByZona("B");

        boletosA = (boletosA != null) ? boletosA : 0;
        boletosB = (boletosB != null) ? boletosB : 0;

        zonaGeneralRepository.save(new ZonaGeneral("A", 1500 - boletosA));
        zonaGeneralRepository.save(new ZonaGeneral("B", 1500 - boletosB));

    }

    /**
     * Método que regresa el total de abonados en una zona
     * @param zona
     * @return
     */
    private Integer getTotalAbonadosByZona(String zona){
        return ventaRepository.totalAbonadosGeneralVendido(zona);
    }

    /**
     * Método que limpia la zona general
     */
    public void limpiarTodo(){
        zonaGeneralRepository.restartGeneral();
    }
}
