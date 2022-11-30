/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toolupteepublic;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

/**
 *
 * @author me
 */

public class Main {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://image.spreadshirtmedia.com/image-server/v1/mp/compositions/T347A2MPA2978PT17X4Y12D12402314FS8899CxFFFFFF:xD31818:xFFFFFF/views/1,width=400,height=400,appearanceId=2,backgroundColor=,noPt=true");
                    //System.out.println(FilenameUtils.getBaseName(url.getPath())); // -> file
                    InputStream in = new BufferedInputStream(url.openStream());
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte[] buf = new byte[2048];
                    int n = 0;
                    while (-1 != (n = in.read(buf))) {
                        out.write(buf, 0, n);
                    }
                    out.close();
                    in.close();
                    byte[] response = out.toByteArray();
                    
                        int newHeight =5400;
                        int newWidth =4500;
                        int newHeightresize = 5400;
                        int newWidthresize = 4500;
                        // PNG supports transparency
                        // int type = config.formatName.equals("png")?BufferedImage.TYPE_INT_ARGB:BufferedImage.TYPE_INT_RGB;
                        ByteArrayInputStream bais = new ByteArrayInputStream(response);

                        BufferedImage inputimage = ImageIO.read(bais);
                        BufferedImage outputImage = trimImage(inputimage);

                        Scalr.Mode mode = Scalr.Mode.FIT_TO_HEIGHT;
                        BufferedImage outputImage2 = Scalr.resize(outputImage, Scalr.Method.ULTRA_QUALITY, mode, newWidthresize - 10, newHeightresize - 10, Scalr.OP_ANTIALIAS);
                        //System.out.println(outputImage2.getHeight());
                        if (outputImage2.getWidth() > newWidth) {
                            Scalr.Mode mode2 = Scalr.Mode.FIT_TO_WIDTH;
                            outputImage2 = Scalr.resize(outputImage, Scalr.Method.ULTRA_QUALITY, mode2, newWidthresize - 10, newHeightresize - 10, Scalr.OP_ANTIALIAS);
                            int hightwirte = Math.round((newHeight - outputImage2.getHeight()) / 2);

                            if ((newHeight - outputImage2.getHeight()) > 200) {
                                hightwirte = 200;
                            }
                            int widthwirte = Math.round((newWidth - outputImage2.getWidth()) / 2);
                            int type = BufferedImage.TYPE_INT_ARGB;

                            BufferedImage outputImage4 = new BufferedImage(newWidth, newHeight, type);
                            Graphics2D graphics2D = outputImage4.createGraphics();
                            RenderingHints hints = new RenderingHints(RenderingHints.KEY_INTERPOLATION,
                                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                            hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                            hints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

                            graphics2D.setRenderingHints(hints);

                            graphics2D.drawImage(outputImage2, widthwirte, hightwirte, null);
                            graphics2D.dispose();

                            ImageIO.write(outputImage4, "png", new File("C:\\Users\\me\\Desktop\\tool\\" + "abc.png"));
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

                            ImageIO.write(outputImage4, "png", new File("C:\\Users\\me\\Desktop\\tool\\" + "abc.png"));
                        }

        } catch (Exception e) {
        }
         
                   
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
}
