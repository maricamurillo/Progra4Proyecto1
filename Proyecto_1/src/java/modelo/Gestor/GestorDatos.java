/*
    GestorDatos.java

    EIF209 - Programación 4 – Proyecto #1 
    Abril 2019

    Autores:
            - 113030275 Mariela Cambronero
            - 111320128 Rodrigo Rodriguez
*/

package modelo.Gestor;
import cr.ac.database.managers.DBManager;
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
        listaActivos = new ArrayList<>();
        
    }

    public static GestorDatos obtenerInstancia()
            throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        if (instancia == null) {
            instancia = new GestorDatos();
        }
        return instancia;
    }

    public List<String> getListaActivos() {
        return listaActivos;
    }

    public void setListaActivos(List<String> listaActivos) {
        this.listaActivos = listaActivos;
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
                for (String  x : listaActivos) {
                    if(x.equals(id)){activo= true; break;}
                }
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
                boolean activo = rs.getBoolean("activo");
                Grupo y = new Grupo(id,secuencia,nombre,cupo,activo);
                x=y;
            }
        }
        return x;
    }
    
    
    
    
    
    
    public JSONObject obterTablaGrupo() throws SQLException{
         JSONObject r = new JSONObject();
         JSONArray a = new JSONArray();
         List<Grupo> grupos = listarGrupos(0);  
         for (Grupo grupo : grupos) {
            JSONArray b = new JSONArray();
            JSONObject j = new JSONObject();
            j.put("id", grupo.getId());
            j.put("nombre", grupo.getNombre());
            j.put("cupo", grupo.getCupo());
            String x = String.format(CMD_ESTUDIANTES_EN_GRUPO,grupo.getId());
             System.err.println("mmmmm"+x);
            List<Estudiante> datos = listarEstudiantes(x);
            for (Estudiante dato : datos) {
                  JSONObject j2 = new JSONObject();
                  j2.put("nombre", dato.getNombre());
                  j2.put("apellidos", dato.getApellidos());
                  b.put(j2);
            }
            j.put("estudiantes",b);
            a.put(j);
        }
        r.put("datos", a);
        return r;
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
    
    public boolean cambiarClave(String id, String clave) {
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
    
    public boolean insertarGrupo(int secuencia, String nombre){
        try {
                DBManager db = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER, URL_Servidor);
                try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                        PreparedStatement stm = cnx.prepareStatement(CMD_INSERTAR_GRUPO)) {
                        stm.clearParameters();
                        //stm.setInt(1, secuencia);
                        stm.setString(1, nombre);
                        return stm.executeUpdate() == 1;
                }
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException | SQLException ex) {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
                return false;
        }
    }
    
    public List<Grupo> listarGrupos(int control) throws SQLException{
        List<Grupo> r = new ArrayList<>();
        String x ="";
        if(control==1){x =CMD_LISTAR_GRUPOS;}
        if(control==0){x =CMD_LISTAR_GRUPOS_ACTIVOS;}
        try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                Statement stm = cnx.createStatement(); 
                ResultSet rs = stm.executeQuery(x)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int cupo = rs.getInt("cupo");
                Grupo g = new Grupo();
                g.setId(id);
                g.setNombre(nombre);
                g.setCupo(cupo);
                r.add(g);
            }
        }
        return r;
    }
    
    public JSONObject obtenerTablaGruposDisponibles() throws SQLException{
        JSONObject r = new JSONObject();
        JSONArray a = new JSONArray();
        List<Grupo> grupos = listarGrupos(1);
        
        for (Grupo grupo : grupos) {
            JSONObject j = new JSONObject();
            j.put("id", grupo.getId());
            j.put("nombre", grupo.getNombre());
            j.put("cupo", grupo.getCupo());
            a.put(j);
        }
        r.put("datos", a);
        return r;
    }
    
    
    public List<Grupo> listarMisGrupos(String idEstudiante) throws SQLException{
        List<Grupo> r = new ArrayList<>();
        Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
        
        try (PreparedStatement stm = cnx.prepareStatement(CMD_LISTAR_GRUPOS_DE_ESTUDIANTE)) {
                stm.clearParameters();
                stm.setString(1, idEstudiante);
                ResultSet rs = stm.executeQuery();
                
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    int cupo = rs.getInt("cupo");
                    Grupo g = new Grupo();
                    g.setId(id);
                    g.setNombre(nombre);
                    g.setCupo(cupo);
                    r.add(g);
                }
        }
        
        return r;
    }
    
    public JSONObject obtenerTablaMisGrupos(String idEstudiante) throws SQLException{
        JSONObject r = new JSONObject();
        JSONArray a = new JSONArray();
        List<Grupo> grupos = listarMisGrupos(idEstudiante);
        
        for (Grupo grupo : grupos) {
            JSONObject j = new JSONObject();
            j.put("id", grupo.getId());
            j.put("nombre", grupo.getNombre());
            j.put("cupo", grupo.getCupo());
            a.put(j);
        }
        r.put("datos", a);
        return r;
    }
    
    public boolean unirseGrupo(String idEstudiante, int idGrupo) {
        try {
                DBManager db = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER, URL_Servidor);
                try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                        PreparedStatement stm = cnx.prepareStatement(CMD_UNIRSE_GRUPO)) {
                        stm.clearParameters();
                        stm.setInt(1, idGrupo);
                        stm.setString(2, idEstudiante);        
                        if (stm.executeUpdate() == 1){
                            return actualizarCupoGrupo(idGrupo, -1);
                        }
                        else{
                            return false;
                        }
                }
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException | SQLException ex) {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
                return false;
        }
    }
    
    public boolean salirseGrupo(String idEstudiante, int idGrupo) {
        try {
                DBManager db = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER, URL_Servidor);
                try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                        PreparedStatement stm = cnx.prepareStatement(CMD_SALIRSE_GRUPO)) {
                        stm.clearParameters();
                        stm.setString(1, idEstudiante);        
                        if (stm.executeUpdate() == 1){
                            if(actualizarCupoGrupo(idGrupo, 1)) {
                                return borrarGrupo(idGrupo);
                            }
                        }
                }
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException | SQLException ex) {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
                return false;
        }
        
        return false;
    }
    
    public boolean borrarGrupo(int idGrupo) {
        try {
                DBManager db = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER, URL_Servidor);
                try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                        PreparedStatement stm = cnx.prepareStatement(CMD_BORRAR_GRUPO)) {
                        stm.clearParameters(); 
                        stm.setInt(1, idGrupo);       
                        return (stm.executeUpdate() == 1);
                }
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException | SQLException ex) {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
                return false;
        }
    }
    
    public boolean actualizarCupoGrupo(int idGrupo, int cantidad) {
        try {
                DBManager db = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER, URL_Servidor);
                try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                        PreparedStatement stm = cnx.prepareStatement(CMD_ACTUALIZAR_CUPO_GRUPO)) {
                        stm.clearParameters();
                        stm.setInt(1, cantidad);   
                        stm.setInt(2, idGrupo);       
                        return (stm.executeUpdate() == 1);
                }
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException | SQLException ex) {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
                return false;
        }
    }
    
    public boolean validarGrupoEstudiante(String idEstudiante) throws SQLException{
        
        try {
                DBManager db = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER, URL_Servidor);
                try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                        PreparedStatement stm = cnx.prepareStatement(CMD_VALIDAR_GRUPO_ESTUDIANTE)) {
                        stm.clearParameters();
                        stm.setString(1, idEstudiante);
                        ResultSet rs = stm.executeQuery();
                        while (rs.next()){
                            int grupoId = rs.getInt("grupo_id");
                            return grupoId == 0;
                        }
                }
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException | SQLException ex) {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
                return false;
        }
        
        return false;
    }
    
    public int buscarIdGrupo(String nombreGrupo){
        try {
                DBManager db = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER, URL_Servidor);
                try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                        PreparedStatement stm = cnx.prepareStatement(CMD_BUSCAR_GRUPO_NOMBRE)) {
                        stm.clearParameters();
                        stm.setString(1, nombreGrupo);
                        ResultSet rs = stm.executeQuery();
                        while (rs.next()){
                            int grupoId = rs.getInt("id");
                            return grupoId;
                        }
                }
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException | SQLException ex) {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
                return -1;
        }
        
        return -1;
    }
    
    public boolean insertarGrupo(String nombre){
        try {
                DBManager db = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER, URL_Servidor);
                try (Connection cnx = db.getConnection(BASE_DATOS, LOGIN, PASSWORD);
                        PreparedStatement stm = cnx.prepareStatement(CMD_INSERTAR_GRUPO)) {
                        stm.clearParameters();
                        stm.setString(1, nombre);
                        return stm.executeUpdate() == 1;
                }
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException | SQLException ex) {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
                return false;
        }
    }
    
    private List<String> listaActivos;
    private static GestorDatos instancia = null;
    private DBManager db = null;
    private String URL_Servidor = "localhost";
    private static final String BASE_DATOS = "eif209_1901_p01";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
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
    private static final String CMD_INSERTAR_GRUPO 
            = "INSERT INTO eif209_1901_p01.grupo(nombre) "
            + "VALUES (?) ";
    private static final String CMD_LISTAR_GRUPOS 
            = "SELECT id, nombre, cupo "
            + "FROM eif209_1901_p01.grupo "
            + "WHERE activo = 1 AND cupo > 0";
     private static final String CMD_LISTAR_GRUPOS_ACTIVOS 
            = "SELECT id, nombre, cupo "
            + "FROM eif209_1901_p01.grupo "
            + "WHERE activo = 1 ";
    private static final String CMD_LISTAR_GRUPOS_DE_ESTUDIANTE 
            = "SELECT g.id, g.nombre, g.cupo "
            + "FROM eif209_1901_p01.grupo g "
            + "INNER JOIN eif209_1901_p01.estudiante e ON e.grupo_id = g.id AND e.id = ?";
    private static final String CMD_UNIRSE_GRUPO
            = "UPDATE eif209_1901_p01.estudiante "
            + "SET grupo_id = ? "
            + "WHERE id = ? ";
    private static final String CMD_SALIRSE_GRUPO
            = "UPDATE eif209_1901_p01.estudiante "
            + "SET grupo_id = null "
            + "WHERE id = ? ";
    private static final String CMD_ACTUALIZAR_CUPO_GRUPO
            = "UPDATE eif209_1901_p01.grupo "
            + "SET cupo = cupo + ? "
            + "WHERE id = ? ";
    private static final String CMD_VALIDAR_GRUPO_ESTUDIANTE
            = "SELECT grupo_id "
            + "FROM eif209_1901_p01.estudiante "
            + "WHERE id = ?";
    private static final String CMD_ESTUDIANTES_EN_GRUPO
            = "SELECT nrc,secuencia,id,apellidos,nombre,ultimo_acceso,grupo_id "
            + "FROM estudiante "
            + "WHERE grupo_id = '%s';";
    
    private static final String CMD_BORRAR_GRUPO
            = "DELETE "
            + "FROM eif209_1901_p01.grupo "
            + "WHERE id = ? AND cupo = 5";
    
    private static final String CMD_BUSCAR_GRUPO_NOMBRE 
            = "SELECT id FROM eif209_1901_p01.grupo "
            + "WHERE nombre = ? ";
}
