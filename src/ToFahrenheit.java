public class ToFahrenheit {
    public double transform(TempUnity type, double temp) {
        if (type == TempUnity.CELSIUS) {
            return (temp * 9.0/5) + 32;
        } else if (type == TempUnity.KELVIN) {
            return 1.8*(temp - 273.15) + 32;
        } else {
            return temp;
        }
    }
}
