    package br.uam.criptografialocalrsa;

    import java.io.*;
    import java.math.BigInteger;
    import java.net.Socket;

    public class Client {
        public static void main(String[] args) {
            try (Socket socket = new Socket("localhost", 1234)) {
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                DataInputStream input = new DataInputStream(socket.getInputStream());

                String mensagem = "Voce e o campeao!";
                System.out.println("Enviando mensagem ao servidor: " + mensagem);
                output.writeUTF(mensagem);

                String mensagemCifrada = input.readUTF();
                System.out.println("Mensagem cifrada recebida do servidor: " + mensagemCifrada);

                // Decifragem da mensagem recebida
                RSA rsa = new RSA(new BigInteger("17"), new BigInteger("23")); // Substitua pelos seus valores
                String mensagemDecifrada = rsa.decrypt(mensagemCifrada);
                System.out.println("Mensagem decifrada no cliente: " + mensagemDecifrada);

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
