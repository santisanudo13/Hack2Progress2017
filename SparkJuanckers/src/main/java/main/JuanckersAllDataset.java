package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;


public class JuanckersAllDataset {
	static SparkSession spark;

	static int a = 0;
	public static void main(String[] args) {
		long time_start, time_end;
		time_start = System.currentTimeMillis();

		//Inicializamos sparksession
		spark = SparkSession
				.builder()
				.appName("SparkProcess")
				.master("local")
				.getOrCreate();

		//DATASET
		Dataset<Row> dataset = spark.read().json("/home/santi/dataset.csv");
		dataset.show();
		
		Dataset<Row> df = dataset.filter(row -> !row.equals(dataset.first()));
		df.show();
		
		
		// Generamos el schema definiendo las claves
		String schemaString = "identificador numFamiliares coordX coordY tarifa e2Total tarifaEstimada";

		List<StructField> fields = new ArrayList<>();
		for (String fieldName : schemaString.split(" ")) {
			StructField field = DataTypes.createStructField(fieldName, DataTypes.StringType, true);
			fields.add(field);
		}
		StructType schema = DataTypes.createStructType(fields);

		//Definimos como se el nuevo RDD, en este caso cifrando el cups
		JavaRDD<Row> rowRDD = df.javaRDD().map(new Function<Row, Row>() {
			@Override
			public Row call(Row rowIni) throws Exception {
				String e2Total = calculaTotal(rowIni);
				String tarifaEstimada = calculaTarifaEstimda(rowIni);

				return RowFactory.create(
						rowIni.getString(rowIni.fieldIndex("identificador")),
						rowIni.getString(rowIni.fieldIndex("numFamiliares")),
						rowIni.getString(rowIni.fieldIndex("coordX")),
						rowIni.getString(rowIni.fieldIndex("coordY")),
						rowIni.getString(rowIni.fieldIndex("tarifa")),
						e2Total,
						tarifaEstimada
						);
			}

			private String calculaTarifaEstimda(Row rowIni) {
				double parte1 = 0.0;
				double parte2 = 0.0;
				double parte3 = 0.0;
				double parte4 = 0.0;

				for(int i=1; i<25; i++) {
					parte1 = parte1 + Double.parseDouble((String) rowIni.getString(rowIni.fieldIndex("e2periodo"+i)));
				}
				
				for(int i=25; i<49; i++) {
					parte2 = parte2 + Double.parseDouble((String) rowIni.getString(rowIni.fieldIndex("e2periodo"+i)));
				}
				
				for(int i=49; i<73; i++) {
					parte3 = parte3 + Double.parseDouble((String) rowIni.getString(rowIni.fieldIndex("e2periodo"+i)));
				}
				
				for(int i=73; i<97; i++) {
					parte4 = parte4 + Double.parseDouble((String) rowIni.getString(rowIni.fieldIndex("e2periodo"+i)));
				}
				
				List<Double> values = new ArrayList<Double>();
				values.add(parte1);
				values.add(parte2);
				values.add(parte3);
				values.add(parte4);
				
				double max = Collections.max(values);
				
				for(int i=0; i<values.size(); i++) {
					if(values.get(i) == max) {
						String tarifaEst = "";
						switch(i) {
						case 0:
							tarifaEst = "20A";
							break;
						case 1:
							tarifaEst = "20DHA";
							break;
						case 2:
							tarifaEst = "21A";
							break;
						case 3:
							tarifaEst = "21DHA";
							break;
						}
						
						return tarifaEst;
					}
						
				}
				return null;
			}

			private String calculaTotal(Row rowIni) {
				double total = 0.0;
				for(int i=1; i<97; i++) {
					total = total + Double.parseDouble((String) rowIni.getString(rowIni.fieldIndex("e2periodo"+i)));
				}
				return total+"";
			}

		});

		Dataset<Row> output = spark.createDataFrame(rowRDD, schema);
		output.show();
		
		output.coalesce(1).write().json("/home/santi/output/ALLDATA");
		time_end = System.currentTimeMillis();
		System.out.println("Tiempo: "+ ( time_end - time_start ) +" milliseconds");
		spark.close();

	}
}

