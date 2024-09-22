package pos.presentation.vuelos;

import pos.logic.Vuelo;
import pos.presentation.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel<Vuelo> implements javax.swing.table.TableModel {

    public TableModel(int[] cols, List<Vuelo> rows) {
        super(cols, rows);
    }

    public static final int NUMERO = 0;
    public static final int ORIGEN = 1;
    public static final int DESTINO = 2;
    public static final int SALIDA = 3;
    public static final int LLEGADA = 4;
    public static final int DURACION = 5;

    @Override
    protected Object getPropetyAt(Vuelo e, int col) {
        switch (cols[col]) {
            case NUMERO: return e.getNumero();
            case ORIGEN: return e.getCiudadOrigen().getNombre();
            case DESTINO: return e.getCiudadDestino().getNombre();
            case SALIDA: return e.getHoraSalida();
            case LLEGADA: return e.getHoraLlegada();
            case DURACION: return e.calcularDuracion();
            default: return "";
        }
    }

    @Override
    protected void initColNames() {
        colNames = new String[6];
        colNames[NUMERO] = "Número";
        colNames[ORIGEN] = "Origen";
        colNames[DESTINO] = "Destino";
        colNames[SALIDA] = "Hora Salida";
        colNames[LLEGADA] = "Hora Llegada";
        colNames[DURACION] = "Duración";
    }
}
