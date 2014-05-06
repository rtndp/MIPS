package parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import registers.RegisterManager;

public class RegTxtParser {

	public static void parse(String fileName) throws Exception {
		BufferedReader bfread = null;
		try {

			bfread = new BufferedReader(new FileReader(new File(fileName)));

			String line = null;
			int count = 0;
			while ((line = bfread.readLine()) != null) {
				line = line.trim();
				if (line.length() == 0)
					throw new Exception(
							"Less than 32 Integer register data in reg.txt, count= "
									+ count);
				int value = Integer.parseInt(line, 2);
				RegisterManager.instance.setRegisterValue("R" + count, value);

				count++;

				if (count == 32)
					break;
			}

		} finally {
			if (bfread != null)
				bfread.close();
		}

	}
}
