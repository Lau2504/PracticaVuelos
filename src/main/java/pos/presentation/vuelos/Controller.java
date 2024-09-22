package pos.presentation.vuelos;


import pos.Application;
import pos.logic.*;
import pos.logic.Vuelo;
import pos.logic.Service;



import java.io.FileOutputStream;
import java.util.List;


public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;


        this.model.init(Service.instance().getCiudades());//recibe una lista?
        view.setController(this);
        view.setModel(this.model);
        this.model.setCiudades(Service.instance().getCiudades());
        for ( Ciudad cat : model.getCiudades() ) {
            System.out.println(cat.getNombre());
        }
    }


    public void search(Vuelo filter) throws  Exception {
        model.setFilter(filter);
        model.setMode(Application.MODE_CREATE);
        model.setCurrent(new Vuelo());
        model.setList(Service.instance().search(model.getFilter()));
    }

    public void save(Vuelo e) throws  Exception {
        switch (model.getMode()) {
            case Application.MODE_CREATE:
                Service.instance().create(e);
                break;
            case Application.MODE_EDIT:
                Service.instance().update(e);
                break;
        }
        model.setFilter(new Vuelo());
        search(model.getFilter());
    }


    public void edit(int row) {
        Vuelo e = model.getList().get(row);
        try {
            model.setMode(Application.MODE_EDIT);
            model.setCurrent(Service.instance().read(e));
        } catch (Exception ex) {
            // Manejo de excepción: al menos loguear la excepción
            ex.printStackTrace();
        }
    }

    public void delete() throws Exception {
        Service.instance().delete(model.getCurrent());
        search(model.getFilter());
    }

    public void clear() {
        model.setMode(Application.MODE_CREATE);
        model.setCurrent(new Vuelo());
    }



//
//    public void loadCategorias() {
//        List<Categoria> categorias = Service.instance().getCategorias();
//        model.setCategorias(categorias);
//    }

}
