package pos.logic;

import java.util.Objects;

public class Vuelo {
    private Double numero; //si falla esto en el archivo cambiarlo a String
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

    public Vuelo() {this(0.0, null, null, 0, 0);}

    public Vuelo(Double numero, Ciudad ciudadOrigen, Ciudad ciudadDestino, Integer horaSalida, Integer horaLlegada) {
        this.numero = numero;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
    }

    public Double getNumero() {
        return numero;
    }

    public void setNumero(Double numero) {
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
    public Integer calcularDuracion(Integer horaLlegada, Integer horaSalida, Integer gmtCiudadOrigen, Integer gmtCiudadDestino){
        Integer horaS = convertirHora(horaSalida, gmtCiudadOrigen);
        Integer horaL = convertirHora(horaLlegada, gmtCiudadDestino);

        if(horaLlegada>horaSalida){
            horaLlegada +=24;
        }

        return horaL - horaSalida;
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
