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

public class ValidatePdfFiles implements IValidatePdfFiles {

    @Override
    public String processSelectedPdfFile(Context ctx, Uri uri) {
        if (isValidPdfFile(uri, ctx)) {
            return savePdfFileToStorage(uri, ctx);
        } else {
            return null;
        }
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
