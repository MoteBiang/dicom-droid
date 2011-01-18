package dicom.droid.example;

/*********
 * Readerexample:
 * DICOM Reader example Activity for parsing and reading a DICOM Image File
 * Copyright 2011 Charalampos Doukas, All rights reserved.
 * Android library for viewing and decoding DICOM images
 * 
 * http://code.google.com/p/dicom-droid/
 * 
 * 
 * This program is free software: you can redistribute it and/or modify 
  it under the terms of the GNU General Public License as published by 
  the Free Software Foundation, either version 3 of the License, or 
  (at your option) any later version. 

  This program is distributed in the hope that it will be useful, 
  but WITHOUT ANY WARRANTY; without even the implied warranty of 
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
  GNU General Public License for more details. 

  You should have received a copy of the GNU General Public License 
  along with this program. If not, see <http://www.gnu.org/licenses/>. 
 *********/

import java.io.File;
import java.io.FileInputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import dicom.droid.*;

public class ReaderExample extends Activity{
	
	DICOMView viewer;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        viewer = new DICOMView(this);
        setContentView(viewer);
	}
	
}

class DICOMView extends View{
	
	Context context;
	private Paint tmpPaint = new Paint(Paint.ANTI_ALIAS_FLAG); 
	//set the DICOM Image file path here
	String path = "/sdcard/dicom.dcm";
	
	public DICOMView(Context context){
		super(context);
		this.context = context;
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		try {
			//Read the imagefile into a byte array (data[])
	         File imagefile = new File(path); 
	         byte[] data = new byte[(int) imagefile.length()];
	         FileInputStream fis = new FileInputStream(imagefile);
	         fis.read(data);
	         fis.close();
	          
	         //Create a DicomReader with the given data array (data[])
	         DicomReader DR = new DicomReader(data) ;
	         //Retrieve the bitmap from the DicomReader
	         Bitmap sourceImage = DR.getImage();
	         canvas.drawBitmap( sourceImage, null , new Rect(0,0,getWidth(),getHeight()),tmpPaint);
	    	
	         //Retrieve the header info from the DicomReader
	         String [] info = new String[16];
	         info = DR.getInfos();
	         
	         //----- PRINT some header data to LogCat
	         Log.v("Patient 's name ", info[0]);
	         Log.v("Patient 's ID ", info[1]);
	         Log.v("Patient 's birthdate ", info[2]);
	         Log.v("Patient 's sex ", info[3]);
	         Log.v("Study Date", info[4]);
	         Log.v("Modality", info[5]);
	         Log.v("Manufacturer", info[6]);
	         Log.v("Number of frames", info[7]);
	         Log.v("Width", info[8]);
	         Log.v("Height", info[9]);
	         Log.v("Bits allocated", info[10]);
	         Log.v("Bits stored", info[11]);
	         Log.v("Sample per pixels", info[12]);
	         Log.v("Physician", info[13]);
	         Log.v("Institution", info[14]);
	         Log.v("Transfert syntax UID", info[15]);
	 		
	         //-----
	         
	         
	         new AlertDialog.Builder(context)
	         .setTitle("DICOM Header Information")
	         .setMessage(info[0] + "\n"+ info[1]+"\n"+ info[2]+"\n"+info[3]+"\n"+info[4]+"\n"+info[5])
	         .setNeutralButton("Ok",
	         new DialogInterface.OnClickListener() {
	         public void onClick(DialogInterface dialog,
	         int which) {
	         }
	         }).show();
	    	
	    	}
	    	catch (Exception ex) {
	    		Log.e("ERROR",ex.toString());
	    	}
		
	}
	
	
	
}


