import java.util.Arrays;
import java.util.Scanner;

//O programa deve:
// * pedir que o usuário digite a quantidade de temperaturas a serem transformadas.
// * pedir que o usuário escolha duas unidades de temperatura. A unidade de origem da temperatura e a unidade a ser transformada.
// * conseguir transformar temperatura em Celsius, Kelvin e Fahrenheit a partir de qualquer uma dessas unidades para qualquer uma dessas unidades.
// * imprimir cada temperatura passada, e a unidade escolhida, e também a temperatura resultante, transformada, com sua respectiva unidade.
// * calcular a média das temperaturas iniciais e transformadas.
// * ser capaz de se recuperar e tratar qualquer erro que venha ocorrer em sua execução.
// POSSÍVEIS PROBLEMAS: qtd negativa, escrita errada da unidade

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

        System.out.println("Você vai converter " + unityInput + " em " + unityOutput + "\n");

        // Entrada de temperaturas:
        for (int i = 0; i < numberOfTemps; i++){
            tempInputs[i] = getTemp();
        }

        System.out.print("Temperatura(s) de entrada em " + unityInput + ":");
        for (int i = 0; i < numberOfTemps; i++){
            System.out.printf(" %.2f ", tempInputs[i]);
        }

        System.out.println();

        switch (unityOutput) {
            case CELSIUS:
                // Saida de temperaturas:
                for (int i = 0; i < numberOfTemps; i++){
                    tempOutputs[i] = transformToCelsius.transform(unityInput, tempInputs[i]);
                }

                System.out.print("Temperatura(s) convertida(s): ");
                for (int i = 0; i < numberOfTemps; i++){
                    System.out.printf("%.2f ºC ", tempOutputs[i]);
                }

                break;

            case FAHRENHEIT:
                // Saida de temperaturas:
                for (int i = 0; i < numberOfTemps; i++){
                    tempOutputs[i] = transformToFahrenheit.transform(unityInput, tempInputs[i]);
                }

                System.out.print("Temperatura(s) convertida(s): ");
                for (int i = 0; i < numberOfTemps; i++){
                    System.out.printf("%.2f ºF ", tempOutputs[i]);
                }

                break;

            case KELVIN:
                // Saida de temperaturas:
                for (int i = 0; i < numberOfTemps; i++){
                    tempOutputs[i] = transformToKelvin.transform(unityInput, tempInputs[i]);
                }

                System.out.print("Temperatura(s) convertida(s): ");
                for (int i = 0; i < numberOfTemps; i++){
                    System.out.printf("%.2f K ", tempOutputs[i]);
                }

                break;

            case SAIR:
                break;

            default:
                System.out.println("Nenhuma conversão foi realizada.");
                break;
        }

        System.out.println();

        //Calculando a média das temperaturas de entrada e saída:
        inputTempAverage = setAverage(tempInputs);
        outputTempAverage = setAverage(tempOutputs);
        System.out.println("Média das temperaturas de entrada: " + inputTempAverage + " " + unityInput);
        System.out.println("Média das temperaturas de saida: " + outputTempAverage + " " + unityOutput);


    }

    private static void initialize() {
        System.out.println("*** Bem-vindo(a) ao nosso programa de conversão de temperaturas. ***");
        System.out.println("Unidades disponíveis: CELSIUS, FAHRENHEIT e KELVIN.");
        System.out.println("Passo a passo: \n1. Insira a quantidade de temperaturas que deseja converter; \n2. Digite o nome das unidades de entrada e saída da conversão; \n3. Insira o(s) valor(es) da(s) temperatura(s). ");
        System.out.println("O resultado será impresso logo abaixo, bem como as médias da(s) temperatura(s) de entrada e saída.");
        System.out.println("Para sair do programa, digite SAIR no passo 2.\n");

    }

    private static int getInt() {
        Scanner input = new Scanner(System.in);

        // Pedir que o usuário digite a quantidade de temperaturas a serem transformadas
        System.out.println("Digite a quantidade de temperaturas: ");
        int qtd = input.nextInt();

        while(qtd < 0) {
            try{
                System.out.println("Digite a quantidade de temperaturas: ");
                qtd = input.nextInt();
            } catch (NegativeArraySizeException e) {
                System.out.println("Você inseriu um valor negativo.");
            }
        }

        return qtd;
    }
    private static TempUnity getUnityTemp(String io) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite a unidade de temperatura da(s) " + io);
        String typeString = input.nextLine().toUpperCase();
        return TempUnity.valueOf(typeString);
    }

    private static double getTemp() {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite a temperatura: ");
        return input.nextDouble();
    }

    private static double setAverage(double[] array) {
        double sum = Arrays.stream(array).sum();
        return sum / array.length;
    }
}
