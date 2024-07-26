package com.gestor.task.Servicis;

import com.gestor.task.Entity.Notas;
import com.gestor.task.dao.DaoNotasInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotasServices implements NotasServicesInter {

    @Autowired
    private DaoNotasInterface daoNotas;

    @Override
    public List<Notas> listar() {
        return daoNotas.listar();
    }

    @Override
    public List<Notas> listPendientes(String estado) {
        return daoNotas.listPendientes(estado);
    }

    @Override
    public List<Notas> listCompletas(String estado) {
        return daoNotas.listCompletas(estado);
    }

    @Override
    public void addNotas(int taskIde, String title, String content, Date fecha, String estado) {
        Thread thread = new Thread(() -> daoNotas.addNotas(taskIde,title, content, fecha, estado));
        thread.start();
    }

    @Override
    public void updateNotas(int taskIde, String title, String content, Date fecha, String estado) {
        Thread thread = new Thread(() -> daoNotas.updateNotas(taskIde, title, content, fecha, estado));
        thread.start();
    }

    @Override
    public void remove(int taskIde) {
        Thread thread = new Thread(() -> daoNotas.remove(taskIde));
        thread.start();
    }
}
