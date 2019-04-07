//import java.io.File;
//import java.io.FileInputStream;
//import java.util.List;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.DateUtil;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFPictureData;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public final class Excel_reader  {
//
//    public static void main(String[] args) throws Exception  {
//
//        File excelFile = new File("D:\\EE122试卷\\data.xlsx");
//        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(excelFile));
//        XSSFSheet sheet = wb.getSheetAt(0);
//
//        for (Row row : sheet) {
//            for (Cell cell : row) {
//                switch (cell.getCellType()) {
//                    case Cell.CELL_TYPE_STRING:
//                        System.out.print(cell.getRichStringCellValue().getString());
//                        System.out.print("|");
//                        break;
//                    case Cell.CELL_TYPE_NUMERIC:
//                        if (DateUtil.isCellDateFormatted(cell)) {
//                            System.out.print(String.valueOf(cell.getDateCellValue()));
//                        } else {
//                            System.out.print(cell.getNumericCellValue());
//                        }
//                        System.out.print("|");
//                        break;
//                    case Cell.CELL_TYPE_BOOLEAN:
//                        System.out.print(cell.getBooleanCellValue());
//                        System.out.print("|");
//                        break;
//                    default:
//                }
//            }
//            System.out.println();
//        }
//
//        //读取图片
//        List<XSSFPictureData> pictures = wb.getAllPictures();
//        for (int i = 0; i < pictures.size(); i++) {
//            XSSFPictureData pictureData = pictures.get(i);
//            byte[] picData = pictureData.getData();
//            System.out.println("image-size:" + picData.length);
//        }
//
//
//        System.out.println(wb.getSheetName(0));
//    }
//}
