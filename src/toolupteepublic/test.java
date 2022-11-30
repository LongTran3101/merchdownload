import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class test {
//     private static final Color backColor1 = new Color(148,148,148);
//     private static final Color backColor2= new Color(149,149,149);
     
     private static final Color backColor4= new Color(151,151,151);
      private static final Color backColor5 = new Color(94,94,94);
//     private static final Color backColor26= new Color(153,153,153);
//      private static final Color backColor36= new Color(154,154,155);
//       private static final Color backColor37 = new Color(154,154,156);
//        private static final Color backColor38 = new Color(154,154,157);
//         private static final Color backColor39 = new Color(154,154,158);
    private static final int THRESHOLD = 35;
    private static final int TRANSPARENT = 0;  // 0x00000000;

    static File base  = new File("C:\\Users\\me\\Desktop\\tpl\\1,width=800,height=800,appearanceId=2,backgroundColor=000000,noPt=true.png");

    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File("C:\\Users\\me\\Desktop\\tpl\\1,width=400,height=400,appearanceId=2,backgroundColor=,noPt=true (1).png"));
        final int w = image.getWidth();
        final int h = image.getHeight();
        BufferedImage scaledImage = new BufferedImage((w * 2), (h * 2), BufferedImage.TYPE_INT_ARGB);
        final AffineTransform at = AffineTransform.getScaleInstance(2.0, 2.0);
        final AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        scaledImage = ato.filter(image, scaledImage);
           File file2 = new File("C:\\Users\\me\\Desktop\\tpl\\123.png");
            ImageIO.write(scaledImage, "png", file2);
        
    }
}