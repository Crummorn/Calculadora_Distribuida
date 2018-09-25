import java.io.*;
import java.net.*;

public class SomSubServer {
	public static void main(String[] args) {
		try {
			ServerSocket socket = new ServerSocket(10000);
			Float numero1, numero2, resultado;
			String operadorString, resultadoString;

			while (true) {
				Socket client = socket.accept();

				InputStream iCli = client.getInputStream();
				OutputStream oCli = client.getOutputStream();

				do {
					System.out.println("\nO cliente se conectou ao servidor de SomSub. PORT:10000\n");

					byte[] numero1Byte = new byte[100];
					byte[] numero2Byte = new byte[100];
					byte[] operadorByte = new byte[100];

					iCli.read(operadorByte);
					iCli.read(numero1Byte);
					iCli.read(numero2Byte);

					operadorString = new String(operadorByte).trim();

					try {
						numero1 = new Float(Float.parseFloat(new String(numero1Byte).trim()));
						numero2 = new Float(Float.parseFloat(new String(numero2Byte).trim()));

						System.out.print("Operação Solicitada: " + operadorString);
						System.out.println("\nValor 1: " + numero1);
						System.out.println("Valor 2: " + numero2);

						if (operadorString.trim().equals("som")) {
							resultado = numero1 + numero2;
							resultadoString = resultado.toString();

							System.out.println("Resultado: " + resultado);

							oCli.write(resultadoString.getBytes());
						} else if (operadorString.trim().equals("sub")) {
							resultado = numero1 - numero2;
							resultadoString = resultado.toString();

							System.out.println("Resultado: " + resultado);

							oCli.write(resultadoString.getBytes());
						}
					} catch (NumberFormatException e) {
						resultadoString = "ERROR!!! CARACTER INVALIDO!!!\n" + "Digite somente numeros.\n";
						System.out.println(e);

						oCli.write(resultadoString.getBytes());
					} catch (Exception e) {
						System.err.println(e);
					}
				} while (!operadorString.trim().equals("bye"));
				client.close();
			}
		} catch (Exception err) {
			System.err.println(err);
		}
	}
}
