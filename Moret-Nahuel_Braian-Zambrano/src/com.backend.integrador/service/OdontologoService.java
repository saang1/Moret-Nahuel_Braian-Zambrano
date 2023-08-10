package src.com.backend.integrador.service;

import src.com.backend.integrador.entity.Odontologo;
import src.com.backend.integrador.repository.IDao;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoDao;

    public OdontologoService(IDao<Odontologo> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return (Odontologo)this.odontologoDao.guardar(odontologo);
    }

    public void eliminar(Integer id) {
        this.odontologoDao.eliminar(id);
    }

    public Odontologo buscar(Integer id) {
        return (Odontologo)this.odontologoDao.buscar(id);
    }

    public List<Odontologo> buscarTodos() {
        return this.odontologoDao.buscarTodos();
    }

    public Odontologo actualizar(Odontologo odontologo) {
        return (Odontologo)this.odontologoDao.actualizar(odontologo);
    }
}
