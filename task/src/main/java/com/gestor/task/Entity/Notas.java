package com.gestor.task.Entity;

import java.util.Date;

public class Notas {
    private int taskIde;
    private String title;
    private String content;

    private String estado;
    private Date fecha;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTaskIde() {
        return taskIde;
    }

    public void setTaskIde(int taskIde) {
        this.taskIde = taskIde;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
