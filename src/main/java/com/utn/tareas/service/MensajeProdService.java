package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class MensajeProdService implements MensajeService {

    @Override
    public String mostrarBienvenida() {
        return "\nENTORNO DE PRODUCCION\n";
    }

    @Override
    public String mostrarDespedida() {
        return "\nDESPEDIDA DEL ENTORNO DE PRODUCCION";
    }

}
