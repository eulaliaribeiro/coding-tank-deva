public class ToKelvin {
    public double transform(TempUnity type, double temp) {
        if(type == TempUnity.CELSIUS) {
            return temp + 273.15;
        } else if (type == TempUnity.FAHRENHEIT) {
            return (temp - 32)*5.0/9 + 273.15;
        } else {
            return temp;
        }
    }
}
