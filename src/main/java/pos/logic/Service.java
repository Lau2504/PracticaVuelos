package pos.logic;

import pos.data.Data;
import pos.data.XmlPersister;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private static Service theInstance;

    public static Service instance(){
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }
    private Data data;

    private Service(){
        try{
            data= XmlPersister.instance().load();
        }
        catch(Exception e){
            data =  new Data();
        }
    }

    public void stop(){
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

   public List<Ciudad> getCiudades(){
        return data.getCiudades();
   }

//================= VUELOS ============

    public void create(Vuelo e) throws Exception {
        if (e.getCiudadDestino() == null || e.getCiudadDestino().getNombre() == null) {
            throw new Exception("Ciudad de destino no puede ser null");
        }
        if (e.getCiudadOrigen() == null || e.getCiudadOrigen().getNombre() == null) {
            throw new Exception("Ciudad de origen no puede ser null");
        }
        Vuelo result = data.getVuelos().stream().filter(i -> i.getNumero().equals(e.getNumero())).findFirst().orElse(null);
        if (result == null) {
            data.getVuelos().add(e);
        } else {
            throw new Exception("Vuelo ya existe");
        }
    }


    public Vuelo read(Vuelo e) throws Exception{
        Vuelo result = data.getVuelos().stream().filter(i->i.getNumero().equals(e.getNumero())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Vuelo no existe");
    }

    public void update(Vuelo e) throws Exception{
        Vuelo result;
        try{
            result = this.read(e);
            data.getVuelos().remove(result);
            data.getVuelos().add(e);
        }catch (Exception ex) {
            throw new Exception("Vuelo no existe");
        }
    }

    public void delete(Vuelo e) throws Exception{
        data.getVuelos().remove(e);
    }

    public List<Vuelo> search(Vuelo e){
        return data.getVuelos().stream()
                .filter(i -> (i.getCiudadDestino() != null && i.getCiudadDestino().getNombre().contains(e.getCiudadDestino().getNombre()))
                        || (i.getCiudadOrigen() != null && i.getCiudadOrigen().getNombre().contains(e.getCiudadOrigen().getNombre()))).sorted(Comparator.comparing(Vuelo::getNumero))
                .collect(Collectors.toList());
    }




}
