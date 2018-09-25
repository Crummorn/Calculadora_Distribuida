import java.net.*;
import java.io.*;

public class SimpleJavaClient {
	public static void main(String[] args) {
		try {
			Socket somSubServer = new Socket("localhost", 10000);
			Socket mulDivServer = new Socket("localhost", 10001);

			InputStream iSomSub = somSubServer.getInputStream();
			OutputStream oSomSub = somSubServer.getOutputStream();

			InputStream iMulDiv = mulDivServer.getInputStream();
			OutputStream oMulDiv = mulDivServer.getOutputStream();

			String operadorString, resultadoString;

			do {
				byte[] operador = new byte[100];
				byte[] numero1 = new byte[100];
				byte[] numero2 = new byte[100];
				byte[] resultado = new byte[100];

				System.out.println("Informe uma das operações abaixo:" + "\n'som' | 'sub' | 'mul' | 'div' | 'bye'");
				System.out.println("SOM = SOMA\t\t\t" + "SUB = SUBTRAÇÃO" + "\nMUL = MULTIPLICAÇÃO\t\t"
						+ "DIV = DIVISÃO" + "\nBYE = SAI DA APLICAÇÃO");
				System.in.read(operador);

				operadorString = new String(operador).trim().toLowerCase();

				if ((operadorString.equals("som")) || (operadorString.equals("sub"))) {
					System.out.println("\nConectado ao servidor somSub. PORT:10000");

					System.out.println("\nInforme o 1° Numero: ");
					System.in.read(numero1);

					System.out.println("Informe o 2° Numero: ");
					System.in.read(numero2);

					oSomSub.write(operador);
					oSomSub.write(numero1);
					oSomSub.write(numero2);

					iSomSub.read(resultado);
					resultadoString = new String(resultado);

					System.out.println("\nResultado: " + resultadoString);
				} else if ((operadorString.equals("mul")) || (operadorString.equals("div"))) {
					System.out.println("\nConectado ao servidor mulDiv. PORT:10001");

					System.out.println("\nInforme o 1° Numero: ");
					System.in.read(numero1);

					System.out.println("Informe o 2° Numero: ");
					System.in.read(numero2);

					oMulDiv.write(operador);
					oMulDiv.write(numero1);
					oMulDiv.write(numero2);

					iMulDiv.read(resultado);
					resultadoString = new String(resultado);

					System.out.println("\nResultado: " + resultadoString);
				}
			} while (!operadorString.trim().equals("bye"));
			somSubServer.close();
			mulDivServer.close();
		} catch (Exception err) {
			System.err.println(err);
		}
	}
}
