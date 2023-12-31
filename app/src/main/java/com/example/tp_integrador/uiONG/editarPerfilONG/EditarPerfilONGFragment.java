package com.example.tp_integrador.uiONG.editarPerfilONG;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.RequestQueue;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tp_integrador.R;
import com.example.tp_integrador.data.domain.TipoUser;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.uiVoluntarios.sharedData.SharedViewModel;
import com.example.tp_integrador.utils.validarCamposVacios.IValidateInputs;
import com.example.tp_integrador.utils.validarUsuario.IValidateMail;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import android.util.Log;

import dagger.hilt.android.AndroidEntryPoint;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

@AndroidEntryPoint
public class EditarPerfilONGFragment extends Fragment {

    @Inject
    IValidateInputs validateInputs;

    @Inject
    IValidateMail validateMail;

    private EditarPerfilONGViewModel mViewModel;

    private EditText editTextName;
    private EditText editTextDescripcion;
    private EditText editTextUbicacion;
    private EditText editTextTelefono;
    private EditText editTextMail;

    private SharedViewModel sharedViewModel;

    private EditText editPassword;

    private Button btnEditarOng;

    private Button btnCancelarOng;

    private Integer idUser;
    private int idType;

    private String logo;

    Bitmap bitmap;
    private ImageView imageView;

    public static EditarPerfilONGFragment newInstance() {
        return new EditarPerfilONGFragment();
    }

    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {

                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        if (uri != null) {
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                                imageView.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_editar_perfil_o_n_g, container, false);

        mViewModel = new ViewModelProvider(this).get(EditarPerfilONGViewModel.class);

        imageView = rootView.findViewById(R.id.clicktoUploadImg);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent);
            }
        });

        editTextName = rootView.findViewById(R.id.txtOngName);
        editTextDescripcion = rootView.findViewById(R.id.txtOngDescription);
        editTextUbicacion = rootView.findViewById(R.id.txtOngLocation);
        editTextMail = rootView.findViewById(R.id.txtOngMail);
        editTextTelefono = rootView.findViewById(R.id.txtOngPhone);
        editPassword = rootView.findViewById(R.id.txtOngPassword);

        btnEditarOng = rootView.findViewById(R.id.btnOngGuardar);
        btnCancelarOng = rootView.findViewById(R.id.btnOngCancelar);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        Ong ongLogueada = sharedViewModel.getOng().getValue();

        mViewModel.getOngLiveData(ongLogueada.getIdOng()).observe(getViewLifecycleOwner(), ong -> {

            if (ong != null) {
                idUser = ong.getUsuario().getIdUser();
                idType = ong.getUsuario().getTipoUser().getId();
                editTextName.setText(ong.getName());
                editTextDescripcion.setText(ong.getDescription());
                editTextUbicacion.setText(ong.getLocation());
                editTextMail.setText(ong.getUsuario().getMail());
                editTextTelefono.setText(ong.getPhone());
                editPassword.setText(ong.getUsuario().getPassword());

                logo = "https://btw.com.ar/app/"+ong.getLogo();
                if (!ong.getLogo().isEmpty())
                {
                   logo = "https://btw.com.ar/app/"+ong.getLogo();

                   //imageView.setImageBitmap(bitmap);
               } else {
                   logo = "https://btw.com.ar/app/images/upload.jpg";
               }

                Picasso.get()
                        .load(logo)
                        .error(R.drawable.logoapp) // imagen de error
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                Log.d("Aviso","cargo OK");
                            }

                            @Override
                            public void onError(Exception e) {
                                Log.d("Aviso","cargo NO");
                                e.printStackTrace();
                            }
                        });
                /* ********** FIN LOAD LOGO *************/
            }
        });


        btnCancelarOng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Aviso","click boton cancelar");
                requireActivity().onBackPressed();
            }
        });

        btnEditarOng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Aviso","Pasa Frame 5");

                String name = editTextName.getText().toString();
                String descripcion = editTextDescripcion.getText().toString();
                String ubicacion = editTextUbicacion.getText().toString();
                String mail = editTextMail.getText().toString();
                String telefono = editTextTelefono.getText().toString();
                String contraseña = editPassword.getText().toString();

                Boolean isValidateInputs = validateInputsOng(name, descripcion, ubicacion, mail, telefono, contraseña);

                Log.d("Aviso","boolen 1:"+isValidateInputs);

                if (!isValidateInputs) {
                    Toast.makeText(requireContext(), "Por favor verificar los campos", Toast.LENGTH_SHORT).show();
                } else {
                    TipoUser tipoUser = new TipoUser(idType);
                    Usuario usuario = new Usuario(mail, contraseña, tipoUser);
                    usuario.setIdUser(idUser);
                    Ong ong = new Ong(1, name, descripcion, "", telefono, mail, ubicacion);
                    ong.setUsuario(usuario);

                    Boolean isOngSave = mViewModel.saveOngLiveData(ong);

                    if (isOngSave) {

                        /* **** 840 LOAD **************/
                        loadImg();
                        /* ************* FIN LOAD ****/

                       Toast.makeText(requireContext(), "Se editaron los datos con éxito!", Toast.LENGTH_SHORT).show();
                        //reloadFragment();
                    } else {
                        Toast.makeText(requireContext(), "Error al guardar la ONG, intente nuevamente", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return rootView;
    }

    private void reloadFragment() {
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_editar_perfil_ong, new EditarPerfilONGFragment());
        ft.commit();
    }

// ****** 840 COMIENZA EL UPLOAD DE LA IMAGEN ***************
    private void loadImg() {

        ByteArrayOutputStream byteArrayOutputStream;
        byteArrayOutputStream = new ByteArrayOutputStream();
        final Boolean[] actionLogo = {null};

        if (bitmap==null)
        {

            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                bitmap = bitmapDrawable.getBitmap();

            }
        }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            final String base64Image = Base64.encodeToString(bytes,Base64.DEFAULT);

            RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

            String url ="https://btw.com.ar/app/upload.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("success"))
                            {
                                //Toast.makeText(requireContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
                                Log.d("Aviso","Pasa luego d actionLogo[0] = true;");
                                actionLogo[0] = true;
                            } else  Toast.makeText(requireContext(), "Error Image Uploaded", Toast.LENGTH_SHORT).show();

                            actionLogo[0] = false;
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(requireContext(), error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    actionLogo[0] = false;
                }
            }){
                protected Map<String, String> getParams(){
                    Map<String, String> paramV = new HashMap<>();

                    paramV.put("image", base64Image);
                    paramV.put("usuario",String.valueOf(idUser));
                   // paramV.put("fragment","O");
                   // paramV.put("action","U");

                    return paramV;
                }
            };
            queue.add(stringRequest);

    }
    // ******* FINALIZA UPLOAD IMAGEN ****************

    private Boolean validateInputsOng(String name, String descripcion, String ubicacion, String mail, String telefono, String contraseña) {
        List<String> inputs = Arrays.asList(name, descripcion, ubicacion, mail, telefono, contraseña);

        Boolean isValidateInputs = validateInputs.apply(inputs);
        Log.d("Aviso","boolen 2:"+isValidateInputs);

        if (isValidateInputs) {
            return validateMail.validate(mail, contraseña);
        }
        return false;
    }
}