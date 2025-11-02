package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareasApplication implements CommandLineRunner {

	private final TareaService tareaService;
	private final MensajeService mensajeService;

	public TareasApplication(TareaService tareaService, MensajeService mensajeService) {
		this.tareaService = tareaService;
		this.mensajeService = mensajeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(TareasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("\nINICIANDO PRUEBAS DE LA APLICACION DE TAREAS\n");
		System.out.println(mensajeService.mostrarBienvenida());
		tareaService.imprimirConfiguracion();
		System.out.println("\n");

		System.out.println("\nTareas Iniciales");
		tareaService.listarTodas().forEach(System.out::println);
		System.out.println("\n");

		System.out.println("\nAgregando nueva tarea...");
		Tarea nueva = tareaService.agregarTareaValidando("Diseñando la interfaz de usuario", Prioridad.BAJA);
		if (nueva != null) {
			System.out.println("Tarea agregada: " + nueva);
		}
		System.out.println("\n");

		System.out.println("\nPrueba de limite (Si esta en DEV el limite es 10, si es PROD es 1000)");
		tareaService.agregarTareaValidando("Tarea extra para probar limite", Prioridad.MEDIA);
		System.out.println("\n");

		System.out.println("\nTareas Pendientes");
		tareaService.listarPendientes().forEach(System.out::println);
		System.out.println("\n");

		System.out.println("\nMarcando Tarea ID 3 como Completada");
		if (tareaService.marcarComoCompletada(3L)) {
			System.out.println("Tarea ID 3 marcada como completada");
		} else {
			System.out.println("Error al marcar tarea ID 3");
		}
		System.out.println("\n");

		System.out.println("\nEstadisticas de Tareas");
		System.out.println(tareaService.obtenerEstadisticas());
		System.out.println("\n");

		System.out.println("\nTareas Completadas");
		tareaService.listarCompletadas().forEach(System.out::println);
		System.out.println("\n");

		System.out.println(mensajeService.mostrarDespedida());

		System.out.println("\nFIN DE PRUEBAS\n");

	}

}
