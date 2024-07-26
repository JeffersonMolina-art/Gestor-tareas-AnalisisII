package com.gestor.task.dao;

import com.gestor.task.Entity.Notas;

import java.util.Date;
import java.util.List;

public interface DaoNotasInterface {
    List<Notas> listar();
    List<Notas> listPendientes(String estado);
    List<Notas> listCompletas(String estado);

    void addNotas(int taskIde, String title, String content, Date fecha, String estado);

    void updateNotas(int taskIde, String title, String content, Date fecha,  String estado);

    void remove(int taskIde);



}
