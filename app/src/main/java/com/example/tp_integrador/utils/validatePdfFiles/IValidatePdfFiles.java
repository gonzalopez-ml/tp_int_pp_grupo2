package com.example.tp_integrador.utils.validatePdfFiles;

import android.content.Context;
import android.net.Uri;

public interface IValidatePdfFiles {
    String processSelectedPdfFile(Context ctx, Uri uri);
}
