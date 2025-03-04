package utils;

public class ValidadorCPF {
    public static boolean cpfEhValido(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        return cpf.length() == 11 && !cpf.matches("(\\d)\\1{10}");
    }
}
