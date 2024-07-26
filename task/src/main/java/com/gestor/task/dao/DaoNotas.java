package com.gestor.task.dao;

import com.gestor.task.Entity.Notas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public class DaoNotas implements DaoNotasInterface{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public DaoNotas(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Notas> listar() {
        String SQL = "SELECT * FROM Notas";
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(Notas.class));
    }

    @Override
    public List<Notas> listPendientes(String estado) {
        String SQL = "SELECT * FROM Notas WHERE estado = ?";
        return jdbcTemplate.query(SQL, new Object[]{estado}, BeanPropertyRowMapper.newInstance(Notas.class));
    }

    @Override
    public List<Notas> listCompletas(String estado) {
        String SQL = "SELECT * FROM Notas WHERE estado = ?";
        return jdbcTemplate.query(SQL, new Object[]{estado}, BeanPropertyRowMapper.newInstance(Notas.class));
    }
    @Override
    public void addNotas(int taskIde, String title, String content, Date fecha, String estado) {
        String SQL = "insert into Notas (taskIde, title, content, fecha, estado) values (?,?,?,?,?)";
        jdbcTemplate.update(SQL,taskIde, title, content, fecha, estado);
    }

    @Override
    public void updateNotas(int taskIde, String title, String content,Date fecha, String estado) {
        String SQL = "UPDATE notas SET title = ?, content = ?, fecha = ?, estado = ? WHERE taskIde = ?";
        jdbcTemplate.update(SQL,title, content, fecha, estado, taskIde);
    }

    @Override
    public void remove(int taskIde) {
        String SQL = "DELETE FROM Notas WHERE taskIde = ?";
        jdbcTemplate.update(SQL, taskIde);
    }
}
