import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.zip.DataFormatException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.Reader;
import java.io.Writer;
import java.util.Random;

/**
 * Class to read in and convert a CSV file to XML. 
 */
public class CSV2XML {


	/**
	 * Class to represent a row in a CSV file. Includes methods to
	 * interpret and process a line read from a CSV to a list of attributes.
	 */
	protected static class CSVRow {

		// the header names for the attributes of the row
		public List<String> header;
		// the values for the attributes of the row
		public List<String> row;

		/**
		 * Helper method that processes a line from a CSV into a list of
		 * attributes.
		 * @param csvLine a line read from a CSV file
		 * @return list of attributes from that line as Strings
		 * @throws DataFormatException when line cannot be processed 
		 */
		protected List<String> handleCSVRow(String csvLine) throws DataFormatException {
			String[] splitLine = csvLine.split(",");
			for (int i = 0; i < splitLine.length; i++) {
				splitLine[i] = splitLine[i].trim();
			}
			List<String> quotesResolvedList = new ArrayList<String>(splitLine.length);
			boolean inQuotes = false;
			StringBuffer sb = null;
			for (int i = 0; i < splitLine.length; i++) {
				if (splitLine[i].startsWith("\"") &&
									!splitLine[i].endsWith("\"")) {
					inQuotes = true;
					sb = new StringBuffer();
					sb.append(splitLine[i]);
					sb.deleteCharAt(0);
				} else if (splitLine[i].endsWith("\"") &&
									!splitLine[i].startsWith("\"")) {
					if (!inQuotes) {
						throw new DataFormatException("Problem reading line: " + csvLine);
					}
					sb.append(",");
					sb.append(splitLine[i]);
					sb.deleteCharAt(sb.length() - 1);
					quotesResolvedList.add(sb.toString());
					sb = null;
					inQuotes = false;
				} else if (inQuotes) {
					sb.append(",");
					sb.append(splitLine[i]);
				} else {
					quotesResolvedList.add(splitLine[i]);
				}
			}
			// if we could never close our quotes, something is wrong
			if (inQuotes) {
				throw new DataFormatException("Problem reading line: " + csvLine);
			}
			return quotesResolvedList;
		}
		
		/**
		 * Constructor that creates a new CSVRow object from a line in a CSV file
		 * and that same CSV's header line.
		 * @param headerLine the line with the header of the CSV
		 * @param rowLine the line with the row of the csv
		 * @throws DataFormatException if the header of row is not formatted as expected
		 */
		public CSVRow(String headerLine, String rowLine) throws DataFormatException {
			this.header = handleCSVRow(headerLine);
			this.row = handleCSVRow(rowLine);

			// both lists should have some length, otherwise there is an issue with reading the file
			if (this.header.size() != this.row.size()) {
				System.out.println(headerLine + "\n" + rowLine);
				throw new DataFormatException("Problem reading the following line (different number of columns then header line): " + rowLine);
			}

		}

	}

	/**
	 * Helper method to read in a CSV file and create a CSVRow object for each line in the file.
	 * @param inputFileReader a reader for the input file
	 * @return a list of CSVRow objects for every row in the file
	 * @throws FileNotFoundException if the file to read cannot be foind
	 * @throws IOException if there is a problem opening or reading the file
	 * @throws DataFormatException of the file is not formatted as expected
	 */
	public static List<CSVRow> readCSV(Reader inputFileReader) throws FileNotFoundException, IOException, DataFormatException {
		List<CSVRow> rowList = new LinkedList<CSVRow>();
		BufferedReader bf = new BufferedReader(inputFileReader);
		String line = null;
		String headerLine = null;
		while ((line = bf.readLine()) != null) {
			String stripLine = line.strip();
			if (stripLine.equals("")) continue;
			if (headerLine == null) {
				headerLine = stripLine;
			} else {
				CSVRow newRow = new CSVRow(headerLine, stripLine);
				rowList.add(newRow);
			}
		}
		bf.close();
		return rowList;
	}

	/**
	 * Helper method to write a list of CSVRow objects to a file in XML format.
	 * @param outputFileWriter the writer for the output XML file
	 * @param csvData the list of CSVRow objects
	 * @throws IOException if there is a problem opening or reading the xml file
	 */
	public static void writeXML(Writer outputFileWriter, List<CSVRow> csvData) throws IOException {
		BufferedWriter writer = new BufferedWriter(outputFileWriter);
		writer.write("<data>\n");
		for (CSVRow originalRow : csvData) {
			writer.write("\t<dataitem>\n");
				for (int i = 0; i < originalRow.header.size(); i++) {
					String attribute = originalRow.header.get(i)
											.replaceAll("\\s+", "-")
											.replaceAll("[<>]", "");
					String value = originalRow.row.get(i);
					if (attribute.strip().equals(""))
						attribute = "noname";
					writer.write("\t\t<"+attribute+">\n");
					writer.write("\t\t\t"+value+"\n");
					writer.write("\t\t</"+attribute+">\n");
				}
			writer.write("\t</dataitem>\n");
		}
		writer.write("</data>");
		writer.close();
	}	
	
	public static String getRandomValue(final Random random,
	    final int lowerBound,
	    final int upperBound,
	    final int decimalPlaces){

	    if(lowerBound < 0 || upperBound <= lowerBound || decimalPlaces < 0){
	        throw new IllegalArgumentException("Put error message here");
	    }

	    final double dbl =
	        ((random == null ? new Random() : random).nextDouble() //
	            * (upperBound - lowerBound))
	            + lowerBound;
	    return String.format("%." + decimalPlaces + "f", dbl);

	}

	public static void main(String[] args) throws Exception {
	 
	  /*
	  
	    // first-time set-up
	  try (PrintWriter writer = new PrintWriter("test.csv")) {

	      StringBuilder sb = new StringBuilder();
	      sb.append("id");
	      sb.append(',');
	      sb.append("account number");
	      sb.append(',');
	      sb.append("balance");
	      sb.append(',');
	      sb.append("credit");
          sb.append(',');
	      sb.append('\n');
	      
	      Random rand = new Random();
	      
	      for(int i = 1; i < 222; i++) {
	        sb.append(i);
	        sb.append(',');
	        sb.append(i);
            sb.append(',');
            sb.append(getRandomValue(rand, 0, 5000, 2));
            sb.append(',');
            sb.append(getRandomValue(rand, 0, 200, 2));
            sb.append('\n');
            
	      }
	      /*
          

          /*
	      sb.append("1");
	      sb.append(',');
	      sb.append("Prashant Ghimire");
	      sb.append('\n');
	      */
	  
	      /*
	      writer.write(sb.toString());

	      System.out.println("done!");

	    } catch (FileNotFoundException e) {
	      System.out.println(e.getMessage());
	    }
	    */
	    
		// convert the csv form project 1 to XML
		List<CSVRow> csvData = readCSV(new FileReader("accounts.csv"));
		writeXML(new FileWriter("accounts.xml"), csvData);

	}

}
