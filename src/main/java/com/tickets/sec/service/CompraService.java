package com.tickets.sec.service;

import com.tickets.sec.dto.CompraNumerados;
import com.tickets.sec.dto.CompraResponse;
import com.tickets.sec.model.Entity.AsientosNumerado;
import com.tickets.sec.model.Entity.Venta;
import com.tickets.sec.model.Entity.VentasAsientosNumerado;
import com.tickets.sec.repository.*;
import com.tickets.sec.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CompraService {

    @Autowired
    private AsientosRepository asientoRepository;
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private VentasAsientosNumeradosRepository ventaAsientosNumeradoRepository;
    @Autowired
    private CredencialesSitioRepository credencialesSitioRepository;
    @Autowired
    private AbonadoService abonadoService;
    @Autowired
    private PagoRepository pagoRepository;

    public CompraResponse procesarCompra(CompraNumerados compra, UUID pagoId) {
        //Buscamos los objetos tipo asiento según los seleccionados en la compra
        List<AsientosNumerado> seleccionados = asientoRepository.findSeleccionados(compra.getLocalidad(), compra.getZona(),
                compra.getTipo(), compra.getAsientosSeleccionados());


        if (seleccionados.isEmpty()) {
            return new CompraResponse("rechazada", "No se encontraron asientos seleccionados");
        } else {

            VentasAsientosNumerado v = new VentasAsientosNumerado();

            List<Integer> asientosList = seleccionados.stream()
                    .map(AsientosNumerado::getId)
                    .collect(java.util.stream.Collectors.toList());

            v.setAsientos(asientosList);
            v.setCantidad(seleccionados.size());

            VentasAsientosNumerado ventaNumeradaSaved = ventaAsientosNumeradoRepository.save(v);

            //Guardamos el abonado si es que es una compra de tipo abonado
            if (isAbonado(compra.getTipoCompra()))
                compra.setComprador(abonadoService.guardarAbonado(compra.getComprador()));

            seleccionados.forEach(a -> {
                this.setAsientoOcupado(a);

                Venta venta = new Venta();
                venta.setVentaNumerada(ventaNumeradaSaved);
                venta.setPago(pagoId != null ? pagoRepository.findById(pagoId).get() : null);
                venta.setVendedor(credencialesSitioRepository.findByUsuario(compra.getVendedor()));
                venta.setAbonado(compra.getComprador());
                venta.setFechaVenta(java.time.LocalDate.now());
                venta.setTotalVenta(getTotalVenta(compra.getLocalidad(), compra.getTipo(), compra.getAsientosSeleccionados().size()));

                ventaRepository.save(venta);
            });

            return new CompraResponse("aprobada", "Compra realizada con éxito");
        }
    }

    public BigDecimal getTotalVenta (String localidad, String tipo, int cantidad){
        return java.math.BigDecimal.valueOf(getCostoAsiento(localidad, tipo) * cantidad);
    }

    public double getCostoAsiento (String localidad, String tipo) {
            try {
                return Double.parseDouble(String.valueOf(Constants.class.getField("PRECIO_" + localidad + "_" + tipo).get(null)));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
    }

    public void setAsientoOcupado (AsientosNumerado asiento){
        asiento.setEstado("N");
        asientoRepository.save(asiento);
    }

    public boolean isAbonado (String tipoCompra){
        return tipoCompra.equals("A");
    }

}