package dataset;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatasetMaker {

	static String pathCsv = "/home/santi/dataset.csv";
	static boolean primeraLinea = true;

	static Date currentDate = new Date();
	static long counterId = 0;
	static char letra = 'A';
	public static void main(String[] args) {
		List<String> list = new ArrayList();

		for(int j=0; j<1; j++) {	
			generaData(list);
			File permFile = new File(pathCsv);
			try {
				// create a writer for permFile
				BufferedWriter out = new BufferedWriter(new FileWriter(permFile, true));
				// create a reader for tmpFile

				for(String s: list) {
					out.write(s+"\n");

				}

				out.close();
			} catch (IOException e) {
			}
			list.clear();
			System.out.println(j);

		}
		System.out.println("END");
	}
	private static void generaData(List list) {

		for(int i=0; i<150000;i++) {
			if(primeraLinea) {
				list.add("identificador, fecha, e2Periodo1, e2Periodo2, e2Periodo3, e2Periodo4, e2Periodo5, e2Periodo6, e2Periodo7, e2Periodo8, e2Periodo9, e2Periodo10, e2Periodo11, e2Periodo12, e2Periodo13, e2Periodo14, e2Periodo15, e2Periodo16, e2Periodo17, e2Periodo18, e2Periodo19, e2Periodo20, e2Periodo21, e2Periodo22, e2Periodo23, e2Periodo24, e2Periodo25, e2Periodo26, e2Periodo27, e2Periodo28, e2Periodo29, e2Periodo30, e2Periodo31, e2Periodo32, e2Periodo33, e2Periodo34, e2Periodo35, e2Periodo36, e2Periodo37, e2Periodo38, e2Periodo39, e2Periodo40, e2Periodo41, e2Periodo42, e2Periodo43, e2Periodo44, e2Periodo45, e2Periodo46, e2Periodo47, e2Periodo48, e2Periodo49, e2Periodo50, e2Periodo51, e2Periodo52, e2Periodo53, e2Periodo54, e2Periodo55, e2Periodo56, e2Periodo57, e2Periodo58, e2Periodo59, e2Periodo60, e2Periodo61, e2Periodo62, e2Periodo63, e2Periodo64, e2Periodo65, e2Periodo66, e2Periodo67, e2Periodo68, e2Periodo69, e2Periodo70, e2Periodo71, e2Periodo72, e2Periodo73, e2Periodo74, e2Periodo75, e2Periodo76, e2Periodo77, e2Periodo78, e2Periodo79, e2Periodo80, e2Periodo81, e2Periodo82, e2Periodo83, e2Periodo84, e2Periodo85, e2Periodo86, e2Periodo87, e2Periodo88, e2Periodo89, e2Periodo90, e2Periodo91, e2Periodo92, e2Periodo93, e2Periodo94, e2Periodo95, e2Periodo96, coordX, coordY, numFamiliares, tarifa");	
				primeraLinea = false;
			}else {
				list.add(generaRandomRow());
			}
		}
	}





	private static String generaRandomRow() {
		StringBuilder string = new StringBuilder();

		string.append(calculaId()).append(", ");
		string.append(currentDate.toString()).append(", ");
		
		double randomValue = 0.0;
		for(int i=1; i<97;i++) {
			randomValue = generateRandom();
			string.append(randomValue).append(", ");
		}
		//Coordenadas
		string.append(generateRandomCoord()).append(", ");
		string.append(generateRandomCoord()).append(", ");;
		string.append(numFamiliares()).append(", ");
		string.append(tipoTarifa());

		return string.toString();
	}
	private static String tipoTarifa() {
		int num = (int) ((Math.random() / (Math.random()*Math.random())) %4);
		
		String tarifa = "";
		switch(num) {
		case 0:
			tarifa = "20A";
			break;
		case 1:
			tarifa = "20DHA";
			break;
		case 2:
			tarifa = "21A";
			break;
		case 3:
			tarifa = "21DHA";
			break;
		}
		return tarifa;
	}
	private static String numFamiliares() {
		int num = (int) ((Math.random() / (Math.random()*Math.random())) %7);
		if(num == 0)
			return 1+"";
		return num+"";

	}
	private static String calculaId() {
		String str = "";
		counterId++;
		if(counterId < 0) {
			counterId = 0;
		    letra = (char) ((int) letra +1);
		}
		
		str =""+letra+counterId;
		

		return str;



	}
	private static Object generateRandomCoord() {


		int signo = 1;
		if(Math.random() >= 0.5) {
			signo = -1;
		}else {
			signo = +1;
		}

		return ((Math.random() / (Math.random()*Math.random())) %20.000000000000000) * signo;

	}
	private static double generateRandom() {
		return (Math.random() / (Math.random()*Math.random())) %20.000000000000000;

	}

}
