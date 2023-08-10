package src.com.backend.integrador.repository.impl;

import src.com.backend.integrador.entity.Odontologo;
import src.com.backend.integrador.repository.IDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private final Logger log = Logger.getLogger(OdontologoDaoH2.class);
    private static final String DB_JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";




    public Odontologo guardar(Odontologo odontologo) {
        log.debug("Guardando un nuevo odontologo");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
            preparedStatement = connection.prepareStatement("INSERT INTO odontologos(nombre,apellido,matricula) VALUES(?,?,?)", 1);
            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setInt(3, odontologo.getMatricula());
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                odontologo.setId(keys.getInt(1));
            }

            preparedStatement.close();
        } catch (ClassNotFoundException | SQLException var5) {
            var5.printStackTrace();
        }

        return odontologo;
    }

    @Override
    public void eliminar(int id) {
        log.debug("Borrando odontologo con id : " + id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
            preparedStatement = connection.prepareStatement("DELETE FROM odontologos where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (ClassNotFoundException | SQLException var5) {
            var5.printStackTrace();
            log.error(var5);
        }
    }

    @Override
    public Odontologo buscar(int id) {
        log.debug("Buscando al odontologo con id = " + id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Odontologo odontologo = null;

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
            preparedStatement = connection.prepareStatement("SELECT id,nombre,apellido,matricula FROM odontologos where id = ?");
            preparedStatement.setInt(1, id);

            int idPaciente;
            String nombre;
            String apellido;
            int matricula;
            for(ResultSet result = preparedStatement.executeQuery(); result.next(); odontologo = new Odontologo(idPaciente, nombre, apellido, matricula)) {
                idPaciente = result.getInt("id");
                nombre = result.getString("nombre");
                apellido = result.getString("apellido");
                matricula = result.getInt("matricula");
            }

            preparedStatement.close();
        } catch (ClassNotFoundException | SQLException var10) {
            var10.printStackTrace();
            log.error(var10);
        }

        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        log.debug("Buscando todos los odontologos");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Odontologo> odontologos = new ArrayList();

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
            preparedStatement = connection.prepareStatement("SELECT *  FROM odontologos");
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                int idOdontologo = result.getInt("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                int matricula = result.getInt("matricula");
                Odontologo odontologo = new Odontologo(idOdontologo, nombre, apellido, matricula);
                odontologos.add(odontologo);
            }

            preparedStatement.close();
        } catch (ClassNotFoundException | SQLException var10) {
            var10.printStackTrace();
            log.error(var10);
        }

        return odontologos;
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
            preparedStatement = connection.prepareStatement("UPDATE odontologos SET nombre = ?, apellido = ?,matricula = ?  WHERE id = ?");
            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setInt(3, odontologo.getMatricula());
            preparedStatement.setInt(4, odontologo.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (ClassNotFoundException | SQLException var5) {
            var5.printStackTrace();
            log.error(var5);
        }

        return odontologo;
    }
}
