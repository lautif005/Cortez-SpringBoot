package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TareaService {

    @Value("${app.nombre}")
    private String nombreAplicacion;

    @Value("${app.max-tareas}")
    private int maximoTareas;

    @Value("${app.mostrar-estadisticas}")
    private boolean mostrarEstadisticas;

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public Tarea agregarTarea(String descripcion, Prioridad prioridad) {
        Tarea nuevaTarea = new Tarea(null, descripcion, false, prioridad);
        return tareaRepository.save(nuevaTarea);
    }

    public List<Tarea> listarTodas() {
        return tareaRepository.findAll();
    }

    public List<Tarea> listarPendientes() {
        return tareaRepository.findAll().stream()
                .filter(tarea -> !tarea.isCompletada())
                .collect(Collectors.toList());
    }

    public List<Tarea> listarCompletadas() {
        return tareaRepository.findAll().stream()
                .filter(Tarea::isCompletada)
                .collect(Collectors.toList());
    }

    public boolean marcarComoCompletada(Long id) {
        Optional<Tarea> tareaOpt = tareaRepository.findById(id);
        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();
            tarea.setCompletada(true);
            tareaRepository.save(tarea);
            return true;
        }
        return false;
    }

    public String obtenerEstadisticas() {
        if (!mostrarEstadisticas) {
            return "Las estadisticas estan deshabilitadas en la configuracion.";
        }
        List<Tarea> todas = listarTodas();
        long total = todas.size();
        long completadas = listarCompletadas().size();
        long pendientes = total - completadas;

        return String.format(
                "Estadisticas de Tareas:\n" +
                        " - Total: %d\n" +
                        " - Completadas: %d\n" +
                        " - Pendientes: %d\n",
                total, completadas, pendientes
        );
    }

    public void imprimirConfiguracion() {
        System.out.println("\nCONFIGURACION ACTUAL");
        System.out.println("Nombre de la aplicación: " + nombreAplicacion);
        System.out.println("Limite maximo de tareas: " + maximoTareas);
        System.out.println("Mostrar estadisticas: " + mostrarEstadisticas + "\n");
    }

    public Tarea agregarTareaValidando(String descripcion, Prioridad prioridad) {
        if (tareaRepository.findAll().size() >= maximoTareas) {
            System.err.println("ERROR: No se puede agregar la tarea. Se ha superado el limite maximo de " + maximoTareas + " tareas.");
            return null;
        }
        return agregarTarea(descripcion, prioridad);
    }

}
