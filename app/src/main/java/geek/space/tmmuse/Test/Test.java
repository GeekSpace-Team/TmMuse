package geek.space.tmmuse.Test;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import geek.space.tmmuse.Model.Film.MovieTime;

public class Test {
    public static void main(String[] args) {
        String movieTime="9.1.2022(13:00,22:30)*11.1.2022-12.1.2022(14:00,22:30)*";
        splitTime(movieTime);
    }

    public static void splitTime(String movieTime) {
        ArrayList<MovieTime> movieTimes=new ArrayList<>();
        String[] times=movieTime.split("\\*");
        for(String time:times){
            String[] dateTime=time.split("\\(");
            for(int l=0;l<dateTime.length;l++){
                if(l%2==0){
                    String[] myDate=dateTime[l].split("-");
                    for(String md:myDate){
                        String[] myTimes=dateTime[l+1].replace(")","").split(",");
                        ArrayList<String> mt=new ArrayList<>();
                        mt.addAll(Arrays.asList(myTimes));
                        movieTimes.add(new MovieTime(md,mt));
                    }
                }

            }
        }


        for(MovieTime md:movieTimes){
            System.out.println(md.getDate());
            for (String mt:md.getTimes()){
                System.out.println("Times: "+mt);
            }
        }





    }
}
