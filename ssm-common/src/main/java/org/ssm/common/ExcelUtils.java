package net.jqsoft.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fr.stable.StringUtils;

import net.jqsoft.common.exception.ManagerException;

public class ExcelUtils {
	
	/**
	 * 读取Excel中的内容
	 * @author			xiaohf
	 * @createDate		2016年12月12日下午4:18:51                                                                 
	 * @param filePath 要解析的文件路径
	 * @param startRow 开始解析的行号  从0开始
	 * @param startCol 开始解析数据的列 从0开始
	 * @param endCol   解析结束的列 从0开始
	 * @return
	 * @throws ManagerException
	 */
	public static List<String[]> readFile(String filePath,int startRow,int startCol,int endCol) throws ManagerException{
		
		
		if(startCol == endCol || startCol > endCol || endCol == 0){
			throw new ManagerException("startCol必须大于endCol，且endCol不能等于零");
		}
		
		//最终返回的excel内容
		List<String[]> excelContents = new ArrayList<String[]>();
		
		// 文件写入输入流
		InputStream inputStream = null;
		
		OutputStream os = null;
		
		try {
			
			File excelFile = new File(filePath);


			if (!excelFile.exists()) {// 文件不存在，不继续执行
				throw new ManagerException("Excel文件导入-上传文件未找到！");
			}

			// 创建工作表对象
			Workbook workbook = null;
			try {
				workbook = new HSSFWorkbook(new FileInputStream(excelFile));
			} catch (Exception ex) {
				workbook = new XSSFWorkbook(new FileInputStream(excelFile));
			}


			// 获取第一个Sheet
			Sheet sheet = workbook.getSheetAt(0);
			if (sheet == null) {
				throw new ManagerException("Excel文件导入-文件中没有找到对应工作表");
			}

			// 开始从第一行遍历
			for (int rowNum = startRow; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					continue;
				}
				//遍历该行的单元格 定义储存数据的单元格数组
				String[] cells = new String[endCol-startCol+1];
				int colNum = 0;
				int emptyCellCount = 0;
				for (int i = startCol; i <= endCol; i++) {
					Cell cell = row.getCell(i);
					if (cell != null && StringUtils.isNotBlank(cell.toString())) {
						cells[colNum] = getValue(cell);
					}else{
						emptyCellCount++;
					}
					colNum++;
				}
				//每个单元格都是空的，则认为该行就是空行，跳过
				if(emptyCellCount == (endCol-startCol+1)){
					continue;
				}
				excelContents.add(cells);
			}
			
		} catch (FileNotFoundException e) {
			throw new ManagerException("Excel文件导入-上传文件未找到"+e.getMessage());
		} catch (IOException e) {
			throw new ManagerException("Excel文件导入-文件读取失败"+e.getMessage());
		} catch (ManagerException e) {
			throw new ManagerException("Excel文件导入-"+e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("Excel文件导入-文件读取失败:"+e.getMessage());
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}
				if (os != null) {
					os.close();
					os = null;
				}
			} catch (IOException e) {
				throw new ManagerException("100200","Excel文件导入-文件读取失败:"+e.getMessage());
			}
		}
		return excelContents;
	}

	
	/**
	 * 获得单元格中的值
	 * 
	 * @author 肖红飞
	 * @createDate 2014-10-27下午03:07:44
	 * @arithMetic
	 * @param cell
	 * @param cellItem 
	 * @return
	 */
	@SuppressWarnings("static-access")
	private static String getValue(Cell cell) {
		String cellValue= "";
		cellValue = cell.toString(); //可以直接tostring取出单元格中的结果
		if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
			// 返回布尔类型的值
			cellValue = String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) { // 数值型
			// 日期型格式处理
			 
			if(HSSFDateUtil.isCellDateFormatted(cell)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				cellValue = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
			}else{
				DecimalFormat df = new DecimalFormat("0");  
				cellValue = df.format(cell.getNumericCellValue());  
			}
		} else if (cell.getCellType() == cell.CELL_TYPE_BLANK) {

		} else if (cell.getCellType() == cell.CELL_TYPE_FORMULA) {

		} else if (cell.getCellType() == cell.CELL_TYPE_STRING) {
			// 返回字符串类型的值
			cellValue = String.valueOf(cell.getStringCellValue());
		}else{
			cellValue = String.valueOf(cell.getStringCellValue());
		}
		
		return cellValue.trim();
	}

	/**
	 * 获得1900年1月1号凌晨间隔n天的日期 转换Excel读取的日期
	 * 
	 * @param days
	 *            距离的天数
	 */
	public static Date from1900(int days) {
		Calendar c = Calendar.getInstance();
		c.set(1900, 0, -1);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}
	

    /**
     * @param strArr    标题数组
     * @param list      字符串数组列表（准备数据：将要导出的数据转换成这种格式）
     * @param width     指定每列的宽度
     * @param sheetName 设置第一个工作表的名称
     * @return
     * @throws Exception
     */
	@Deprecated
    public static InputStream export(String[] strArr, List<Object[]> list, int[] width, String sheetName) throws IOException {
    	
    	 Workbook workbook = new HSSFWorkbook();//产生工作薄对象
         CellStyle style = workbook.createCellStyle();//创建样式
         Font f = workbook.createFont();//创建字体
         Sheet sheet = workbook.createSheet();//产生工作表对象
         workbook.setSheetName(0, sheetName);//设置第一个工作表的名称
         style = setStyle(workbook, style, f, 0);//启用大标题样式
         Row row = sheet.createRow(0); //产生一行
         Cell cell = null;
         sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) (strArr.length - 1)));
         cell = row.createCell(0);//产生单元格
         cell.setCellValue(sheetName);
         cell.setCellStyle(style);

         if (list.size() == 0) {
             style = setStyle(workbook, style, f, 1);//启用11号普通字体
             row = sheet.createRow(1); //产生一行
             cell = row.createCell(0);//产生单元格
             cell.setCellValue("没有数据。");
             cell.setCellStyle(style);
         } else {
             style = setStyle(workbook, style, f, 2);//启用12号加粗字体
             addHead(style,strArr,row,cell,sheet,1);//加载标题
             style = setStyle(workbook, style, f, 1);//启用11号普通字体
             addData(style,list,cell,sheet,2);//加载数据
         }
         setExcelWidth(sheet, width);
         ByteArrayOutputStream output = new ByteArrayOutputStream();
         workbook.write(output);
         byte[] ba = output.toByteArray();
         InputStream excelFile = new ByteArrayInputStream(ba);
         output.flush();
         output.close();
         return excelFile;
    }
    
    /**
     * @param tempPath  导出模版文件路径
     * @param list      字符串数组列表（准备数据：将要导出的数据转换成这种格式）
     * @param width     指定每列的宽度
     * @param sheetName 设置第一个工作表的名称
     * @return
     * @throws Exception
     */
    public static InputStream export(String tempPath, List<Object[]> list, int[] width, String sheetName) throws IOException {
    	

		InputStream excelFile = null;
		ByteArrayOutputStream output = null;
		try {
			File tempFile = new File(ExcelUtils.getWebContentPath()+tempPath);
			if (!tempFile.exists()) {// 文件不存在，不继续执行
				throw new ManagerException("模版文件未找到！");
			}
			//读取模版文件
			// 创建工作表对象
			Workbook workbook = null;
			try {
				workbook = new HSSFWorkbook(new FileInputStream(tempFile));
			} catch (Exception ex) {
				workbook = new XSSFWorkbook(new FileInputStream(tempFile));
			}
			CellStyle style = workbook.createCellStyle();//创建样式
			Font f = workbook.createFont();//创建字体
			Sheet sheet = workbook.getSheetAt(0);//产生工作表对象
			style = setStyle(workbook, style, f, 0);//启用大标题样式
			Cell cell = null;

			if (list.size() == 0) {
			    style = setStyle(workbook, style, f, 1);//启用11号普通字体
			    Row row = sheet.createRow(2); //产生一行
			    cell = row.createCell(0);//产生单元格
			    cell.setCellValue("没有数据。");
			    cell.setCellStyle(style);
			} else {
			    style = setStyle(workbook, style, f, 1);//启用11号普通字体
			    addData(style,list,cell,sheet,2);//加载数据
			}
			setExcelWidth(sheet, width);
			output = new ByteArrayOutputStream();
			workbook.write(output);
			byte[] ba = output.toByteArray();
			excelFile = new ByteArrayInputStream(ba);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(output != null){
				output.flush();
				output.close();
			}
		}
		
        return excelFile;
    }

    /**
	 * 获取Web根目录
	 */
	public static String getWebContentPath() {
		URL url = ExcelUtils.class.getResource("");
		if (url != null) {
			String path = url.toString();
			int webInfIndex = path.indexOf("WEB-INF");
			if (webInfIndex != -1) {
				path = path.substring(path.indexOf("/")+1, webInfIndex);
			}
			try {
				path = java.net.URLDecoder.decode(path, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return path;
		}
		
		return "";
	}
    /**
     * 填充标题
     * @param style 样式
     * @param strArr 标题数组
     * @param row
     * @param cell
     * @param sheet
     * @param rowIndex 设置标题所在行
     */
    private static void addHead(CellStyle style,String[] strArr, Row row, Cell cell,Sheet sheet,int rowIndex) {//加载标题
        row = sheet.createRow(rowIndex); //产生一行
        int i = 0;
        for (String str : strArr) {
            cell = row.createCell(i);//产生单元格
            cell.setCellValue(str);
            cell.setCellStyle(style);
            i++;
        }
    }

    /**
     * 填充数据
     * @param style 样式
     * @param list 数据的数组列表
     * @param row
     * @param cell
     * @param sheet
     * @param startRow 设置加载数据开始行
     */
    private static void addData(CellStyle style,List<Object[]> list,Cell cell,Sheet sheet,int startRow){
        int i=startRow;
        for (Object[] objs : list) {//加载数据
        	Row row = sheet.createRow(i);
            i++;//产生一行
            for (int j = 0; j < objs.length; j++) {
                cell = row.createCell(j);
                if (objs[j] instanceof String) {
                    cell.setCellValue((String) objs[j]);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                } else if (objs[j] instanceof Integer) {
                    cell.setCellValue((Integer) objs[j]);
                } if (objs[j] instanceof Double) {
                    cell.setCellValue((Double) objs[j]);
                } if (objs[j] instanceof Float) {
                    cell.setCellValue((Float) objs[j]);
                }
                cell.setCellStyle(style);
            }
        }
    }



    /**
     * 设置表格列宽
     * @param sheet
     */
    private static void setExcelWidth(Sheet sheet,int[] width){
        for(int i=0;i<width.length;i++){
            sheet.setColumnWidth(i, width[i]);
        }
    }

    /**
     * 自定义单元格样式 1普通  2加粗
     * @param workbook
     * @param style
     * @param f
     * @param kind
     */
    private static CellStyle setStyle(Workbook workbook, CellStyle style, Font f, int kind){
        style = workbook.createCellStyle();
        f = workbook.createFont();
        if(kind==0){
            f.setFontHeightInPoints((short) 20);//字号
            f.setFontName("黑体");
            f.setBoldweight(Font.BOLDWEIGHT_NORMAL);//加粗
            style.setAlignment(CellStyle.ALIGN_CENTER); // 居中
//            style.setFillForegroundColor((short) 13);// 设置背景色
//            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        }
        if(kind==1){
            f.setFontHeightInPoints((short) 11);//字号
            f.setBoldweight(Font.BOLDWEIGHT_NORMAL);//加粗
        }
        if(kind==2){
            f.setFontHeightInPoints((short) 12);//字号
            f.setBoldweight(Font.BOLDWEIGHT_BOLD);//加粗
            style.setAlignment(CellStyle.ALIGN_CENTER); // 居中
            style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());// 设置背景色
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        }

        style.setBorderBottom(CellStyle.BORDER_THIN);//下边框
        style.setBorderLeft(CellStyle.BORDER_THIN);//左边框
        style.setBorderRight(CellStyle.BORDER_THIN);//右边框
        style.setBorderTop(CellStyle.BORDER_THIN);//上边框

        style.setFont(f);
        return style;
    }

    /**
     * 使文件名不乱码(针对不同内核的浏览器返回不同编码的文件名)
     * @param fileName
     * @param request
     */
    public static String encodeFileName(String fileName,HttpServletRequest request) throws Exception{
        String userAgent = request.getHeader("User-Agent");
        //针对IE或者以IE为内核的浏览器：
        if (userAgent.contains("MSIE")||userAgent.contains("Trident")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            //非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
        }
        return fileName;
    }
    
}
