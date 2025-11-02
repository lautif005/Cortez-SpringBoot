package com.utn.tareas.repository;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TareaRepository {

    private final List<Tarea> tareas = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public TareaRepository() {
        tareas.add(new Tarea(nextId.getAndIncrement(), "Configurar proyecto base Spring Boot", true, Prioridad.ALTA));
        tareas.add(new Tarea(nextId.getAndIncrement(), "Crear clase Tarea y Enum Prioridad", true, Prioridad.ALTA));
        tareas.add(new Tarea(nextId.getAndIncrement(), "Implementar TareaRepository en memoria", true, Prioridad.MEDIA));
        tareas.add(new Tarea(nextId.getAndIncrement(), "Definir metodos en TareaService", true, Prioridad.BAJA));
    }

    public Tarea save(Tarea tarea) {
        if (tarea.getId() == null) {
            tarea.setId(nextId.getAndIncrement());
            tareas.add(tarea);
        } else {
            deleteById(tarea.getId());
            tareas.add(tarea);
        }
        return tarea;
    }

    public List<Tarea> findAll() {
        return new ArrayList<>(tareas);
    }

    public Optional<Tarea> findById(Long id) {
        return tareas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    public boolean deleteById(Long id) {
        return tareas.removeIf(t -> t.getId().equals(id));
    }

}
