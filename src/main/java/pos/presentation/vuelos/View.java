package pos.presentation.vuelos;

import pos.Application;
import pos.logic.*;
import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel panel1;
    private JPanel panel;
    private JTextField numeroTextF;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JComboBox comboBoxOrigen;
    private JComboBox comboBoxSalida;
    private JComboBox comboBoxDestino;
    private JComboBox comboBoxLlegada;
    private JTextField ciudadTextF;
    private JButton buscarButton;
    private JTable listaVuelos;
    private JLabel numeroJ;
    private JLabel origenJ;
    private JLabel salidaJ;
    private JLabel destinoJ;
    private JLabel llegadaJ;
    private JLabel ciudadJ;


    Model model;
    Controller controller;

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this); //el view se agrega como un listener del model asi que cuando algo
        //cambia en el model se le notifica al view y este muestra lo que cambio
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public JPanel getPanel() {
        return panel1;
    }

    public View(){
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validar()){
                    Vuelo vuelo = take();
                    if(vuelo != null){
                        try{
                            controller.save(vuelo);
                            JOptionPane.showMessageDialog(panel, "Datos guardados exitosamente", "",JOptionPane.INFORMATION_MESSAGE);
                            controller.clear();
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               controller.clear();
           }
        });
        buscarButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try{
                   Vuelo filter = new Vuelo();
                   Ciudad c1= new Ciudad();
                   c1.setNombre(ciudadTextF.getText());
                   filter.setCiudadDestino(c1);
                   filter.setCiudadDestino(c1);
                   controller.search(filter);
               }
               catch(Exception ex){
                   JOptionPane.showMessageDialog(panel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
               }
           }
        });
    }

    private boolean validar(){
        boolean valido = true;
        if(numeroTextF.getText().isEmpty()){
            valido = false;
            numeroTextF.setBorder(Application.BORDER_ERROR);
            numeroTextF.setToolTipText("Numero requerido");
        } else {
            numeroTextF.setBorder(null);
            numeroTextF.setToolTipText(null);
        }
        if(comboBoxDestino.getSelectedIndex()==-1){
            valido = false;
            comboBoxDestino.setBorder(Application.BORDER_ERROR);
            comboBoxDestino.setToolTipText("Hora de destino requerida");
        }
        else{
            comboBoxDestino.setBorder(null);
            comboBoxDestino.setToolTipText(null);
        }
        if(comboBoxOrigen.getSelectedIndex()==-1){
            valido = false;
            comboBoxOrigen.setBorder(Application.BORDER_ERROR);
            comboBoxOrigen.setToolTipText("Hora de origen requerida");
        }
        else {
            comboBoxOrigen.setBorder(null);
            comboBoxOrigen.setToolTipText(null);
        }
        if(comboBoxSalida.getSelectedIndex()==-1){
            valido = false;
            comboBoxSalida.setBorder(Application.BORDER_ERROR);
            comboBoxSalida.setToolTipText("Ciudad de salida requerida");
        }
        else{
            comboBoxSalida.setBorder(null);
            comboBoxSalida.setToolTipText(null);
        }
        if(comboBoxDestino.getSelectedIndex()==-1){
            valido = false;
            comboBoxDestino.setBorder(Application.BORDER_ERROR);
            comboBoxDestino.setToolTipText("Ciudad de destino requerida");
        }
        else{
            comboBoxDestino.setBorder(null);
            comboBoxDestino.setToolTipText(null);
        }
        return valido;
    }
    public Vuelo take(){
        try{
            Vuelo vuelo = new Vuelo();
            vuelo.setNumero(numeroTextF.getText());
            vuelo.setCiudadDestino((Ciudad) comboBoxDestino.getSelectedItem());
            vuelo.setCiudadOrigen( (Ciudad) comboBoxOrigen.getSelectedItem());
            vuelo.setHoraLlegada(Integer.parseInt((String) comboBoxLlegada.getSelectedItem()));
            vuelo.setHoraSalida(Integer.parseInt((String) comboBoxSalida.getSelectedItem()));
            return vuelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, "Verifique el tipo de los valores ingresados", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()){
            case Model.CURRENT :
                numeroTextF.setText(""+model.getCurrent().getNumero());
                comboBoxDestino.setSelectedItem(model.getCurrent().getCiudadDestino());
                comboBoxOrigen.setSelectedItem(model.getCurrent().getCiudadOrigen());
                comboBoxLlegada.setSelectedItem(model.getCurrent().getHoraLlegada());
                comboBoxSalida.setSelectedItem(model.getCurrent().getHoraSalida());

                if(model.getMode()== Application.MODE_EDIT){
                    numeroTextF.setEnabled(false);
                }
                else{
                    numeroTextF.setEnabled(true);
                }

                numeroJ.setBorder(null);
                numeroJ.setToolTipText(null);
                destinoJ.setBorder(null);
                destinoJ.setToolTipText(null);
                origenJ.setBorder(null);
                origenJ.setToolTipText(null);
                salidaJ.setBorder(null);
                salidaJ.setToolTipText(null);
                break;
            case Model.CIUDADES:
                comboBoxOrigen.setModel(new DefaultComboBoxModel(model.getCiudades().toArray()));
                comboBoxDestino.setModel(new DefaultComboBoxModel(model.getCiudades().toArray()));
                break;

            case Model.FILTER:
                //ciudadTextF.setText(model.getFilter().getCiudadDestino().getNombre());
                break;
            case Model.LIST:
                int[] cols = {TableModel.NUMERO, TableModel.ORIGEN, TableModel.DESTINO, TableModel.SALIDA, TableModel.LLEGADA, TableModel.DURACION};
                listaVuelos.setModel(new TableModel(cols, model.getList()));
                listaVuelos.setRowHeight(30);
                TableColumnModel columnModel = listaVuelos.getColumnModel();
                columnModel.getColumn(1).setPreferredWidth(150);
                columnModel.getColumn(3).setPreferredWidth(150);
                break;
        }
    }

}
