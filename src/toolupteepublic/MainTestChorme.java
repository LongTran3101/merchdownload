package toolupteepublic;

import io.appium.java_client.remote.MobileCapabilityType;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.misc.BASE64Decoder;


public class MainTestChorme {
    public static void checkCaptcha(WebDriver driver,JavascriptExecutor js,String PathLocal) throws InterruptedException, IOException{
         WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.geetest_radar_tip")));
      
        js.executeScript("$('.geetest_radar_tip').click();");
        
       new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.geetest_canvas_img canvas:nth-child(1)")));
         Thread.sleep(2000);
        
        
        
        WebElement elem = driver.findElement(By.cssSelector("div.geetest_canvas_img canvas:nth-child(1)"));

        String base64string = (String) js.executeScript("return arguments[0].toDataURL('image/png').replace(/^data:image\\/png;base64,/, '');", elem);
        String[] base64Array = base64string.split(",");

        String base64 = base64Array[base64Array.length - 1];

        System.out.println("a");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] data = decoder.decodeBuffer(base64);

        ByteArrayInputStream memstream = new ByteArrayInputStream(data);
        BufferedImage saveImage = ImageIO.read(memstream);

        ImageIO.write(saveImage, "png", new File("captcha.png"));

        elem = driver.findElement(By.cssSelector("div.geetest_canvas_img canvas:nth-child(2)"));

        base64string = (String) js.executeScript("return arguments[0].toDataURL('image/png').replace(/^data:image\\/png;base64,/, '');", elem);
        base64Array = base64string.split(",");

        base64 = base64Array[base64Array.length - 1];

        System.out.println("a");

        data = decoder.decodeBuffer(base64);

        memstream = new ByteArrayInputStream(data);
        saveImage = ImageIO.read(memstream);

        ImageIO.write(saveImage, "png", new File("puzzle.png"));

        //elem = driver.findElement(By.cssSelector("div..geetest_canvas_img .geetest_canvas_fullbg"));

        base64string = (String) js.executeScript("return ($('.geetest_canvas_img .geetest_canvas_fullbg')[0]).toDataURL('image/png').replace(/^data:image\\/png;base64,/, '');");
        base64Array = base64string.split(",");

        base64 = base64Array[base64Array.length - 1];

        System.out.println("a");

        data = decoder.decodeBuffer(base64);

        memstream = new ByteArrayInputStream(data);
        saveImage = ImageIO.read(memstream);

        ImageIO.write(saveImage, "png", new File("original.png"));
        
        
//           js.executeScript("$('body,html').animate({scrollTop: $('.geetest_slider_button').offset().top}, 800);");
//          System.out.println("a");
//           Thread.sleep(2000);
//          System.out.println("a");
         WebElement botton = driver.findElement(By.cssSelector("div.geetest_slider_button"));
        
     
        
//         int x = ((botton.getSize().getHeight()/2) - botton.getSize().getHeight());
//        int y =  (botton.getSize().getWidth()/2) - botton.getSize().getWidth();
//        Locatable elementLocation = (Locatable) botton;
        
//    Point ab =   elementLocation.getCoordinates().inViewPort();
//        System.out.println(x);
//        System.out.println(y);
//        System.out.println("x new"+ab.getX() +" y new" + ab.getY());
//        int yz=ab.getY();
        
       
        System.out.println("trc node");
        ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "cd "+PathLocal+" && node index.js");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        StringBuffer a=new StringBuffer();
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            a.append(line);
            
        }
        System.out.println(a.toString());
        String[] catchapx=a.toString().split(",");
        int cx=Integer.parseInt(catchapx[0]);
        
        int cxPuzzle=Integer.parseInt(catchapx[1]);
        String move=String.valueOf(cx-cxPuzzle) +"px";
//         js.executeScript("$('.geetest_slider_button').css({'transform' : 'translate("+move+", 0px)'});");
//            Thread.sleep(2000);
        Actions action=new Actions(driver);
        action.moveToElement(botton);
        //action.clickAndHold();
        //action.moveByOffset(cx-cxPuzzle, 0);
        action.clickAndHold();
        action.moveByOffset(cx-cxPuzzle-10, 0);
        action.pause(Duration.ofSeconds(2l));
        //action.moveByOffset( 0, 0).pause(cx);
        action.moveByOffset(5, 0);
        action.pause(Duration.ofSeconds(2l));
        action.dragAndDropBy(botton,5, 0);
        //action.dragAndDropBy(botton, cx-cxPuzzle, 0).pause(3000);
        action.build().perform();
    }
    public static void main(String[] args) throws InterruptedException, IOException, InvalidFormatException {
        // readFromExcel("listing.xlsx");

//         File originalimage = new File("original.png");
//        BufferedImage originalbuff = ImageIO.read(originalimage);
//        
//         File captchaimage = new File("captcha.png");
//        BufferedImage captchabuff = ImageIO.read(captchaimage);
//        
//        BufferedImage diff=getDifferenceImage(originalbuff, captchabuff);
//        ImageIO.write(diff, "png", new File("diff.png"));
        ProtectionDomain pd = MainTestChorme.class.getProtectionDomain();
        CodeSource cs = pd.getCodeSource();
        URL location = cs.getLocation();

        File directory4 = new File(location.getPath());
        String PathLocal = directory4.getParentFile().getPath();
        System.out.println(PathLocal);
        // List<Account> listacc=readFromExcel("listing.xlsx");
        File directory1 = new File(PathLocal + "./listing.xlsx");
        ///List<Image> listacc = readImageExcel(directory1);

        // System.out.println(listacc.size());
//        File currentDir = new File("");
//        System.out.println(currentDir.getAbsolutePath());
//        try {
//            File myObj = new File(PathLocal + "./a/51A7ObLTGVL.png");
//            if (myObj.createNewFile()) {
//                System.out.println("File created: " + myObj.getName());
//            } else {
//                System.out.println("File already exists.");
//            }
//        } catch (Exception e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
        File directory2 = new File(PathLocal + "./chromedriver.exe");
        String chromeDriverPath = directory2.getPath();
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        /*
         * ChromeDriverService chSvc = new ChromeDriverService.Builder()
         * .usingDriverExecutable(new
         * File("./chromedriver.exe")).usingAnyFreePort().build();
         */
        // theo debug
        /*
         * ChromeOptions options = new ChromeOptions();
         * 
         * options.setExperimentalOption("debuggerAddress", "127.0.0.1:4444");
         * options.addArguments("--start-maximized");
         * options.addArguments("disable-infobars"); WebDriver driver = new
         * ChromeDriver( options);
         */
        // end
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String chromeProfilePath = "C:\\Users\\me\\AppData\\Local\\Google\\Chrome\\User Data\\";
        ChromeOptions chromeProfile = new ChromeOptions();

        chromeProfile.addArguments("start-maximized");
        
        chromeProfile.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
        chromeProfile.setExperimentalOption("useAutomationExtension", false);
        chromeProfile.addArguments("--user-data-dir=" + chromeProfilePath);
        // Here you specify the actual profile folder (Profile 2)
        chromeProfile.addArguments("--profile-directory=Default");
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeProfile);

        WebDriver driver = new ChromeDriver(capabilities);

        String scrip = "(function(jqueryUrl, callback) {\n"
                + "    if (typeof jqueryUrl != 'string') {\n"
                + "        jqueryUrl = \n"
                + "            'https://code.jquery.com/jquery-3.2.1.min.js';\n"
                + "    }\n"
                + "    if (typeof jQuery == 'undefined') {\n"
                + "        var script = document.createElement('script');\n"
                + "        var head = document.getElementsByTagName('head')[0];\n"
                + "        var done = false;\n"
                + "        script.onload = script.onreadystatechange = (function() {\n"
                + "            if (!done && (!this.readyState || this.readyState == 'loaded'\n"
                + "                    || this.readyState == 'complete')) {\n"
                + "                done = true;\n"
                + "                script.onload = script.onreadystatechange = null;\n"
                + "                head.removeChild(script);\n"
                + "                callback();\n"
                + "            }\n"
                + "        });\n"
                + "        script.src = jqueryUrl;\n"
                + "        head.appendChild(script);\n"
                + "    }\n"
                + "    else {\n"
                + "        callback();\n"
                + "    }\n"
                + "})(arguments[0], arguments[arguments.length - 1]);";

        // driver.get("https://www.facebook.com");
        
        
         WebElement elem3333 = driver.findElement(By.cssSelector("input#TEST"));
         for (int i = 0; i < 49; i++) {
            elem3333.sendKeys(Keys.RIGHT);
        }
         
        driver.get("https://www.geetest.com/en/demo");
        Thread.sleep(10000);
        injectJQuery(driver, scrip);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("$('.tab-item-1').click();");
        
        
        checkCaptcha(driver, js,PathLocal);
        System.out.println("sau function check captcha");
        Thread.sleep(500000);
        System.out.println("a");
        Thread.sleep(5000);
         WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.geetest_radar_tip")));
      
        js.executeScript("$('.geetest_radar_tip').click();");
        
       new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.geetest_canvas_img canvas:nth-child(1)")));
         Thread.sleep(1500);
        
        
        
        WebElement elem = driver.findElement(By.cssSelector("div.geetest_canvas_img canvas:nth-child(1)"));

        String base64string = (String) js.executeScript("return arguments[0].toDataURL('image/png').replace(/^data:image\\/png;base64,/, '');", elem);
        String[] base64Array = base64string.split(",");

        String base64 = base64Array[base64Array.length - 1];

        System.out.println("a");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] data = decoder.decodeBuffer(base64);

        ByteArrayInputStream memstream = new ByteArrayInputStream(data);
        BufferedImage saveImage = ImageIO.read(memstream);

        ImageIO.write(saveImage, "png", new File("captcha.png"));

        elem = driver.findElement(By.cssSelector("div.geetest_canvas_img canvas:nth-child(2)"));

        base64string = (String) js.executeScript("return arguments[0].toDataURL('image/png').replace(/^data:image\\/png;base64,/, '');", elem);
        base64Array = base64string.split(",");

        base64 = base64Array[base64Array.length - 1];

        System.out.println("a");

        data = decoder.decodeBuffer(base64);

        memstream = new ByteArrayInputStream(data);
        saveImage = ImageIO.read(memstream);

        ImageIO.write(saveImage, "png", new File("puzzle.png"));

        //elem = driver.findElement(By.cssSelector("div..geetest_canvas_img .geetest_canvas_fullbg"));

        base64string = (String) js.executeScript("return ($('.geetest_canvas_img .geetest_canvas_fullbg')[0]).toDataURL('image/png').replace(/^data:image\\/png;base64,/, '');");
        base64Array = base64string.split(",");

        base64 = base64Array[base64Array.length - 1];

        System.out.println("a");

        data = decoder.decodeBuffer(base64);

        memstream = new ByteArrayInputStream(data);
        saveImage = ImageIO.read(memstream);

        ImageIO.write(saveImage, "png", new File("original.png"));
        
        
//           js.executeScript("$('body,html').animate({scrollTop: $('.geetest_slider_button').offset().top}, 800);");
//          System.out.println("a");
//           Thread.sleep(2000);
//          System.out.println("a");
         WebElement botton = driver.findElement(By.cssSelector("div.geetest_slider_button"));
        
     
        
//         int x = ((botton.getSize().getHeight()/2) - botton.getSize().getHeight());
//        int y =  (botton.getSize().getWidth()/2) - botton.getSize().getWidth();
//        Locatable elementLocation = (Locatable) botton;
        
//    Point ab =   elementLocation.getCoordinates().inViewPort();
//        System.out.println(x);
//        System.out.println(y);
//        System.out.println("x new"+ab.getX() +" y new" + ab.getY());
//        int yz=ab.getY();
        
       
        System.out.println("trc node");
        ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "cd "+PathLocal+" && node index.js");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        StringBuffer a=new StringBuffer();
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            a.append(line);
            
        }
        System.out.println(a.toString());
        String[] catchapx=a.toString().split(",");
        int cx=Integer.parseInt(catchapx[0]);
        
        int cxPuzzle=Integer.parseInt(catchapx[1]);
        String move=String.valueOf(cx-cxPuzzle) +"px";
//         js.executeScript("$('.geetest_slider_button').css({'transform' : 'translate("+move+", 0px)'});");
//            Thread.sleep(2000);
        Actions action=new Actions(driver);
        action.moveToElement(botton);
        //action.clickAndHold();
        //action.moveByOffset(cx-cxPuzzle, 0);
        action.clickAndHold();
        action.moveByOffset(cx-cxPuzzle-10, 0);
        action.pause(Duration.ofSeconds(2l));
        //action.moveByOffset( 0, 0).pause(cx);
        action.moveByOffset(5, 0);
        action.pause(Duration.ofSeconds(2l));
        action.dragAndDropBy(botton,5, 0);
        //action.dragAndDropBy(botton, cx-cxPuzzle, 0).pause(3000);
        action.build().perform();
        //action.moveByOffset(x, yz);
//        int xPosition=x + cx - cxPuzzle ;
//        int yPosition = y ;
        //action.dragAndDropBy(botton, xPosition, yPosition);
//        //Thread.sleep(500);
////        System.out.println("hold");
////        js.executeScript("$('.geetest_slider_button').css({'transform' : 'translate("+move+", 0px)'});");
////        System.out.println("transform");
////        Thread.sleep(1000);
////          WebElement botton2 = driver.findElement(By.cssSelector("div.geetest_slider_button"));
////        action.moveToElement(botton2);
////            Thread.sleep(2000);
//        
//        //action.moveByOffset(xPosition,yPosition);
////          Thread.sleep(5000);
////         xPosition =  xPosition+ cx - cxPuzzle;
////         yPosition = y;
//        ////pyautogui.moveTo(x*1920/a, (y + c)*1080/b, 0.1);
//        //action.moveByOffset(xPosition,yPosition);
//        //action.moveByOffset(x+cx-cxPuzzle,y);
//        //action.
//        action.perform(); 
//        
        System.out.println("sau move");
        Thread.sleep(500000);
        

//
//        String canvas_base64 = (String) js.executeScript("return arguments[0].toDataURL('image/png').substring(21);", elem);
//        File outputfile = new File("image.png");
//        System.out.println(outputfile.getPath());
//        BASE64Decoder decoder = new BASE64Decoder();
//        byte[] data = decoder.decodeBuffer(canvas_base64);
//try (OutputStream stream = new FileOutputStream(outputfile)) {
//    stream.write(data);
//}
        //Base64.getDecoder().
        /*Thread.sleep(30000);
         WebElement elem2 = driver.findElement(By.xpath("//input[@type='file']"));
         elem2.sendKeys("D:\\developdemo\\spy\\a\\51A7ObLTGVL.png");*/
        Thread.sleep(50000);

        System.out.println("link up");
        driver.get("https://www.teepublic.com/design/quick_create");
        Thread.sleep(10000);

//        for (Image image : listacc) {
//            if (isElementcssSelector(".m-uploader-funnel.tp-file-single", driver)) {
//                driver.findElement(By.cssSelector(".m-uploader-funnel.tp-file-single")).click();
//            }
//            System.out.println("sau link up");
//            WebElement elem = driver.findElement(By.xpath("//input[@type='file']"));
//            elem.sendKeys(PathLocal + "./a/" + image.getImagename());
//            Thread.sleep(50000);
//            System.out.println("xu ly tag");
//            String[] words = image.getTag().toLowerCase().replace(",", "").split(" ");
//            List<String> newtag = new ArrayList<>();
//            for (int jkk = 0; jkk < words.length; jkk++) {
//                if (words[jkk] == null || words[jkk].trim().isEmpty()) {
//                    continue;
//                }
//                if (newtag.contains(words[jkk].trim()) == false) {
//                    newtag.add(words[jkk].trim());
//                }
//
//            }
//            WebElement element_enter = driver.findElement(By.cssSelector("div#secondary_tags input.ui-autocomplete-input"));
//            int lengtag = 15;
//            if (newtag.size() < 15) {
//                lengtag = newtag.size();
//            }
//            for (int k = 0; k < lengtag; k++) {
//                element_enter.sendKeys(newtag.get(k).trim());
//                Thread.sleep(500);
//                element_enter.sendKeys(Keys.ENTER);
//                Thread.sleep(2000);
//            }
//            Thread.sleep(1000);
//            WebElement element_maintag = driver.findElement(By.cssSelector("input#design_primary_tag"));
//            element_maintag.sendKeys(image.getMain());
//            Thread.sleep(500);
//            WebElement element_title = driver.findElement(By.cssSelector("input#design_design_title"));
//            element_title.sendKeys(image.getMain());
//            Thread.sleep(500);
//            WebElement element_des = driver.findElement(By.cssSelector("textarea#design_design_description"));
//            element_des.sendKeys(image.getMain());
//            Thread.sleep(500);
//            System.out.println("add jquery ");
//
//            injectJQuery(driver, scrip);
//            System.out.println("End add jquery ");
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//
//            Thread.sleep(5000);
//            System.out.println("change mau");
//            js.executeScript("$('#primary_color_tshirt .dd-select').click();");
//            js.executeScript("$('#primary_color_tshirt .dd-click-off-close li:nth-child(4) a').click();");
//            Thread.sleep(500);
//            js.executeScript("$('#primary_color_baseballtee .dd-select').click();");
//            js.executeScript("$('#primary_color_baseballtee .dd-click-off-close li:nth-child(2) a').click();");
//            Thread.sleep(500);
//            Thread.sleep(5000);
//            System.out.println("resize ");
//            js.executeScript("$('#design_mockup_config_case_attributes_image_size').val(80).trigger('change');");
//            js.executeScript("$('#design_mockup_config_pillow_attributes_image_size').val(80).trigger('change');");
//
//            System.out.println("cang giua ");
//            js.executeScript("$('.m-uploader-toolbar_tool-h-align').click();");
//            js.executeScript("$('.m-uploader-toolbar_tool-top-align').click();");
//            js.executeScript("$('.m-uploader-toolbar_tool-v-align').click();");
//
//            Thread.sleep(500);
//            driver.findElement(By.cssSelector("input#terms")).click();
//            Thread.sleep(500);
//            driver.findElement(By.cssSelector("button.publish-and-promote-button")).click();
//            //driver.findElement(By.cssSelector("div#primary_color_tshirt ul.dd-click-off-close li:nth-child(4) a")).click();
//            Thread.sleep(10000);
//            Thread.sleep(2000);
//            driver.get("https://www.teepublic.com/");
//
//            /*Thread.sleep(30000);
//		WebElement elem2 = driver.findElement(By.xpath("//input[@type='file']"));
//		elem2.sendKeys("D:\\developdemo\\spy\\a\\51A7ObLTGVL.png");*/
//            Thread.sleep(5000);
//
//            System.out.println("link up");
//            driver.get("https://www.teepublic.com/design/quick_create");
//            Thread.sleep(10000);
//
//        }
        // Logout
    }

    public static void injectJQuery(WebDriver driver, String jQueryStr)
            throws IOException {

        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript(jQueryStr);
    }

    protected static boolean isElementcssSelector(String tagcss, WebDriver driver) {
        try {
            driver.findElement(By.cssSelector(tagcss));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
//	public static List<Account> readFromExcel(String file) throws IOException {
//		List<Account> reList = new ArrayList<>();
//		XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
//		XSSFSheet myExcelSheet = myExcelBook.getSheet("data");
//		for (int i = 1; i < myExcelSheet.getPhysicalNumberOfRows(); i++) {
//			{
//				Account account = new Account();
//				if (myExcelSheet.getRow(i).getCell(1) != null
//						&& myExcelSheet.getRow(i).getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING) {
//					String name = myExcelSheet.getRow(i).getCell(1).getStringCellValue();
//					System.out.println("name : " + name);
//					account.setUserName(name);
//				} else {
//					break;
//					// account.setUserName("");
//
//				}
//
//				if (myExcelSheet.getRow(i).getCell(2) != null
//						&& myExcelSheet.getRow(i).getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING) {
//					String pass = myExcelSheet.getRow(i).getCell(2).getStringCellValue();
//					System.out.println("pass :" + pass);
//					account.setPassword(pass);
//				} else {
//					account.setPassword("");
//				}
//
//				reList.add(account);
//			}
//		}
//
//		myExcelBook.close();
//		return reList;
//
//	}

    public static BufferedImage getDifferenceImage(BufferedImage img1, BufferedImage img2) {
    int width1 = img1.getWidth(); // Change - getWidth() and getHeight() for BufferedImage
    int width2 = img2.getWidth(); // take no arguments
    int height1 = img1.getHeight();
    int height2 = img2.getHeight();
    if ((width1 != width2) || (height1 != height2)) {
        System.err.println("Error: Images dimensions mismatch");
        System.exit(1);
    }

    // NEW - Create output Buffered image of type RGB
    BufferedImage outImg = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);

    // Modified - Changed to int as pixels are ints
    int diff;
    int result; // Stores output pixel
    for (int i = 0; i < height1; i++) {
        for (int j = 0; j < width1; j++) {
            int rgb1 = img1.getRGB(j, i);
            int rgb2 = img2.getRGB(j, i);
            int r1 = (rgb1 >> 16) & 0xff;
            int g1 = (rgb1 >> 8) & 0xff;
            int b1 = (rgb1) & 0xff;
            int r2 = (rgb2 >> 16) & 0xff;
            int g2 = (rgb2 >> 8) & 0xff;
            int b2 = (rgb2) & 0xff;
            diff = Math.abs(r1 - r2); // Change
            diff += Math.abs(g1 - g2);
            diff += Math.abs(b1 - b2);
            diff /= 3; // Change - Ensure result is between 0 - 255
            // Make the difference image gray scale
            // The RGB components are all the same
            result = (diff << 16) | (diff << 8) | diff;
            outImg.setRGB(j, i, result); // Set result
        }
    }

    // Now return
    return outImg;
}
    public static List<Image> readImageExcel(File file) throws IOException, InvalidFormatException {
        List<Image> reList = new ArrayList<>();
        XSSFWorkbook myExcelBook = new XSSFWorkbook(file);
        XSSFSheet myExcelSheet = myExcelBook.getSheet("ketqua");
        for (int i = 1; i < myExcelSheet.getPhysicalNumberOfRows(); i++) {
            {
                Image image = new Image();
                if (myExcelSheet.getRow(i).getCell(0) != null
                        && myExcelSheet.getRow(i).getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    String Foldername = myExcelSheet.getRow(i).getCell(0).getStringCellValue();
                    // System.out.println("name : " + Foldername);
                    image.setFoldername(Foldername);
                } else {
                    break;
                    // image.setFoldername("");
                }

                if (myExcelSheet.getRow(i).getCell(1) != null
                        && myExcelSheet.getRow(i).getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    String Imagename = myExcelSheet.getRow(i).getCell(1).getStringCellValue();
                    // System.out.println("Imagename :" + Imagename);
                    image.setImagename(Imagename);
                } else {
                    image.setImagename("");
                }

                if (myExcelSheet.getRow(i).getCell(2) != null
                        && myExcelSheet.getRow(i).getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    String Title = myExcelSheet.getRow(i).getCell(2).getStringCellValue();
                    // System.out.println("Title :" + Title);
                    image.setTitle(Title);
                } else {
                    image.setTitle("");
                }

                if (myExcelSheet.getRow(i).getCell(3) != null
                        && myExcelSheet.getRow(i).getCell(3).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    String Des = myExcelSheet.getRow(i).getCell(3).getStringCellValue();
                    // System.out.println("Des :" + Des);
                    image.setDes(Des);
                } else {
                    image.setDes("");
                }

                if (myExcelSheet.getRow(i).getCell(4) != null
                        && myExcelSheet.getRow(i).getCell(4).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    String Tag = myExcelSheet.getRow(i).getCell(4).getStringCellValue();
                    // System.out.println("Tag :" + Tag);
                    image.setTag(Tag);
                } else {
                    image.setTag("");
                }
                if (myExcelSheet.getRow(i).getCell(5) != null
                        && myExcelSheet.getRow(i).getCell(5).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    String Main = myExcelSheet.getRow(i).getCell(5).getStringCellValue();
                    // System.out.println("Main :" + Main);
                    image.setMain(Main);
                } else {
                    image.setMain("");
                }

                reList.add(image);
            }
        }

        myExcelBook.close();
        return reList;

    }

}
