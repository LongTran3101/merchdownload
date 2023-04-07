//package toolupteepublic;
//
//
//public class MainTestChorme {
//    }
//
////	public static List<Account> readFromExcel(String file) throws IOException {
////		List<Account> reList = new ArrayList<>();
////		XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
////		XSSFSheet myExcelSheet = myExcelBook.getSheet("data");
////		for (int i = 1; i < myExcelSheet.getPhysicalNumberOfRows(); i++) {
////			{
////				Account account = new Account();
////				if (myExcelSheet.getRow(i).getCell(1) != null
////						&& myExcelSheet.getRow(i).getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING) {
////					String name = myExcelSheet.getRow(i).getCell(1).getStringCellValue();
////					System.out.println("name : " + name);
////					account.setUserName(name);
////				} else {
////					break;
////					// account.setUserName("");
////
////				}
////
////				if (myExcelSheet.getRow(i).getCell(2) != null
////						&& myExcelSheet.getRow(i).getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING) {
////					String pass = myExcelSheet.getRow(i).getCell(2).getStringCellValue();
////					System.out.println("pass :" + pass);
////					account.setPassword(pass);
////				} else {
////					account.setPassword("");
////				}
////
////				reList.add(account);
////			}
////		}
////
////		myExcelBook.close();
////		return reList;
////
////	}
//
//    public static BufferedImage getDifferenceImage(BufferedImage img1, BufferedImage img2) {
//    int width1 = img1.getWidth(); // Change - getWidth() and getHeight() for BufferedImage
//    int width2 = img2.getWidth(); // take no arguments
//    int height1 = img1.getHeight();
//    int height2 = img2.getHeight();
//    if ((width1 != width2) || (height1 != height2)) {
//        System.err.println("Error: Images dimensions mismatch");
//        System.exit(1);
//    }
//
//    // NEW - Create output Buffered image of type RGB
//    BufferedImage outImg = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);
//
//    // Modified - Changed to int as pixels are ints
//    int diff;
//    int result; // Stores output pixel
//    for (int i = 0; i < height1; i++) {
//        for (int j = 0; j < width1; j++) {
//            int rgb1 = img1.getRGB(j, i);
//            int rgb2 = img2.getRGB(j, i);
//            int r1 = (rgb1 >> 16) & 0xff;
//            int g1 = (rgb1 >> 8) & 0xff;
//            int b1 = (rgb1) & 0xff;
//            int r2 = (rgb2 >> 16) & 0xff;
//            int g2 = (rgb2 >> 8) & 0xff;
//            int b2 = (rgb2) & 0xff;
//            diff = Math.abs(r1 - r2); // Change
//            diff += Math.abs(g1 - g2);
//            diff += Math.abs(b1 - b2);
//            diff /= 3; // Change - Ensure result is between 0 - 255
//            // Make the difference image gray scale
//            // The RGB components are all the same
//            result = (diff << 16) | (diff << 8) | diff;
//            outImg.setRGB(j, i, result); // Set result
//        }
//    }
//
//    // Now return
//    return outImg;
//}
//    public static List<Image> readImageExcel(File file) throws IOException, InvalidFormatException {
//        List<Image> reList = new ArrayList<>();
//        XSSFWorkbook myExcelBook = new XSSFWorkbook(file);
//        XSSFSheet myExcelSheet = myExcelBook.getSheet("ketqua");
//        for (int i = 1; i < myExcelSheet.getPhysicalNumberOfRows(); i++) {
//            {
//                Image image = new Image();
//                if (myExcelSheet.getRow(i).getCell(0) != null
//                        && myExcelSheet.getRow(i).getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
//                    String Foldername = myExcelSheet.getRow(i).getCell(0).getStringCellValue();
//                    // System.out.println("name : " + Foldername);
//                    image.setFoldername(Foldername);
//                } else {
//                    break;
//                    // image.setFoldername("");
//                }
//
//                if (myExcelSheet.getRow(i).getCell(1) != null
//                        && myExcelSheet.getRow(i).getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING) {
//                    String Imagename = myExcelSheet.getRow(i).getCell(1).getStringCellValue();
//                    // System.out.println("Imagename :" + Imagename);
//                    image.setImagename(Imagename);
//                } else {
//                    image.setImagename("");
//                }
//
//                if (myExcelSheet.getRow(i).getCell(2) != null
//                        && myExcelSheet.getRow(i).getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING) {
//                    String Title = myExcelSheet.getRow(i).getCell(2).getStringCellValue();
//                    // System.out.println("Title :" + Title);
//                    image.setTitle(Title);
//                } else {
//                    image.setTitle("");
//                }
//
//                if (myExcelSheet.getRow(i).getCell(3) != null
//                        && myExcelSheet.getRow(i).getCell(3).getCellType() == HSSFCell.CELL_TYPE_STRING) {
//                    String Des = myExcelSheet.getRow(i).getCell(3).getStringCellValue();
//                    // System.out.println("Des :" + Des);
//                    image.setDes(Des);
//                } else {
//                    image.setDes("");
//                }
//
//                if (myExcelSheet.getRow(i).getCell(4) != null
//                        && myExcelSheet.getRow(i).getCell(4).getCellType() == HSSFCell.CELL_TYPE_STRING) {
//                    String Tag = myExcelSheet.getRow(i).getCell(4).getStringCellValue();
//                    // System.out.println("Tag :" + Tag);
//                    image.setTag(Tag);
//                } else {
//                    image.setTag("");
//                }
//                if (myExcelSheet.getRow(i).getCell(5) != null
//                        && myExcelSheet.getRow(i).getCell(5).getCellType() == HSSFCell.CELL_TYPE_STRING) {
//                    String Main = myExcelSheet.getRow(i).getCell(5).getStringCellValue();
//                    // System.out.println("Main :" + Main);
//                    image.setMain(Main);
//                } else {
//                    image.setMain("");
//                }
//
//                reList.add(image);
//            }
//        }
//
//        myExcelBook.close();
//        return reList;
//
//    }
//
//}
