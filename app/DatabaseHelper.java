import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "PAQUETEEXPRESS.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear las tablas de la base de datos
        db.execSQL("CREATE TABLE Usuario (idUsuario INTEGER PRIMARY KEY, nombre TEXT NOT NULL, clave TEXT NOT NULL)");
        db.execSQL("CREATE TABLE Administrador (idAdministrador INTEGER PRIMARY KEY, nombre TEXT NOT NULL, clave TEXT NOT NULL)");
        db.execSQL("CREATE TABLE GrupoDeUsuario (idGrupoDeUsuario INTEGER PRIMARY KEY, nombre TEXT NOT NULL, funciones TEXT NOT NULL)");
        db.execSQL("CREATE TABLE Repartidor (idRepartidor INTEGER PRIMARY KEY, nombre TEXT NOT NULL)");
        db.execSQL("CREATE TABLE Vehiculo (idVehiculo INTEGER PRIMARY KEY, modelo TEXT NOT NULL, marca TEXT NOT NULL, numeroPlaca TEXT NOT NULL)");
        db.execSQL("CREATE TABLE Direccion (idDireccion INTEGER PRIMARY KEY, descripcion TEXT NOT NULL)");
        db.execSQL("CREATE TABLE Paquete (idPaquete INTEGER PRIMARY KEY, descripcion TEXT NOT NULL, tipo TEXT NOT NULL)");
        db.execSQL("CREATE TABLE ComentariosAdicionales (idComentarios INTEGER PRIMARY KEY, campo TEXT NOT NULL)");
        db.execSQL("CREATE TABLE Estado (idEstado INTEGER PRIMARY KEY, descripcion TEXT NOT NULL)");
        db.execSQL("CREATE TABLE SolicitudDeReparto (idUsuario INTEGER PRIMARY KEY, nombre TEXT NOT NULL, clave TEXT NOT NULL)");
        db.execSQL("CREATE TABLE SolicitudEnvio (idSolicitudEnvio INTEGER PRIMARY KEY, idReceptor INTEGER, modelo TEXT NOT NULL, marca TEXT NOT NULL, FOREIGN KEY (idReceptor) REFERENCES Receptor (idReceptor))");
        db.execSQL("CREATE TABLE Pedido (idPedido INTEGER PRIMARY KEY, idEstado INTEGER, idDireccion INTEGER, idPaquete INTEGER, idRepartidor INTEGER, idSolicitudEnvio INTEGER, idFecha INTEGER, FOREIGN KEY (idEstado) REFERENCES Estado (idEstado), FOREIGN KEY (idDireccion) REFERENCES Direccion (idDireccion), FOREIGN KEY (idPaquete) REFERENCES Paquete (idPaquete), FOREIGN KEY (idRepartidor) REFERENCES Repartidor (idRepartidor), FOREIGN KEY (idSolicitudEnvio) REFERENCES SolicitudEnvio (idSolicitudEnvio))");
        db.execSQL("CREATE TABLE Entrega (idEntrega INTEGER PRIMARY KEY, idPedido INTEGER, fechaSolicitud TEXT NOT NULL, horaSolicitud TEXT NOT NULL, fechaEntrega TEXT NOT NULL, horaEntrega TEXT NOT NULL, FOREIGN KEY (idPedido) REFERENCES Pedido (idPedido))");
        db.execSQL("CREATE TABLE Receptor (idReceptor INTEGER PRIMARY KEY, nombre TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // agregar la lógica para actualizar la base de datos si es necesario
    }

    // Métodos para realizar operaciones CRUD en la base de datos
    public long insertUsuario(String nombre, String clave) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("clave", clave);
        return db.insert("Usuario", null, values);
    }

    public Cursor getUsuarios() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("Usuario", new String[] {"idUsuario", "nombre", "clave"}, null, null, null, null, null);
    }

    // Agrega más métodos CRUD para tablas según necesidades
}
