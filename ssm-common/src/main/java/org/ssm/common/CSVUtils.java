package org.ssm.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/**
 * CSV导入导出工具集合
 * 	                                                                                                 
 * @equals			
 * @author			xiaohf
 * @createDate		2016年10月26日 下午1:57:12
 * @version			v1.0
 */
public class CSVUtils {
	
	/**
	 * 数据写入CSV文件
	 * @author			xiaohf
	 * @createDate		2016年10月26日下午1:59:21
	 * @arithMetic                                                                           
	 * @param file
	 * @param dataList
	 * @return
	 */
	public static boolean writeCsv(File file, List<String> dataList) {
		boolean isSucess = false;

		FileOutputStream out = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			out = new FileOutputStream(file);
			osw = new OutputStreamWriter(out);
			bw = new BufferedWriter(osw);
			if (dataList != null && !dataList.isEmpty()) {
				for (String data : dataList) {
					bw.append(data).append("\r");
				}
			}
			isSucess = true;
		} catch (Exception e) {
			isSucess = false;
		} finally {
			if (bw != null) {
				try {
					bw.close();
					bw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (osw != null) {
				try {
					osw.close();
					osw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return isSucess;

	}
	
	/**
	 * CSV读取
	 * @author			xiaohf
	 * @createDate		2016年10月26日下午2:00:51
	 * @arithMetic                                                                           
	 * @param file
	 * @return
	 */
	public static List<String[]> readCsv(File file,String charset) {
		
		List<String[]> dataList = new ArrayList<String[]>();

//		BufferedReader br = null;
//		try {
//			br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
//			String line = "";
//			while ((line = br.readLine()) != null) {
//				String[] dataRow = line.split(",");
//				dataList.add(dataRow);
//			}
//		} catch (Exception e) {
//		} finally {
//			if (br != null) {
//				try {
//					br.close();
//					br = null;
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		try {
			Charset c = Charset.forName(charset);
			BufferedReader fReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),c));
			CSVReader csvReader = new CSVReader(fReader, ',');
			dataList = csvReader.readAll();

			csvReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
		}
		return dataList;
	}
	
}
