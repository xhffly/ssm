package org.ssm.common;

import java.io.File;
import java.util.List;

import junit.framework.TestCase;

public class CSVUtilsTest extends TestCase {

	public void testWriteCsv() {
		
	}

	public void testReadCsv() {
		File file = new File("D:/csvtest.csv");
		List<String[]> dataList = CSVUtils.readCsv(file);
		
		System.out.println(dataList.get(1)[1]);
	}

}
