
package controlador;

import java.sql.ResultSet;

public class cUsuario {

    private String _nombre = "";
    private String _psw = "";
    private int _idUsr = 0;
    private String _error = "";
    private boolean _valido = false;      
    

    public cUsuario() {
        
    }
    
    
    
    public cUsuario(int idusr) {
        BD.cDatos bd = new BD.cDatos();
        try {
            bd.conectar();
            ResultSet rsVal = bd.consulta("call spDatosUsuario(" + idusr + ");");            

            while (rsVal.next()) {
                int idper = Integer.parseInt(rsVal.getString("idUsuario"));
                if (idper > 0) {                    
                    this._idUsr = idper;
                    this._valido = true;
                    this._error = rsVal.getString("msj");
                    this._nombre = rsVal.getString("NombreC");
                    this._psw = rsVal.getString("contra");
                }
            }
            bd.cierraConexion();

        } catch (Exception xxD) {
            this._idUsr = 0;
            this._valido = false;
            this._error = xxD.getMessage();
        }
    }
    //Constructor que recibe como parametros el nombre de usuario y la contraseña
    public cUsuario(String nom, String pass) {
        //la conexión a la base de datos se establece
        BD.cDatos bd = new BD.cDatos();
        try {
            bd.conectar();
            //Se hace la consulta para invocar al usuario segun el nombre y la contraseña dados
            ResultSet rsVal = bd.consulta("call spValidaUsuario('" + nom + "', '" + pass + "');");            
            if (rsVal.next()) {
                int idper = Integer.parseInt(rsVal.getString("idPer"));
                if (idper > 0) {
                    this._idUsr = idper;
                    this._valido = true;
                    this._error = rsVal.getString("msj");
                    this._nombre = rsVal.getString("NombreC");                 
                }
            }
            bd.cierraConexion();
        } catch (Exception xxD) {
            this._idUsr = 0;
            this._valido = false;
            this._error = xxD.getMessage();
        }
    }

    public boolean VALIDO() {
        this._valido = true;
        return _valido;
    }

    public String ERROR() {
        return this._error;
    }

    public String NOMBRE() {
        return this._nombre;
    }
    
    //Se valida si se encontro un usuario segun los parametros dados en el constructor
    public boolean VALIDAUSR() {
        if (this._idUsr == 1) {
            this._valido = true;
            this._error = "Bien hecho";
        } else {
            this._error = "Mal";
        }
        return this._valido;
    }

    public String PSW() {
        return this._psw;
    }

}
