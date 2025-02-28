abstract class Veiculo {
    protected String marca;
    protected String modelo;
    protected int ano;
    protected int capacidadePassageiros;
    protected String combustivel;

    public Veiculo(String marca, String modelo, int ano, int capacidadePassageiros, String combustivel) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.capacidadePassageiros = capacidadePassageiros;
        this.combustivel = combustivel;
    }

    public abstract double calcularAutonomia();

    public void exibirDetalhes() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Ano: " + ano);
        System.out.println("Capacidade de Passageiros: " + capacidadePassageiros);
        System.out.println("Combustível: " + combustivel);
    }
}

class Carro extends Veiculo {
    private final String tipoCarro;
    private static final double TANQUE_CARRO = 50.0;
    private static final double CONSUMO_CARRO = 12.0;

    public Carro(String marca, String modelo, int ano, int capacidadePassageiros, String combustivel, String tipoCarro) {
        super(marca, modelo, ano, capacidadePassageiros, combustivel);
        this.tipoCarro = tipoCarro;
    }

    @Override
    public double calcularAutonomia() {
        return TANQUE_CARRO * CONSUMO_CARRO;
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Tipo de Carro: " + tipoCarro);
        System.out.println("Autonomia: " + calcularAutonomia() + " km");
    }
}

class Caminhao extends Veiculo {
    private final double capacidadeCarga;
    private static final double TANQUE_CAMINHAO = 300.0;
    private static final double CONSUMO_CAMINHAO = 6.0;

    public Caminhao(String marca, String modelo, int ano, int capacidadePassageiros, String combustivel, double capacidadeCarga) {
        super(marca, modelo, ano, capacidadePassageiros, combustivel);
        this.capacidadeCarga = capacidadeCarga;
    }

    @Override
    public double calcularAutonomia() {
        double reducao = Math.min(capacidadeCarga, 25.0) * 0.01;
        double consumoComCarga = CONSUMO_CAMINHAO * (1 - reducao);
        return TANQUE_CAMINHAO * consumoComCarga;
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Capacidade de Carga: " + capacidadeCarga + " toneladas");
        System.out.println("Autonomia: " + calcularAutonomia() + " km");
    }
}

class Onibus extends Veiculo {
    private final int quantidadeEixos;
    private static final double TANQUE_ONIBUS = 200.0;
    private static final double CONSUMO_ONIBUS = 5.0;

    public Onibus(String marca, String modelo, int ano, int capacidadePassageiros, String combustivel, int quantidadeEixos) {
        super(marca, modelo, ano, capacidadePassageiros, combustivel);
        if (quantidadeEixos < 6 || quantidadeEixos > 8) {
            throw new IllegalArgumentException("O número de eixos deve ser entre 6 e 8.");
        }
        this.quantidadeEixos = quantidadeEixos;
    }

    @Override
    public double calcularAutonomia() {
        return TANQUE_ONIBUS * CONSUMO_ONIBUS;
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Quantidade de Eixos: " + quantidadeEixos);
        System.out.println("Autonomia: " + calcularAutonomia() + " km");
    }
}

class CarroEletrico extends Carro {
    private final double bateriaKWh;

    public CarroEletrico(String marca, String modelo, int ano, int capacidadePassageiros, String tipoCarro, double bateriaKWh) {
        super(marca, modelo, ano, capacidadePassageiros, "Elétrico", tipoCarro);
        this.bateriaKWh = bateriaKWh;
    }

    @Override
    public double calcularAutonomia() {
        return bateriaKWh * 5.0;
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Capacidade da Bateria: " + bateriaKWh + " kWh");
        System.out.println("Autonomia: " + calcularAutonomia() + " km");
    }
}

class CaminhaoRefrigerado extends Caminhao {
    private final double temperaturaMinima;
    private static final double REDUCAO_REFRIGERACAO = 0.10;

    public CaminhaoRefrigerado(String marca, String modelo, int ano, int capacidadePassageiros, String combustivel, double capacidadeCarga, double temperaturaMinima) {
        super(marca, modelo, ano, capacidadePassageiros, combustivel, capacidadeCarga);
        this.temperaturaMinima = temperaturaMinima;
    }

    @Override
    public double calcularAutonomia() {
        return super.calcularAutonomia() * (1 - REDUCAO_REFRIGERACAO);
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Temperatura Mínima: " + temperaturaMinima + "°C");
        System.out.println("Autonomia com Refrigeração: " + calcularAutonomia() + " km");
    }
}

public class Main {
    public static void main(String[] args) {
        Carro carro = new Carro("Toyota", "Corolla", 2020, 5, "Gasolina", "Sedan");
        Caminhao caminhao = new Caminhao("Mercedes", "Actros", 2021, 2, "Diesel", 10.0);
        Onibus onibus = new Onibus("Volvo", "B12M", 2019, 45, "Diesel", 7);
        CarroEletrico carroEletrico = new CarroEletrico("Tesla", "Model 3", 2022, 5, "Sedan", 75.0);
        CaminhaoRefrigerado caminhaoRefrigerado = new CaminhaoRefrigerado("Scania", "R 450", 2020, 2, "Diesel", 12.0, -20.0);

        carro.exibirDetalhes();
        System.out.println();
        caminhao.exibirDetalhes();
        System.out.println();
        onibus.exibirDetalhes();
        System.out.println();
        carroEletrico.exibirDetalhes();
        System.out.println();
        caminhaoRefrigerado.exibirDetalhes();
    }
}
