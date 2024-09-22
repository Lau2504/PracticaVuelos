package pos.logic;

import java.util.Objects;

public class Ciudad {
    private String id;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getGmt() {
        return gmt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ciudad ciudad = (Ciudad) o;
        return Objects.equals(id, ciudad.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public void setGmt(Integer gmt) {
        this.gmt = gmt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String nombre;
    private Integer gmt;
    public Ciudad() {

    }
    public Ciudad(String id, String nombre, Integer gmt) {
        this.id = id;
        this.nombre = nombre;
        this.gmt = gmt;
    }



    @Override
    public String toString() { return nombre; }
}
