package View;

public interface Actioner {

    public final static String CAPTURARDATOS = "Captura Datos";

    public final static String ESTADISTICASVENTANA = "Estadisticas";

    public String[] captureData(String data);
    public void mensaje(String mensaje);
}
