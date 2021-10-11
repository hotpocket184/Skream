package us.happypockets.skream.util;

public class ScrubNPCSound {
    public static String getSound(String exprs){
        return exprs.replace("_", ".").toLowerCase();
    }
}
