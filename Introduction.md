# Introduction #
The dicom-droid library for viewing DICOM Image files in Android

Version: 1.0

# Details #

This library (DicomDroid.jar) can be included as an external library in Android projects and provides the functionality of exporting DICOM images as Bitmap (android.graphics.Bitmap) objects and extracting DICOM header information.

Please check the Source for an example on how to use the library. A test dicom image is included for trial. Make sure you edit the image path properly.

Comments:
Currently, only uncompressed images are supported.
Might be slow on old droid phones (e.g., 10 seconds to parse image)