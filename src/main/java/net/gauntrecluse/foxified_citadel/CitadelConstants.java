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
     * Added in FOX for debugging and configuration purposes. <br>
     * Associated with a config option in {@link CommonConfig}
     */
    public static boolean isForcingAprilFools;

    /**
     * @return Whether April Fools content should display. If {@code isForcingAprilFools} is true, then calendar check is bypassed to return true. <br>
     * Otherwise, returns true if both {@code isAprilFoolsEnabled} is enabled and calendar check shows it's April Fools. <br>
     * Due to the initDate check, it is not very resource intensive to call the method more than once, but if a mod uses this extensively,
     * it may be worthwhile storing the result of this calendar calculation in a variable that is initialized during setup for optimization.
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
        return true;
    }

    public static boolean debugShaders(){
        return false;
    }
}
