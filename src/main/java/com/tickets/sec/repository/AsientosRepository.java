package com.tickets.sec.repository;

import java.util.List;

import com.tickets.sec.model.Entity.AsientosNumerado;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AsientosRepository extends JpaRepository<AsientosNumerado, Integer> {

        public List<AsientosNumerado> findByLocalidadAndZonaAndTipo(String localidad, String zona, String tipo);

        @Query("SELECT a FROM AsientosNumerado a WHERE a.localidad = :localidad AND a.zona = :zona AND a.tipo = :tipo AND a.numero IN :num_asiento")
        public List<AsientosNumerado> findSeleccionados(@Name("localidad") String localidad, @Name("zona") String zona,
                        @Name("tipo") String tipo, @Name("num_asiento") List<Integer> num_asiento);

        @Query("SELECT a FROM AsientosNumerado a WHERE a.id IN :id")
        public List<AsientosNumerado> findByIdList(@Name("id") List<Integer> id);

        @Query("SELECT COUNT(*) FROM AsientosNumerado WHERE localidad = :localidad AND zona = :zona AND tipo = :tipo AND estado ='D'")
        public int zoneAvailable(@Name("localidad") String localidad, @Name("zona") String zona,
                        @Name("tipo") String tipo);

        @Query("SELECT COUNT(*) FROM AsientosNumerado WHERE localidad = :localidad AND estado ='N'")
        public int totalVendidosLocalidad(@Name("localidad") String localidad);

        @Query("SELECT COUNT(*) FROM AsientosNumerado WHERE estado ='N'")
        public int totalVendidos();

        @Modifying
        @Query(value = "UPDATE asientos_numerados an " +
                "SET an.estado = 'D' " +
                "WHERE an.id IN (" +
                "    SELECT UNNEST(va.asientos) " +
                "    FROM ventas v " +
                "    JOIN ventas_asientos_numerados va ON v.venta_numerada = va.id " +
                "    WHERE v.tipo_venta = 'A')",
                nativeQuery = true)
        void cleanAllSitsNoAbonate();

        @Modifying
        @Query("update AsientosNumerado set estado = 'D'")
        public void cleanAllSits();

}