/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toolupteepublic;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.imgscalr.Scalr;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static toolupteepublic.MainTestChorme.isElementcssSelector;
import static toolupteepublic.MainTestChorme.readImageExcel;
import static toolupteepublic.downloadanh1.isElementXpath;

/**
 *
 * @author me
 */
public class RESIZEMERCH extends javax.swing.JFrame {

    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";
    /**
     *
     * Creates new form downloadanh
     */
    JFileChooser chooser;
    String choosertitle;
    String stringUrlSave;
    static String Key;

    public RESIZEMERCH() {

        initComponents();
    }

    private static BufferedImage trimImage(BufferedImage image) {
        try {
            WritableRaster raster = image.getAlphaRaster();
            int width = raster.getWidth();
            int height = raster.getHeight();
            int left = 0;
            int top = 0;
            int right = width - 1;
            int bottom = height - 1;
            int minRight = width - 1;
            int minBottom = height - 1;

            top:
            for (; top < bottom; top++) {
                for (int x = 0; x < width; x++) {
                    if (raster.getSample(x, top, 0) != 0) {
                        minRight = x;
                        minBottom = top;
                        break top;
                    }
                }
            }

            left:
            for (; left < minRight; left++) {
                for (int y = height - 1; y > top; y--) {
                    if (raster.getSample(left, y, 0) != 0) {
                        minBottom = y;
                        break left;
                    }
                }
            }

            bottom:
            for (; bottom > minBottom; bottom--) {
                for (int x = width - 1; x >= left; x--) {
                    if (raster.getSample(x, bottom, 0) != 0) {
                        minRight = x;
                        break bottom;
                    }
                }
            }

            right:
            for (; right > minRight; right--) {
                for (int y = bottom; y >= top; y--) {
                    if (raster.getSample(right, y, 0) != 0) {
                        break right;
                    }
                }
            }

            return image.getSubimage(left, top, right - left + 1, bottom - top + 1);
        } catch (Exception e) {
            return image;
        }

    }

    public static BufferedImage joinBufferedImage(BufferedImage img1, BufferedImage img2, int wid, int height) {

        //do some calculate first
        int offset = 5;

        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(wid, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
//        Color oldColor = g2.getColor();
//        //fill background
//        
//        g2.fillRect(0, 0, wid, height);
//        //draw image

        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, img1.getWidth() + offset, 0);
        g2.dispose();
        return newImage;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        result = new javax.swing.JLabel();
        buttonChosefoder = new javax.swing.JButton();
        linkSaveFile = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        abcxyz = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        width = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        hight = new javax.swing.JTextField();
        checkresize = new javax.swing.JCheckBox();
        tileresize = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        linkSaveFile1 = new javax.swing.JTextField();
        buttonChosefoder1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TOOL CRAWL MERCH");

        jButton1.setText("download");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonChosefoder.setText("Chọn thư mục lưu");
        buttonChosefoder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChosefoderActionPerformed(evt);
            }
        });

        linkSaveFile.setText("C:\\Users\\me\\Desktop\\tpl\\");
            linkSaveFile.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    linkSaveFileActionPerformed(evt);
                }
            });

            abcxyz.setColumns(20);
            abcxyz.setRows(5);
            jScrollPane1.setViewportView(abcxyz);

            jLabel2.setText("FB");

            jTextField1.setEditable(false);
            jTextField1.setText("fb.com/cwalk.rua");

            jTextField2.setEditable(false);
            jTextField2.setText("0985651126");
            jTextField2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextField2ActionPerformed(evt);
                }
            });

            jLabel4.setText("Phone");

            jTextField3.setEditable(false);
            jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField3.setText("Mỗi ngày mỗi key chỉ sử dụng được trên một máy");
            jTextField3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextField3ActionPerformed(evt);
                }
            });

            jTextField4.setEditable(false);
            jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField4.setText("Liên hệ để kich hoạt key");

            width.setText("4500");
            width.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    widthActionPerformed(evt);
                }
            });

            jLabel5.setText("X");

            hight.setText("5400");

            checkresize.setSelected(true);
            checkresize.setText("resize");

            tileresize.setText("80");

            jLabel8.setText("tỉ lệ resize");

            buttonGroup3.add(jRadioButton3);
            jRadioButton3.setSelected(true);
            jRadioButton3.setText("căn trên");

            buttonGroup3.add(jRadioButton4);
            jRadioButton4.setText("căn giữa");
            jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jRadioButton4ActionPerformed(evt);
                }
            });

            linkSaveFile1.setText("C:\\Users\\me\\Desktop\\tpl\\");
                linkSaveFile1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        linkSaveFile1ActionPerformed(evt);
                    }
                });

                buttonChosefoder1.setText("Chọn thư mục áo");
                buttonChosefoder1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        buttonChosefoder1ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(width, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonChosefoder))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jRadioButton3)
                                .addGap(26, 26, 26)
                                .addComponent(jRadioButton4)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tileresize)
                        .addGap(512, 512, 512))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(checkresize)
                                .addGap(114, 114, 114)
                                .addComponent(result)
                                .addGap(128, 128, 128)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hight, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextField4)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 110, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(247, 247, 247))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(buttonChosefoder1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(155, 155, 155)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(linkSaveFile1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(linkSaveFile))))
                        .addGap(20, 20, 20))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(tileresize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(linkSaveFile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonChosefoder1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonChosefoder, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linkSaveFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(result))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jRadioButton3)
                                    .addComponent(jRadioButton4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(width, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkresize))))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {

            final File folder = new File(linkSaveFile1.getText());
            List<Image> a = listFilesForFolder(folder);
            for (Image image : a) {
                File fi = new File(image.getFoldername());
                byte[] response2 = Files.readAllBytes(fi.toPath());
                //byte[] response = Files.readAllBytes(image.getFoldername());
                // byte[] response = out.toByteArray();
                if (checkresize.isSelected()) {
                    int newHeight = Integer.parseInt(hight.getText());
                    int newWidth = Integer.parseInt(width.getText());
                    int newHeightresize = Integer.parseInt(hight.getText()) * Integer.parseInt(tileresize.getText()) / 100;
                    int newWidthresize = Integer.parseInt(width.getText()) * Integer.parseInt(tileresize.getText()) / 100;
                    // PNG supports transparency
                    // int type = config.formatName.equals("png")?BufferedImage.TYPE_INT_ARGB:BufferedImage.TYPE_INT_RGB;
                    ByteArrayInputStream bais = new ByteArrayInputStream(response2);

                    BufferedImage outputImage = null;

                    BufferedImage inputimage = ImageIO.read(bais);
                    outputImage = trimImage(inputimage);

                    Scalr.Mode mode = Scalr.Mode.FIT_TO_HEIGHT;
                    BufferedImage outputImage2 = Scalr.resize(outputImage, Scalr.Method.ULTRA_QUALITY, mode, newWidthresize - 10, newHeightresize - 10, Scalr.OP_ANTIALIAS);
                    //System.out.println(outputImage2.getHeight());
                    if (outputImage2.getWidth() > newWidth) {
                        Scalr.Mode mode2 = Scalr.Mode.FIT_TO_WIDTH;
                        outputImage2 = Scalr.resize(outputImage, Scalr.Method.ULTRA_QUALITY, mode2, newWidthresize - 10, newHeightresize - 10, Scalr.OP_ANTIALIAS);
//                                            int hightwirte = Math.round(newHeight - outputImage2.getHeight());
//                                            if((newHeight - outputImage2.getHeight())>35)
//                                                 {
//                                                        hightwirte = Math.round(newHeight - outputImage2.getHeight()+20);
//                                                 }
                        int hightwirte = Math.round((newHeight - outputImage2.getHeight()) / 2);
                        
                      
                        
                        if ((newHeight - outputImage2.getHeight()) > 400) {
                            hightwirte=400;
                        }
                             if ((newHeight - outputImage2.getHeight()) > 600) {
                            hightwirte=500;
                        }
                              if ((newHeight - outputImage2.getHeight()) > 500) {
                            hightwirte=450;
                        }
                        int widthwirte = Math.round((newWidth - outputImage2.getWidth()) / 2);
                        int type = BufferedImage.TYPE_INT_ARGB;

                        BufferedImage outputImage4 = new BufferedImage(newWidth, newHeight, type);
                        Graphics2D graphics2D = outputImage4.createGraphics();
                        RenderingHints hints = new RenderingHints(RenderingHints.KEY_INTERPOLATION,
                                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                        graphics2D.setRenderingHints(hints);

                        graphics2D.drawImage(outputImage2, widthwirte, hightwirte, null);
                        graphics2D.dispose();

                        ImageIO.write(outputImage4, "png", new File(linkSaveFile.getText() + image.Title.replace("png.png", ".png")));
                    } else {
                        int hightwirte = Math.round((newHeight - outputImage2.getHeight()) / 2);
                        int widthwirte = Math.round((newWidth - outputImage2.getWidth()) / 2);

                        int type = BufferedImage.TYPE_INT_ARGB;

                        BufferedImage outputImage4 = new BufferedImage(newWidth, newHeight, type);
                        Graphics2D graphics2D = outputImage4.createGraphics();
                        RenderingHints hints = new RenderingHints(RenderingHints.KEY_INTERPOLATION,
                                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                        graphics2D.setRenderingHints(hints);

                        graphics2D.drawImage(outputImage2, widthwirte, hightwirte, null);
                        graphics2D.dispose();

                       ImageIO.write(outputImage4, "png", new File(linkSaveFile.getText() + image.Title.replace("png.png", ".png")));
                    }

                } else {

                    FileOutputStream fos = new FileOutputStream(linkSaveFile.getText() + image.Title.replace("png.png", ".png"));
                    fos.write(response2);
                    fos.close();

                }
            }

            System.out.println(a.size());
            this.abcxyz.setText("done");
        } catch (Exception ex) {
            // abcxyz.setText(ex.getMessage());

            ex.printStackTrace();
        }


    }//GEN-LAST:event_jButton1ActionPerformed
    public static void injectJQuery(WebDriver driver, String jQueryStr)
            throws IOException {

        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript(jQueryStr);
    }

    public boolean containsName(final List<image1> list, String name) {
        return list.stream().filter(o -> o.getUrl().equals(name)).findFirst().isPresent();
    }

    public static List<Image> listFilesForFolder(final File folder) {
        List<Image> lst = new ArrayList<Image>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
               if(fileEntry.getPath().contains(".png")){
                Image i = new Image();
                i.setTitle(fileEntry.getName());
                i.setFoldername(fileEntry.getPath());
                lst.add(i);
                System.out.println(fileEntry.getName());
               }
               
            }
        }
        return lst;
    }

    private BufferedImage createImageFromBytes(byte[] imageData) {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int randomInt(int min, int max) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int rand = random.nextInt(min, max);
        return rand;
    }

    public void clonepng() {

    }

    protected static boolean isElementcssSelector(String tagcss, ChromeDriver driver) {
        try {
            driver.findElement(By.cssSelector(tagcss));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected static boolean isElementXpath(String xpath, ChromeDriver driver) {
        try {
            driver.findElement(By.xpath(xpath));
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

    private static String getUrlContents(String theUrl) {
        StringBuilder content = new StringBuilder();

        // many of these calls can throw exceptions, so i've just
        // wrapped them all in one try/catch statement.
        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString().replace("OK|", "");
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

    private void buttonChosefoderActionPerformed2(java.awt.event.ActionEvent evt) {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //    
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            stringUrlSave = chooser.getSelectedFile().toString();
            linkSaveFile1.setText(stringUrlSave+"\\");

        } else {
            System.out.println("No Selection ");
        }

    }

    private void buttonChosefoderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChosefoderActionPerformed
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //    
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            stringUrlSave = chooser.getSelectedFile().toString();
            linkSaveFile.setText(stringUrlSave + "\\");

        } else {
            System.out.println("No Selection ");
        }

    }//GEN-LAST:event_buttonChosefoderActionPerformed

    private void linkSaveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkSaveFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_linkSaveFileActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void widthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_widthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_widthActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void linkSaveFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkSaveFile1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_linkSaveFile1ActionPerformed

    private void buttonChosefoder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChosefoder1ActionPerformed
        buttonChosefoderActionPerformed2(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_buttonChosefoder1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                RESIZEMERCH abc = new RESIZEMERCH();
                abc.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.out.println("WindowClosingDemo.windowClosing");

                    }

                    @Override
                    public void windowOpened(WindowEvent e) {

                    }
                });
                abc.setVisible(true);
            }
        });

    }

    public static String cutLink(String url) {
        String urlresturn = "";
        try {

            String a = url;
            int dem = 0;
            for (int index = a.indexOf("/");
                    index >= 0;
                    index = a.indexOf("/", index + 1)) {
                dem++;
                if (dem == 3) {

                    urlresturn = "https://besthqwallpapers.com/img/original" + a.substring(index);

                    break;
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(RESIZEMERCH.class.getName()).log(Level.SEVERE, null, ex);
        }
        return urlresturn;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea abcxyz;
    private javax.swing.JButton buttonChosefoder;
    private javax.swing.JButton buttonChosefoder1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JCheckBox checkresize;
    private javax.swing.JTextField hight;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField linkSaveFile;
    private javax.swing.JTextField linkSaveFile1;
    private javax.swing.JLabel result;
    private javax.swing.JTextField tileresize;
    private javax.swing.JTextField width;
    // End of variables declaration//GEN-END:variables
}
