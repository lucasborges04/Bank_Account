public class BankAccount {
    private double balance;
    private final double specialCheckLimit;

    //  Cria a conta e define o cheque especial
    public BankAccount(double initialDeposit) {
        this.balance = initialDeposit;
        if(initialDeposit <= 500.0){
            this.specialCheckLimit = 50.0;
        } else{
            this.specialCheckLimit = initialDeposit * 0.5;
        }
    }

    //  Retorna o saldo atual da conta
    public double getBalance(){
        return balance;
    }

    //  retorna o limite do cheque especial
    public double getSpecialCheckLimit(){
        return specialCheckLimit;
    }

    //  Retorna quanto do cheque especial está disponível
    public double getSpecialCheckAvailable(){
        double used = Math.max(0.0, -balance);
        return specialCheckLimit - used;
    }

    //  Verifica se a conta está usando o cheque especial
    public boolean isUsingSpecialCheck(){
        return balance < 0.0;
    }

    public void deposit(double value){
        if(value <= 0){
            System.out.println("Valor de depósito inválido.");
            return;
        }
        balance += value;
        System.out.println("Depósito de R$" + String.format("%.2f", value) + " realizado com sucesso.");
    }

    public void withdraw(double value){
        if(value <= 0){
            System.out.println("Valor de saque inválido.");
            return;
        }

        double totalAvailable = balance + specialCheckLimit;
        if(value > totalAvailable){
            System.out.println("Saldo insuficiente, mesmo com cheque especial.");
            return;
        }

        double balanceBefore = balance;
        balance -= value;

//        double specialCheckInstallment = Math.max(0, value - Math.max(0, balanceBefore));

        if(balance < 0.0){
            double used = Math.max(0.0, value - Math.max(0.0, balanceBefore));

            if(used > 0.0) {
                double tax = used * 0.20;
                balance -= tax;
                System.out.println("Você usou R$" + String.format("%.2f", used) + " do cheque especial.");
                System.out.println("Foi cobrado uma taxa de 20% sobre o valor usado: R$" + String.format("%.2f", tax));
            }
        }

        System.out.println("Saque de R$" + String.format("%.2f", value) + " realizado com sucesso.");

    }

    public void payBill(double value){
        if(value <= 0){
            System.out.println("Valor de boleto inválido.");
            return;
        }
        System.out.println("Pagamento de boleto iniciado...");
        withdraw(value);
    }

}
