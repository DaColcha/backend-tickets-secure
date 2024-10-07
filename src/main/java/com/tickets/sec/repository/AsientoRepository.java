package com.tickets.sec.repository;

import java.util.List;

import org.springframework.boot.context.properties.bind.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tickets.sec.model.Entity.Asiento;
import com.tickets.sec.model.Entity.AsientoId;

public interface AsientoRepository extends JpaRepository<Asiento, AsientoId> {

        public List<Asiento> findByLocalidadAndZonaAndTipo(String localidad, String zona, String tipo);

        @Query("SELECT a FROM Asiento a WHERE localidad = :localidad  AND estado = 'N' AND tipo_compra = 'A'")
        public List<Asiento> findAbonadoByLocalidad(@Name("localidad") String localidad);

        @Query("SELECT a FROM Asiento a WHERE localidad = :localidad AND zona = :zona AND tipo = :tipo AND num_asiento IN :num_asiento")
        public List<Asiento> findSeleccionados(@Name("localidad") String localidad, @Name("zona") String zona,
                        @Name("tipo") String tipo, @Name("num_asiento") List<Integer> num_asiento);

        @Query("SELECT a FROM Asiento a WHERE estado = 'N' AND tipo_compra = 'A'")
        public List<Asiento> findAbonados();

        @Query("SELECT COUNT(*) FROM Asiento a WHERE estado = 'N' AND tipo_compra = 'A'")
        public Integer totalAbonados();

        @Query("SELECT COUNT(*) FROM Asiento WHERE localidad = :localidad AND zona = :zona AND tipo = :tipo AND estado ='D'")
        public int zoneAvailable(@Name("localidad") String localidad, @Name("zona") String zona,
                        @Name("tipo") String tipo);

        @Query("SELECT COUNT(*) FROM Asiento WHERE localidad = :localidad AND estado ='N'")
        public int totalVendidosLocalidad(@Name("localidad") String localidad);

        @Query("SELECT COUNT(*) FROM Asiento WHERE estado ='N'")
        public int totalVendidos();

        @Modifying
        @Query("update Asiento set estado = 'D', tipo_compra = null, comprador = null, sitio_venta = null, pago = null, plazo = null where localidad = :localidad and zona = :zona and tipo = :tipo and num_asiento IN :num_asiento")
        public void cleanSitNoAbonate(@Name("localidad") String localidad, @Name("zona") String zona,
                        @Name("tipo") String tipo, @Name("num_asiento") List<Integer> num_asiento);

        @Modifying
        @Query("update Asiento set estado = 'D', tipo_compra = null, comprador = null, sitio_venta = null where tipo_compra = 'N'")
        public void cleanAllSitsNoAbonate();

        @Modifying
        @Query("update Asiento set estado = 'D', tipo_compra = null, comprador = null, sitio_venta = null")
        public void cleanAllSits();

        @Modifying
        @Query("update Asiento set estado = 'D', tipo_compra = null, comprador = null, sitio_venta = null, pago = null, plazo = null where localidad = :localidad and zona = :zona and tipo = :tipo and num_asiento IN :num_asiento")
        public void cleanAbonadoSits(@Name("localidad") String localidad, @Name("zona") String zona,
                        @Name("tipo") String tipo, @Name("num_asiento") List<Integer> num_asiento);

}