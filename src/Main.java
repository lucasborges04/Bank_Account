import java.util.Scanner;

public class Main {
//    private final static BankAccount bankAccount = new BankAccount();

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o depósito inicial (R$)");
        var initialDeposit = scanner.nextDouble();
        BankAccount account = new BankAccount(initialDeposit);

        var option = -1;

        do{
            System.out.println("===MENU===");
            System.out.println("1 - Consultar Saldo");
            System.out.println("2 - Consultar Cheque Especial");
            System.out.println("3 - Depositar Dinheiro");
            System.out.println("4 - Sacar Dinheiro");
            System.out.println("5 - Pagar um Boleto");
            System.out.println("6 - Verificar se a conta está usando cheque especial");
            System.out.println("0 - Sair");
            System.out.println("Escolha uma opção");
            option = scanner.nextInt();

            switch(option){
                case 1:
                    System.out.println("Saldo: R$" + String.format("%.2f", account.getBalance()));
                    break;
                case 2:
                    System.out.println("Limite cheque especial: R$" + String.format("%.2f", account.getSpecialCheckLimit()));
                    System.out.println("Disponível do cheque especial: R$" + String.format("%.2f", account.getSpecialCheckAvailable()));
                    break;
                case 3:
                    System.out.print("Informe o valor a depositar (R$): ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Informe o valor a sacar (R$): ");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdraw(withdrawalAmount);
                    break;
                case 5:
                    System.out.print("Informe o valor do boleto (R$): ");
                    double ticketValue = scanner.nextDouble();
                    account.payBill(ticketValue);
                    break;
                case 6:
                    if(account.isUsingSpecialCheck()){
                        System.out.println("A conta está usando cheque especial. Dívida: R$" + String.format("%.2f", -account.getBalance()));
                    }
                    System.out.println("A conta não está usando cheque especial.");
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while(option != 0);

        scanner.close();
    }
}
