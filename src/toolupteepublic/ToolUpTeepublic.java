/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toolupteepublic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author me
 */
public class ToolUpTeepublic {

    /**
     * @param args the command line arguments
     */
     public static int randomInt(int min,int max)
     {
         ThreadLocalRandom random = ThreadLocalRandom.current();
         int rand = random.nextInt(min, max);
         return rand;
     }
    public static void main(String[] args) {
        

        Date dt = new Date();
		      Calendar c = Calendar.getInstance();
		c.setTime(dt);
		boolean monday = c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
		c.add(Calendar.DATE, -2);
		dt = c.getTime();
		      SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                System.out.println(df.format(dt));
    }

}
