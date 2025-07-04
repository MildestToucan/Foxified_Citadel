package net.gauntrecluse.foxified_citadel;

import net.gauntrecluse.foxified_citadel.config.CommonConfig;

import java.util.Calendar;
import java.util.Date;

public class CitadelConstants {
    public static final boolean REMAPREFS = true;
    public static final boolean DEBUG = false;

    private static boolean initDate = false;

    private static boolean aprilFools = false;

    /**
     * Added in FOX for debugging and configuration purposes.
     */
    public static boolean isForcingAprilFools;

    /**
     * @return Whether April Fools content should display. If {@code isForcingAprilFools} is true, then calendar check is bypassed to return true. <br>
     * Otherwise, returns true if both {@code isAprilFoolsEnabled} is enabled and calendar check shows it's April Fools.
     */
    public static boolean getAprilFools(){
        if(!isForcingAprilFools) {
            if(!initDate){
                initDate = true;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                aprilFools = calendar.get(Calendar.MONTH) + 1 == 4 && calendar.get(Calendar.DATE) == 1;
            }
            return aprilFools && CommonConfig.isAprilFoolsEnabled;
        }
        return true; //Returns true if configuration forces april fools content to be enabled.
    }

    public static boolean debugShaders(){
        return false;
    }
}
