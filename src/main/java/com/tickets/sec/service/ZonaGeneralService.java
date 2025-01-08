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
import java.util.UUID;

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


    public Integer getDisponibles(String zona) {
        return zonaGeneralRepository.findByLocalidad(zona).getDisponibles();
    }

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

    private boolean verificarDisponibilidad(String zona, Integer cantidad){
        return getDisponibles(zona) >= cantidad;
    }

    private boolean isAbonado(String tipoCompra){
        return tipoCompra.equals("A");
    }

    public BigDecimal calcularTotal(Integer cantidad){
        return new BigDecimal(cantidad * Constants.PRECIO_GENERAL);
    }

    public void limpiarGeneral(){
        Integer boletosA = ventaRepository.totalAbonadosGeneralVendido("A");
        Integer boletosB = ventaRepository.totalAbonadosGeneralVendido("B");

        boletosA = (boletosA != null) ? boletosA : 0;
        boletosB = (boletosB != null) ? boletosB : 0;

        zonaGeneralRepository.save(new ZonaGeneral("A", 1500 - boletosA));
        zonaGeneralRepository.save(new ZonaGeneral("B", 1500 - boletosB));

    }

    public void limpiarTodo(){
        zonaGeneralRepository.restartGeneral();
    }
}
