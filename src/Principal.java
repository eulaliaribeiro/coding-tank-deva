import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

// O programa deve:
// * pedir que o usuário digite a quantidade de temperaturas a serem transformadas.
// * pedir que o usuário escolha duas unidades de temperatura. A unidade de origem da temperatura e a unidade a ser transformada.
// * conseguir transformar temperatura em Celsius, Kelvin e Fahrenheit a partir de qualquer uma dessas unidades para qualquer uma dessas unidades.
// * imprimir cada temperatura passada, e a unidade escolhida, e também a temperatura resultante, transformada, com sua respectiva unidade.
// * calcular a média das temperaturas iniciais e transformadas.
// * ser capaz de se recuperar e tratar qualquer erro que venha ocorrer em sua execução.

public class Principal {
    public static void main(String[] args) {
        int numberOfTemps;
        double inputTempAverage, outputTempAverage;

        ToCelsius transformToCelsius = new ToCelsius();
        ToFahrenheit transformToFahrenheit = new ToFahrenheit();
        ToKelvin transformToKelvin = new ToKelvin();

        initialize();

        numberOfTemps = getInt();

        double[] tempInputs = new double[numberOfTemps];
        double[] tempOutputs = new double[numberOfTemps];

        TempUnity unityInput = getUnityTemp("entrada(s)");
        TempUnity unityOutput = getUnityTemp("saída(s)");

        System.out.println("\nVocê vai converter " + unityInput + " em " + unityOutput + "\n");

        // Entrada de temperaturas:
        for (int i = 0; i < numberOfTemps; i++){
            tempInputs[i] = getTemp();
        }

        System.out.println();

        switch (unityOutput) {
            case CELSIUS:
                // Saída de temperaturas:
                for (int i = 0; i < numberOfTemps; i++){
                    tempOutputs[i] = transformToCelsius.transform(unityInput, tempInputs[i]);
                    System.out.printf("%.2f grau(s) " + unityInput + " = " + "%.2f grau(s) CELSIUS\n", tempInputs[i], tempOutputs[i]);
                }

                break;

            case FAHRENHEIT:
                // Saída de temperaturas:
                for (int i = 0; i < numberOfTemps; i++){
                    tempOutputs[i] = transformToFahrenheit.transform(unityInput, tempInputs[i]);
                    System.out.printf("%.2f grau(s) " + unityInput + " = " + "%.2f grau(s) FAHRENHEIT\n", tempInputs[i], tempOutputs[i]);
                }

                break;

            case KELVIN:
                // Saída de temperaturas:
                for (int i = 0; i < numberOfTemps; i++){
                    tempOutputs[i] = transformToKelvin.transform(unityInput, tempInputs[i]);
                    System.out.printf("%.2f grau(s) " + unityInput + " = " + "%.2f grau(s) KELVIN\n", tempInputs[i], tempOutputs[i]);
                }

                break;

            default:
                System.out.println("Nenhuma conversão foi realizada.");
                break;
        }

        System.out.println();

        //Calculando a média das temperaturas de entrada e saída:
        inputTempAverage = setAverage(tempInputs);
        outputTempAverage = setAverage(tempOutputs);
        System.out.printf("Média das temperaturas de entrada: %.2f \n", inputTempAverage);
        System.out.printf("Média das temperaturas de saída: %.2f", outputTempAverage);

    }

    private static void initialize() {
        System.out.println("*** Bem-vindo(a) ao programa de conversão de temperaturas. ***");
        System.out.println("Unidades disponíveis: CELSIUS, FAHRENHEIT e KELVIN.");
        System.out.println("Passo a passo: \n1. Insira a quantidade de temperaturas que deseja converter; \n2. Digite o nome das unidades de entrada e saída da conversão; \n3. Insira o(s) valor(es) da(s) temperatura(s). ");
        System.out.println("O resultado será impresso logo abaixo, bem como as médias da(s) temperatura(s) de entrada e saída.");
    }

    private static int getInt() {
        Scanner input = new Scanner(System.in);

            try {
                System.out.println("Digite a quantidade de temperaturas que deseja converter: ");
                int qtd = input.nextInt();
                if (qtd < 1) {
                    System.err.println("Insira um número maior que 0.");
                    return getInt();
                }
                return qtd;
            } catch (InputMismatchException e) {
                System.err.println("Insira um número válido.");
                return getInt();
            }

    }
    private static TempUnity getUnityTemp(String io) {
        Scanner input = new Scanner(System.in);
        String typeString = " ";

        while (!typeString.equals("CELSIUS") && !typeString.equals("FAHRENHEIT") && !typeString.equals("KELVIN")) {
            try{
                System.out.println("Digite a unidade de temperatura da(s) " + io);
                typeString = input.nextLine().toUpperCase();
                return TempUnity.valueOf(typeString);
            } catch (IllegalArgumentException | InputMismatchException e){
                System.err.println("Digite a unidade corretamente.");
            }
        }

        return TempUnity.valueOf(typeString);
    }

    private static double getTemp() {
        Scanner input = new Scanner(System.in);

        try {
            System.out.println("Digite um valor de temperatura: ");
            return input.nextDouble();

        } catch (InputMismatchException e) {
            System.err.println("Insira um número válido.");
            return getTemp();

        }
    }

    private static double setAverage(double[] array) {
        double sum = Arrays.stream(array).sum();
        return sum / array.length;
    }
}
