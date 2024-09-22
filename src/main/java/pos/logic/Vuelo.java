package pos.logic;

import java.util.Objects;

public class Vuelo {
    private String numero; //si falla esto en el archivo cambiarlo a String
    private Ciudad ciudadOrigen;
    private Ciudad ciudadDestino;
    private Integer horaSalida;
    private Integer horaLlegada;

    public Ciudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(Ciudad ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public Vuelo() {this("", new Ciudad(), new Ciudad(), 0, 0);}

    public Vuelo(String numero, Ciudad ciudadOrigen, Ciudad ciudadDestino, Integer horaSalida, Integer horaLlegada) {
        this.numero = numero;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }


    public Ciudad getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(Ciudad ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public Integer getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Integer horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Integer getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Integer horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public Integer convertirHora(Integer hora, Integer gmt){
        return hora- gmt;
    }
    public Integer calcularDuracion(){
        Integer horaS = horaSalida-ciudadOrigen.getGmt();
        Integer horaL = horaLlegada-ciudadDestino.getGmt();

        if(horaL<horaS){
            horaL +=24;
        }

        return horaL - horaS;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vuelo vuelo = (Vuelo) o;
        return Objects.equals(numero, vuelo.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numero);
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "numero=" + numero +
                '}';
    }
}
