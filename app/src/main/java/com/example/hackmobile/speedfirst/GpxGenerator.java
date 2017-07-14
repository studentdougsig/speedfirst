package com.example.hackmobile.speedfirst;

import android.location.Location;
import android.text.format.DateFormat;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dougsigelbaum on 7/10/16.
 */
public class GpxGenerator {

        public static void writePath(File file, String n, List<Location> points) {

//            String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?><gpx xmlns=\"http://www.topografix.com/GPX/1/1\" creator=\"MapSource 6.15.5\" version=\"1.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd\"><trk>\n";
//            String name = "<name>" + n + "</name><trkseg>\n";
//
//            String segments = "";
//            DateFormat df;
////            df = new DateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
//            for (Location l : points) {
//                segments += "<trkpt lat=\"" + l.getLatitude() + "\" lon=\"" + l.getLongitude() + "\"><time>" + df.format(new Date(l.getTime())) + "</time></trkpt>\n";
//            }
//
//            String footer = "</trkseg></trk></gpx>";
//
//            try {
//                FileWriter writer = new FileWriter(file, false);
//                writer.append(header);
//                writer.append(name);
//                writer.append(segments);
//                writer.append(footer);
//                writer.flush();
//                writer.close();
//            } catch (IOException e) {
//                Log.e("GpxGenerator", "Error Writing Path",e);
//            }
        }
}
