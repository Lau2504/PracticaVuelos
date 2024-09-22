package pos.presentation.vuelos;

import pos.Application;
import pos.logic.*;
import pos.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    private Vuelo filter;
    private List<Vuelo> list;
    private Vuelo current;
    private List<Ciudad> ciudades;
    private int mode;

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LIST);
        firePropertyChange(CURRENT);
        firePropertyChange(FILTER);
        firePropertyChange(CIUDADES);
    }

    public Model() {
    }

    public void init(List<Ciudad> list) {
        this.list = new ArrayList<>();
        this.current = new Vuelo();
        this.filter = new Vuelo();
        this.ciudades = list;
        this.mode = Application.MODE_CREATE;
    }

    public List<Vuelo> getList() {
        return list;
    }

    public void setList(List<Vuelo> list) {
        this.list = list;
        firePropertyChange(LIST);
    }

    public Vuelo getCurrent() {
        return current;
    }

    public void setCurrent(Vuelo current) {
        this.current = current;
        firePropertyChange(CURRENT);
    }

    public Vuelo getFilter() {
        return filter;
    }

    public void setFilter(Vuelo filter) {
        this.filter = filter;
        firePropertyChange(FILTER);
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
        firePropertyChange(CIUDADES);
    }

    public static final String LIST = "list";
    public static final String CURRENT = "current";
    public static final String FILTER = "filter";
    public static final String CIUDADES = "ciudades";
}
