package com.tickets.sec.repository;

import java.util.List;

import com.tickets.sec.model.Entity.AsientosNumerado;
import com.tickets.sec.model.Entity.AsientosNumeradoId;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AsientosRepository extends JpaRepository<AsientosNumerado, AsientosNumeradoId> {

        public List<AsientosNumerado> findByLocalidadAndZonaAndTipo(String localidad, String zona, String tipo);

        @Query("SELECT a FROM AsientosNumerado a WHERE localidad = :localidad  AND estado = 'N'")
        public List<AsientosNumerado> findAbonadoByLocalidad(@Name("localidad") String localidad);

        @Query("SELECT a FROM AsientosNumerado a WHERE localidad = :localidad AND zona = :zona AND tipo = :tipo AND numero IN :num_asiento")
        public List<AsientosNumerado> findSeleccionados(@Name("localidad") String localidad, @Name("zona") String zona,
                        @Name("tipo") String tipo, @Name("num_asiento") List<Integer> num_asiento);

        @Query("SELECT a FROM AsientosNumerado a WHERE estado = 'N'")
        public List<AsientosNumerado> findAbonados();

        @Query("SELECT COUNT(*) FROM AsientosNumerado a WHERE estado = 'N'")
        public Integer totalAbonados();

        @Query("SELECT COUNT(*) FROM AsientosNumerado WHERE localidad = :localidad AND zona = :zona AND tipo = :tipo AND estado ='D'")
        public int zoneAvailable(@Name("localidad") String localidad, @Name("zona") String zona,
                        @Name("tipo") String tipo);

        @Query("SELECT COUNT(*) FROM AsientosNumerado WHERE localidad = :localidad AND estado ='N'")
        public int totalVendidosLocalidad(@Name("localidad") String localidad);

        @Query("SELECT COUNT(*) FROM AsientosNumerado WHERE estado ='N'")
        public int totalVendidos();

        @Modifying
        @Query("update AsientosNumerado set estado = 'D' where localidad = :localidad and zona = :zona and tipo = :tipo and numero IN :num_asiento")
        public void cleanSitNoAbonate(@Name("localidad") String localidad, @Name("zona") String zona,
                        @Name("tipo") String tipo, @Name("num_asiento") List<Integer> num_asiento);

        //TODO: Cambiar el estado de todos los asientos QUE NO FUERON COMPRADOS POR ABONADOS a Disponible

        @Modifying
        @Query("update AsientosNumerado set estado = 'D'")
        public void cleanAllSits();

        @Modifying
        @Query("update AsientosNumerado set estado = 'D' where localidad = :localidad and zona = :zona and tipo = :tipo and numero IN :numeros")
        public void cleanAbonadoSits(@Name("localidad") String localidad, @Name("zona") String zona,
                        @Name("tipo") String tipo, @Name("num_asiento") List<Integer> numeros);

}