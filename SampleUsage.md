# Introduction #

Sample usage of the library within an Android project


# Details #

Open Image file as a byte array object:

```
String path = "/sdcard/dicom.dcm";
File imagefile = new File(path); 
byte[] data = new byte[(int) imagefile.length()];
FileInputStream fis = new FileInputStream(imagefile);
fis.read(data);
fis.close();
```

Create a DicomReader object with the given data array (data[.md](.md))

```
DicomReader DR = new DicomReader(data) ;
//Retrieve the bitmap from the DicomReader;
Bitmap sourceImage = DR.getImage();
```


Retrieve the header info from the DicomReader

```
String [] info = new String[16];
info = DR.getInfos();
```

Print some header data to LogCat
```
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

```