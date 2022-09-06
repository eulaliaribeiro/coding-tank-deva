public class ToCelsius {
    public double transform(TempUnity type, double temp) {
        if(type == TempUnity.FAHRENHEIT) {
            return (temp - 32) / 1.8;
        } else if (type == TempUnity.KELVIN) {
            return temp - 273.15;
        } else {
            return temp;
        }
    }
}
