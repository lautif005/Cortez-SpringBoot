package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class MensajeDevService implements MensajeService {

    @Override
    public String mostrarBienvenida() {
        return "\nENTORNO DE DESARROLLO\n" +
                "Se están usando configuraciones permisivas para testing y logs DEBUG\n";
    }

    @Override
    public String mostrarDespedida() {
        return "\n DESPEDIDA DEL ENTORNO DE DESARROLLO\n" +
                "Buen trabajo en testing";
    }

}
