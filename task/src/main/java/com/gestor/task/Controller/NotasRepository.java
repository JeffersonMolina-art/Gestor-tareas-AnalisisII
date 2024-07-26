package com.gestor.task.Controller;

import com.gestor.task.Entity.Notas;
import com.gestor.task.Servicis.NotasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/notas")
@CrossOrigin("*")
public class NotasRepository {

    @Autowired
    private NotasServices notasServices;

    @GetMapping("/listar")
    public List<Notas> listar() {
        return notasServices.listar();
    }

    @GetMapping("/pendientes")
    public List<Notas> listPendientes() {
        return notasServices.listPendientes("pendiente");
    }

    @GetMapping("/completas")
    public List<Notas> listCompletas() {
        return notasServices.listCompletas("completada");
    }

    @PostMapping("/agregar")
    public void addNotas(@RequestBody Notas nota) {
        notasServices.addNotas(nota.getTaskIde(), nota.getTitle(), nota.getContent(), new Date(), nota.getEstado());
    }

    @PutMapping("/actualizar/{taskIde}")
    public void updateNotas(
            @PathVariable int taskIde,
            @RequestBody Notas nota
    ) {
        notasServices.updateNotas(taskIde, nota.getTitle(), nota.getContent(), nota.getFecha(), nota.getEstado());
    }


    @DeleteMapping("/eliminar/{taskIde}")
    public void remove(@PathVariable int taskIde) {
        notasServices.remove(taskIde);
    }
}
