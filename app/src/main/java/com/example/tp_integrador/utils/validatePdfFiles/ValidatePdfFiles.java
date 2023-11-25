package com.example.tp_integrador.utils.validatePdfFiles;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ValidatePdfFiles implements IValidatePdfFiles {

    @Override
    public String processSelectedPdfFile(Context ctx, Uri uri) {
        if (isValidPdfFile(uri, ctx)) {
            String localFilePath = savePdfFileToStorage(uri, ctx);

            if (localFilePath != null) {
                // Ahora, realiza la carga del archivo al servidor externo y obtén la dirección del servidor
                String serverFilePath = uploadPdfFileToFirebaseStorage(localFilePath);

                // Devuelve la dirección del servidor
                return serverFilePath;
            }
        }

        return null;
    }

    private String uploadPdfFileToFirebaseStorage(String localFilePath) {
        // Obtén una referencia a tu Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Define la referencia del archivo en el almacenamiento de Firebase
        StorageReference pdfRef = storageRef.child("archivos_pdf/" + System.currentTimeMillis() + "_archivo.pdf");

        // Sube el archivo PDF
        UploadTask uploadTask = pdfRef.putFile(Uri.fromFile(new File(localFilePath)));

        // Maneja el éxito o el fracaso de la carga
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            // La carga fue exitosa
            // Obtén la URL de descarga del archivo
            Task<Uri> downloadUrlTask = pdfRef.getDownloadUrl();
            downloadUrlTask.addOnSuccessListener(uri -> {
                String downloadUrl = uri.toString();
                // Aquí puedes usar la URL de descarga según tus necesidades
                // Por ejemplo, puedes almacenar esta URL en tu base de datos
                // junto con otros detalles del archivo subido.
                Log.d("Upload", "Archivo subido con éxito. URL de descarga: " + downloadUrl);
            });
        }).addOnFailureListener(exception -> {
            // La carga falló
            Log.e("Upload", "Error al subir el archivo: " + exception.getMessage());
        });
        return downloadUrl;
    }





    private String savePdfFileToStorage(Uri fileUri, Context ctx) {
        String fileName = getFileName(fileUri, ctx);
        String destinationPath = ctx.getExternalFilesDir(null).getAbsolutePath() + File.separator + fileName;

        try {
            InputStream inputStream = ctx.getContentResolver().openInputStream(fileUri);
            OutputStream outputStream = new FileOutputStream(destinationPath);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

            return destinationPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isValidPdfFile(Uri uri, Context ctx) {
        if (uri == null) {
            return false;
        }

        String fileName = getFileName(uri, ctx);
        return fileName.toLowerCase().endsWith(".pdf");
    }


    private String getFileName(Uri uri, Context ctx) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = ctx.getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    result = cursor.getString(index);
                }
            }
        }
        return result != null ? result : "Archivo no encontrado";
    }
}
