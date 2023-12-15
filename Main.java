import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static final String NOME_ARQUIVO = "funcionarios.txt";

    public static void main(String[] args) {
        List<Funcionario> funcionarios = lerArquivo();
        exibirMenu(funcionarios);
    }

    private static void exibirMenu(List<Funcionario> funcionarios) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Buscar funcionário por nome");
            System.out.println("2. Buscar funcionário por matrícula");
            System.out.println("3. Relatório dos funcionários");
            System.out.println("4. Sair");

            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    buscarPorNome(funcionarios, scanner);
                    break;
                case 2:
                    buscarPorMatricula(funcionarios, scanner);
                    break;
                case 3:
                    relatorio(funcionarios);
                break;
                case 4:
                    System.out.println("Programa encerrado.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static List<Funcionario> lerArquivo() {
        List<Funcionario> funcionarios = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(NOME_ARQUIVO))) {
            while (scanner.hasNextLine()) {
                String[] dados = scanner.nextLine().split(",");

                String nome = dados[2].replaceAll("-", " ");
                double salario = Double.parseDouble(dados[3]);

                funcionarios.add(new Funcionario(dados[0], dados[1], nome, salario));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Será criado um novo arquivo ao salvar.");
        }

        return funcionarios;
    }

    private static void buscarPorNome(List<Funcionario> funcionarios, Scanner scanner) {
        System.out.print("Digite o nome do funcionário: ");
        String nomeBusca = scanner.nextLine().trim();

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.nome.equalsIgnoreCase(nomeBusca)) {
                System.out.println("Funcionário encontrado: " + funcionario.matricula + ", " + funcionario.cargo + ", " + funcionario.nome + ", " + funcionario.salario);
                return;
            }
        }

        System.out.println("Funcionário com o nome '" + nomeBusca + "' não encontrado.");
    }

    private static void buscarPorMatricula(List<Funcionario> funcionarios, Scanner scanner) {
        System.out.print("Digite a matrícula do funcionário: ");
        String matriculaBusca = scanner.nextLine().trim();

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.matricula.equals(matriculaBusca)) {
                System.out.println("Funcionário encontrado: " + funcionario.matricula + ", " + funcionario.cargo + ", " + funcionario.nome + ", " + funcionario.salario);
                return;
            }
        }

        System.out.println("Funcionário com a matrícula '" + matriculaBusca + "' não encontrado.");
    }
  

    private static void relatorio(List<Funcionario> funcionarios) {
        System.out.println("Relatório dos funcionários:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.matricula + ", " + funcionario.cargo + ", " + funcionario.nome + ", " + funcionario.salario);
        }
    }



  private static void ordenarPorMatricula(List<Funcionario> funcionarios) {
      int n = funcionarios.size();

      int h = 1;
      while (h < n / 3) {
          h = 3 * h + 1;
      }

      while (h >= 1) {
          for (int i = h; i < n; i++) {
              for (int j = i; j >= h && compararPorMatricula(funcionarios.get(j - h), funcionarios.get(j)) > 0; j -= h) {
                  trocar(funcionarios, j, j - h);
              }
          }
          h /= 3;
      }
  }

  private static int compararPorMatricula(Funcionario a, Funcionario b) {
    
      return a.matricula.compareTo(b.matricula);
  }

  private static void trocar(List<Funcionario> funcionarios, int i, int j) {
      Funcionario temp = funcionarios.get(i);
      funcionarios.set(i, funcionarios.get(j));
      funcionarios.set(j, temp);
  }

    


}
