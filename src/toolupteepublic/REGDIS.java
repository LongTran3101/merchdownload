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
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
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
import static toolupteepublic.c.Key;
import static toolupteepublic.downloadanh1.isElementXpath;

/**
 *
 * @author me
 */
public class REGDIS extends javax.swing.JFrame {

    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";
    /**
     *
     * Creates new form downloadanh
     */
    JFileChooser chooser;
    String choosertitle;
    String stringUrlSave;
    static String Key;

    public REGDIS() {

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
        jLabel1 = new javax.swing.JLabel();
        textURLForder = new javax.swing.JTextField();
        theolink = new javax.swing.JRadioButton();
        theofile = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        makey = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        keyApi = new javax.swing.JTextField();
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
        theopage = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        tagex = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        soluongDS = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        tileresize = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();

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

        linkSaveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkSaveFileActionPerformed(evt);
            }
        });

        abcxyz.setColumns(20);
        abcxyz.setRows(5);
        jScrollPane1.setViewportView(abcxyz);

        jLabel1.setText("Link áo");

        textURLForder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textURLForderActionPerformed(evt);
            }
        });

        buttonGroup1.add(theolink);
        theolink.setSelected(true);
        theolink.setText("Theo Link");
        theolink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                theolinkActionPerformed(evt);
            }
        });

        buttonGroup1.add(theofile);
        theofile.setText("Nhiều link");

        jButton2.setText("tạo key");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        makey.setColumns(20);
        makey.setRows(5);
        jScrollPane2.setViewportView(makey);

        jLabel3.setText("Key");

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

        checkresize.setText("resize");

        buttonGroup1.add(theopage);
        theopage.setText("Theo page");

        jLabel6.setText("key word cần lọc");

        tagex.setText("to,or,the,and,is,in");
        tagex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tagexActionPerformed(evt);
            }
        });

        jLabel7.setText("Số lượng DS");

        soluongDS.setText("2000");

        buttonGroup2.add(jRadioButton1);
        jRadioButton1.setText("Link profile");
        jRadioButton1.setToolTipText("");

        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("ten profile");

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

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(soluongDS))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(theolink)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(theofile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(width)))
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonChosefoder))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(77, 77, 77))
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.Alignment.LEADING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textURLForder)
                            .addComponent(keyApi)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tagex)
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(theopage))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jRadioButton3)
                                .addGap(26, 26, 26)
                                .addComponent(jRadioButton4)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addGap(0, 98, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(282, 282, 282)
                                .addComponent(linkSaveFile)))
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tileresize)
                        .addGap(518, 518, 518))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(keyApi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(textURLForder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRadioButton2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(tileresize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonChosefoder, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linkSaveFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(theolink)
                    .addComponent(theofile)
                    .addComponent(theopage))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(result))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(soluongDS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(width, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkresize))))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tagex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            Process p = Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
            p.waitFor();
            Runtime.getRuntime().exec("taskkill /F /IM CHROME.exe");
            p.waitFor();
            ProtectionDomain pd = REGDIS.class.getProtectionDomain();
            CodeSource cs = pd.getCodeSource();
            URL location = cs.getLocation();

            File directory4 = new File(location.getPath());
            String PathLocal = directory4.getParentFile().getPath().replace("%20", " ");
            File currentDir = new File("");
            System.out.println(currentDir.getAbsolutePath());

            File directory2 = new File(PathLocal + "./chromedriver.exe");
            String chromeDriverPath = directory2.getPath();
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            String chromeProfilePath = textURLForder.getText();
            ChromeOptions chromeProfile = new ChromeOptions();
            chromeProfile.setBinary(PathLocal + ".\\GoogleChromePortable\\App\\Chrome-bin\\chrome.exe");

            chromeProfile.addArguments("--user-data-dir=ChromeProfile");
            chromeProfile.addArguments("--silent");
            chromeProfile.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36");
            chromeProfile.addArguments("--disable-blink-features");
            chromeProfile.addArguments("--disable-blink-features=AutomationControlled");
            chromeProfile.addArguments("--disable-notifications");
            chromeProfile.setExperimentalOption("useAutomationExtension", false);
            chromeProfile.setExperimentalOption("excludeSwitches",
                    Collections.singletonList("enable-automation"));

            ChromeDriver driver = new ChromeDriver(chromeProfile);
            System.out.println("1");
        File file = new File(PathLocal + "./accreg.txt");
       
            Scanner input = new Scanner(file);
             System.out.println("2");
            while (input.hasNextLine()) {
                try {
                     String Line = input.nextLine();
                String fisrName = Line.split("\\|")[1];
                String lastName = Line.split("\\|")[2];
                String mail = Line.split("\\|")[0];
                driver.get("https://displate.com/auth/signin/register-shop");
                Thread.sleep(20000);
                driver.findElement(By.className("captcha-solver")).click();
                Thread.sleep(2000);
                WebElement des1 = driver.findElement(By.cssSelector("#firstname"));
                des1.sendKeys(Keys.CONTROL + "a");
                des1.sendKeys(Keys.DELETE);
                des1.sendKeys(fisrName);
                Thread.sleep(2000);
                des1 = driver.findElement(By.cssSelector("#lastname"));
                des1.sendKeys(Keys.CONTROL + "a");
                des1.sendKeys(Keys.DELETE);
                des1.sendKeys(lastName);
                Thread.sleep(2000);
                des1 = driver.findElement(By.cssSelector("#email"));
                des1.sendKeys(Keys.CONTROL + "a");
                des1.sendKeys(Keys.DELETE);
                des1.sendKeys(mail);
                Thread.sleep(2000);
                des1 = driver.findElement(By.cssSelector("#password"));
                des1.sendKeys(Keys.CONTROL + "a");
                des1.sendKeys(Keys.DELETE);
                des1.sendKeys("Huongdung@123");
                JavascriptExecutor jse = (JavascriptExecutor) driver;

                des1 = driver.findElement(By.cssSelector("#accept_everything"));
                jse.executeScript("arguments[0].click();", des1);
                Thread.sleep(80000);

                driver.findElement(By.cssSelector(".js-create-account")).click();
                Thread.sleep(20000);
               

                if (driver.getCurrentUrl().contains("onboarding")) {
                    String filename = PathLocal + "./acc.txt";
                    FileWriter fw2 = new FileWriter(filename, true); //the true will append the new data
                    fw2.write(mail+"\n");//appends the string to the file
                    fw2.close();
                    System.out.println("done: -- " + mail);
                } else {
                    System.out.println("Loi -- " + mail);
                }
                } catch (Exception e) {
                    continue;
                }
               

            }

            //Thread.sleep(2000);
            //driver.close();
            // Logout
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

    public static String callAPIPost(String completeUrl, String body) {
        try {
            int CONNECTION_TIMEOUT_MS = 60000;
            int LATENT_CONNECTION_TIMEOUT_MS = 6000000;

            CloseableHttpClient client = HttpClientBuilder.create().build();
            RequestConfig config = RequestConfig.custom().setConnectTimeout(CONNECTION_TIMEOUT_MS)
                    .setConnectionRequestTimeout(CONNECTION_TIMEOUT_MS).setSocketTimeout(CONNECTION_TIMEOUT_MS).build();
            HttpPost httppost = new HttpPost(completeUrl);
            try {
                httppost.setHeader("Content-Type", "application/json;charset=UTF-8");
                httppost.setHeader("Authorization", "Bearer ");
                httppost.setConfig(config);
                StringEntity stringEntity = new StringEntity(body, "UTF-8");
                httppost.getRequestLine();
                httppost.setEntity(stringEntity);
            } catch (Exception e) {

            }
            CloseableHttpResponse response = client.execute(httppost);
            if (response.getStatusLine().getStatusCode() >= 300) {
                throw new IOException(String.format("failure - received a %d for %s.",
                        response.getStatusLine().getStatusCode(), httppost.getURI().toString()));
            }
            HttpEntity entity = response.getEntity();

            // StringWriter writer = new StringWriter();
            // IOUtils.copy(entity.getContent(), writer);
            return EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
        }

        return null;
    }

    public static Workbook wirteExcel(List<image1> listimage)
            throws IOException {

        String[] columns = {"Name", "title", "Brand New", "des1", "des2", "mau", "Kieu ao", "Main Tag", "tag", "Drand", "link"};

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Contacts");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Other rows and cells with contacts data
        int rowNum = 1;
        int i = 0;
        for (image1 image : listimage) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(image.getName());
            row.createCell(1).setCellValue(image.getAlt());
            row.createCell(2).setCellValue(image.getBrandnew());
            row.createCell(3).setCellValue(image.getDes());
            row.createCell(4).setCellValue(image.getTag());
            row.createCell(5).setCellValue(image.getMau());
            row.createCell(6).setCellValue(image.getKieuao());
            row.createCell(7).setCellValue(image.getMaintag());
            row.createCell(8).setCellValue(image.getTagchuan());
            row.createCell(9).setCellValue(image.getBrand());
            row.createCell(10).setCellValue(image.getUrl());

            i++;
        }

        return workbook;
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

    protected static boolean isElementBy(By by, ChromeDriver driver) {
        try {
            driver.findElement(by);
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

    private void textURLForderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textURLForderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textURLForderActionPerformed

    private void theolinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_theolinkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_theolinkActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            ProtectionDomain pd = REGDIS.class.getProtectionDomain();
            CodeSource cs = pd.getCodeSource();
            URL location = cs.getLocation();

            File directory4 = new File(location.getPath());
            String PathLocal = directory4.getParentFile().getPath();
            ThreadLocalRandom random = ThreadLocalRandom.current();

            int rand = random.nextInt(1, 999999999);

            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            String adip = "";
            List<String> adress = new ArrayList<>();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface ni = networkInterfaces.nextElement();
                byte[] hardwareAddress = ni.getHardwareAddress();
                if (hardwareAddress != null) {
                    String[] hexadecimalFormat = new String[hardwareAddress.length];
                    for (int i = 0; i < hardwareAddress.length; i++) {
                        hexadecimalFormat[i] = String.format("%02X", hardwareAddress[i]);
                    }
                    adress.add(String.join("-", hexadecimalFormat));
                    //System.out.println(String.join("-", hexadecimalFormat));
                }
            }

            adip = String.join(",", adress);
            //System.out.println("http://donthan.info/APIRED/index.php?key=" + rand + "&adress=" + adip + "&method=create");
            String checkKeyUrl = "http://45.77.65.193:8080/insert";
            subMitClass submitKey = new subMitClass();
            submitKey.setKey(String.valueOf(rand));
            submitKey.setAddress(adip);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String bodyKey = gson.toJson(submitKey);

            String respKey = callAPIPost(checkKeyUrl, bodyKey);
            subMitClass submitRPKey = new subMitClass();
            if (respKey != null && !respKey.isEmpty()) {
                submitRPKey = gson.fromJson(respKey, subMitClass.class);
                if (submitRPKey.getKey().equalsIgnoreCase("00")) {
                    makey.setText(String.valueOf(rand));
                    keyApi.setText(String.valueOf(rand));
                    Key = String.valueOf(rand);

                }

            }

        } catch (Exception e) {
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void tagexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tagexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tagexActionPerformed

    private void widthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_widthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_widthActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {
            Process p = Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
            p.waitFor();
            Runtime.getRuntime().exec("taskkill /F /IM CHROME.exe");
            p.waitFor();
            ProtectionDomain pd = REGDIS.class.getProtectionDomain();
            CodeSource cs = pd.getCodeSource();
            URL location = cs.getLocation();

            File directory4 = new File(location.getPath());
            String PathLocal = directory4.getParentFile().getPath().replace("%20", " ");
            File currentDir = new File("");
            System.out.println(currentDir.getAbsolutePath());

            File directory2 = new File(PathLocal + "./chromedriver.exe");
            String chromeDriverPath = directory2.getPath();
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            String chromeProfilePath = textURLForder.getText();
            ChromeOptions chromeProfile = new ChromeOptions();
            chromeProfile.setBinary(PathLocal + ".\\GoogleChromePortable\\App\\Chrome-bin\\chrome.exe");

            chromeProfile.addArguments("--user-data-dir=ChromeProfile");
            chromeProfile.addArguments("--silent");
            chromeProfile.addArguments("--user-agent=Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.63 Safari/537.36");
            chromeProfile.addArguments("--disable-blink-features");
            chromeProfile.addArguments("--disable-blink-features=AutomationControlled");
            chromeProfile.addArguments("--disable-notifications");
            chromeProfile.setExperimentalOption("useAutomationExtension", false);
            chromeProfile.setExperimentalOption("excludeSwitches",
                    Collections.singletonList("enable-automation"));

// Here you specify the actual profile folder (Profile 2)
            //chromeProfile.addArguments("--profile-directory=" + textURL.getText());
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeProfile);
            //ChromeDriverService driverService = ChromeDriverService.createDefaultService().;
            //driverService. = true;

            ChromeDriver driver = new ChromeDriver(chromeProfile);
        } catch (Exception ex1) {
           
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                REGDIS abc = new REGDIS();
                abc.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.out.println("WindowClosingDemo.windowClosing");

                    }

                    @Override
                    public void windowOpened(WindowEvent e) {

                        try {
                            ProtectionDomain pd = MainTestChorme.class.getProtectionDomain();
                            CodeSource cs = pd.getCodeSource();
                            URL location = cs.getLocation();
                            File directory4 = new File(location.getPath());
                            String PathLocal = directory4.getParentFile().getPath();
                            String filename = PathLocal + "./key.txt";
                            File myObj = new File(filename);
                            Scanner myReader = new Scanner(myObj);

                            while (myReader.hasNextLine()) {
                                Key = myReader.nextLine();

                            }
                            myReader.close();
                            if (!Key.isEmpty()) {
                                abc.keyApi.setText(Key);
                            }
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(REGDIS.class.getName()).log(Level.SEVERE, null, ex);
                        }

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
            Logger.getLogger(REGDIS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return urlresturn;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea abcxyz;
    private javax.swing.JButton buttonChosefoder;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JCheckBox checkresize;
    private javax.swing.JTextField hight;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField keyApi;
    private javax.swing.JTextField linkSaveFile;
    private javax.swing.JTextArea makey;
    private javax.swing.JLabel result;
    private javax.swing.JTextField soluongDS;
    private javax.swing.JTextField tagex;
    private javax.swing.JTextField textURLForder;
    private javax.swing.JRadioButton theofile;
    private javax.swing.JRadioButton theolink;
    private javax.swing.JRadioButton theopage;
    private javax.swing.JTextField tileresize;
    private javax.swing.JTextField width;
    // End of variables declaration//GEN-END:variables
}
