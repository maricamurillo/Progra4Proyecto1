/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Gestor;
import cr.ac.database.managers.DBManager;
import cr.ac.database.managers.MySQLDBManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Estudiante;
import modelo.entidades.Grupo;
import org.json.JSONArray;
import org.json.JSONObject;
public class GestorDatos {
    
     private GestorDatos()
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        db = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER);
    }

    public static GestorDatos obtenerInstancia()
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        if (instancia == null) {
            instancia = new GestorDatos();
        }
        return instancia;
    }
     public  boolean verificarUsuario(String usuario, String clave) {
        boolean encontrado = false;

        try {  
            Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);

            try (PreparedStatement stm = cnx.prepareStatement(CMD_VERIFICAR)) {
                stm.clearParameters();
                stm.setString(1, usuario);
                stm.setString(2, clave);
                ResultSet rs = stm.executeQuery();
                encontrado = rs.next();
        
            }

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            if (db != null) {
                db.closeConnection();
            }
        }
        return encontrado;
    }
    public void setUrlServidor(String nuevoURL) { // urlServidor
        URL_Servidor = nuevoURL;
    }
    public List<Estudiante> listarEstudiantes(String estudiante) throws SQLException{
        List<Estudiante> r = new ArrayList<>();
         try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                Statement stm = cnx.createStatement(); ResultSet rs = stm.executeQuery(estudiante)) {
            while (rs.next()) {
                String id = rs.getString("id");
                int nrc = rs.getInt("nrc");
                String apellidos = rs.getString("apellidos");
                String nombre = rs.getString("nombre");
                Date ultimoAcceso = rs.getDate("ultimo_acceso");
                int grupo = rs.getInt("grupo_id");
                int secuencia = rs.getInt("secuencia");
                boolean activo = false;
                r.add(new Estudiante(id,nrc,apellidos,nombre,ultimoAcceso,grupo,secuencia,activo));
            }
        }
        return r;
    }
    public Grupo getGrupo(int ids) throws SQLException{
        String grupo = String.format(CMD_Grupo_Estudiante,ids);
        
        System.out.println("modelo.Gestor.GestorDatos.getGrupo()"+grupo);
        Grupo x = new Grupo();
        try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                Statement stm = cnx.createStatement(); ResultSet rs = stm.executeQuery(grupo)) {
            while (rs.next()) {
                int id = rs.getInt("id"); 
                String nombre = rs.getString("nombre");
                int secuencia = rs.getInt("secuencia");
                int cupo = rs.getInt("cupo");
                String activo = rs.getString("activo");
                Grupo y = new Grupo(id,secuencia,nombre,cupo,activo);
                x=y;
            }
        }
        return x;
    }
    public JSONObject obtenerTablaEstudiante(int orden) throws SQLException{
        JSONObject r = new JSONObject();
        JSONArray a = new JSONArray();
         List<Estudiante> datos = listarEstudiantes(CMD_Estudiante_NRS);
        switch(orden){
            case 1: datos = listarEstudiantes(CMD_Estudiante_NRS);
            break;
            case 2: datos = listarEstudiantes(CMD_Estudiante_ID);
            break;
            case 3: datos = listarEstudiantes(CMD_Estudiante_APELLIDOS);
            break;
            case 4: datos = listarEstudiantes(CMD_Estudiante_NOMBRE);
            break;
            case 5: datos = listarEstudiantes(CMD_Estudiante_GRUPOS);
            break;    
        }
        for (Estudiante dato : datos) {
            JSONObject j = new JSONObject();
            j.put("id", dato.getId());
            j.put("nrc", dato.getNrc());
            j.put("apellidos", dato.getApellidos());
            j.put("nombre", dato.getNombre());
            j.put("ultimoAcceso", dato.getUltimoAcceso());
            if(dato.getGrupo() !=0){
                Grupo x = getGrupo(dato.getGrupo());
                 j.put("grupo", x.getNombre());
                 j.put("secuencia", x.getSecuencia());
            }
            else {
                j.put("grupo", "n/p");
                j.put("secuencia","n/p");
            }
            j.put("activo", dato.isActivo());
            a.put(j);
        }
        r.put("datos", a);
        return r;
    }
    
    public boolean cambiarClave(String id, String clave){
        try {
                DBManager db = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER, URL_Servidor);
                try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                        PreparedStatement stm = cnx.prepareStatement(CMD_CAMBIAR_CLAVE)) {
                        stm.clearParameters();
                        stm.setString(1, clave);
                        stm.setString(2, id);

                    return (stm.executeUpdate() == 1);
                }
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException | SQLException ex) {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
                return false;
        }
    }
    
    private static GestorDatos instancia = null;
    private DBManager db = null;
    private String URL_Servidor = "localhost";
    private static final String BASE_DATOS = "eif209_1901_p01";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "admin1234";
    private static final String CMD_VERIFICAR ="SELECT id\n" +
        "FROM estudiante\n" +
        "WHERE id = ? AND clave= ?;";
    private static final String CMD_Estudiante_NRS ="SELECT * FROM general_estudiante;";
    private static final String CMD_Estudiante_ID ="SELECT * FROM id_estudiante;";
    private static final String CMD_Estudiante_APELLIDOS ="SELECT * FROM apellidos_estudiante;";
    private static final String CMD_Estudiante_GRUPOS ="SELECT * FROM grupo_id_estudiante;";
    private static final String CMD_Estudiante_NOMBRE ="SELECT * FROM nombre_estudiante;";
    private static final String CMD_Grupo_Estudiante = "SELECT nombre, secuencia,id,cupo,activo \n"+
        "FROM grupo \n"+
        "WHERE id = '%s';";
    private static final String CMD_CAMBIAR_CLAVE
            = "UPDATE eif209_1901_p01.estudiante "
            + "SET clave = ? "
            + "WHERE id = ? ";
}
