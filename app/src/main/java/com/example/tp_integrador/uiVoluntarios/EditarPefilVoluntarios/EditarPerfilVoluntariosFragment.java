package com.example.tp_integrador.uiVoluntarios.EditarPefilVoluntarios;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tp_integrador.LoginActivity;
import com.example.tp_integrador.MainActivityVoluntarios;
import com.example.tp_integrador.R;
import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.TipoUser;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.data.domain.enums.RequestType;
import com.example.tp_integrador.uiRegistro.RegistroVoluntario;
import com.example.tp_integrador.uiVoluntarios.sharedData.SharedViewModel;
import com.example.tp_integrador.utils.validarCamposVacios.IValidateInputs;
import com.example.tp_integrador.utils.validarUsuario.IValidateMail;
import com.example.tp_integrador.utils.validatePdfFiles.IValidatePdfFiles;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import android.util.Log;
@AndroidEntryPoint
public class EditarPerfilVoluntariosFragment extends Fragment {

    @Inject
    IValidateInputs validateInputs;

    @Inject
    IValidateMail validateMail;

    @Inject
    IValidatePdfFiles validatePdfFiles;

    private EditarPerfilVoluntariosViewModel mViewModel;

    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextDNI;
    private EditText editTextSkills;
    private EditText editTextPhone;
    private EditText editTextAvailability;
    private EditText editTextCurriculum;
    private EditText editTextPhoto;
    private EditText editTextMail;
    private EditText editPassword;
    private SharedViewModel sharedViewModel;

    private Button btnEditarVoluntario;
    private Button cancelarEditarVolutario;
    private Button examineCvButton;

    private Integer idUser;
    private int idType;
    Bitmap bitmap;
    private ImageView imageView;
    private String logo;
    private String cvFileName;

    private static final String PDF_TYPE = "application/pdf";

    public static EditarPerfilVoluntariosFragment newInstance() {
        return new EditarPerfilVoluntariosFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_editar_perfil_voluntarios, container, false);

        mViewModel = new ViewModelProvider(this).get(EditarPerfilVoluntariosViewModel.class);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        imageView = rootView.findViewById(R.id.clicktoUploadImg);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent);
            }
        });


        editTextName = rootView.findViewById(R.id.editTextNameVoluntario);
        editTextLastName = rootView.findViewById(R.id.editTextLastNameVoluntario);
        editTextDNI = rootView.findViewById(R.id.editTextDniVoluntario);
        editTextPhone = rootView.findViewById(R.id.editTextPhoneVolntario);
        editTextAvailability = rootView.findViewById(R.id.editTxtDispoVoluntario);
        editTextSkills = rootView.findViewById(R.id.editTxtHabilidadesVoluntario);
        editTextMail = rootView.findViewById(R.id.editTxtMailEditVoluntario);
        editPassword = rootView.findViewById(R.id.editTxtContraVoluntario);
        examineCvButton = rootView.findViewById(R.id.examineEditCvButton);

        btnEditarVoluntario = rootView.findViewById(R.id.btnEditEditarVoluntario);
        cancelarEditarVolutario = rootView.findViewById(R.id.btnCancelarEditVoluntario);

        Voluntario voluntarioLogueado = sharedViewModel.getVoluntarioLiveData().getValue();

        mViewModel.getVoluntarioLiveData(voluntarioLogueado.getIdVoluntario()).observe(getViewLifecycleOwner(), voluntario -> {

            if (voluntario != null) {
                idUser = voluntario.getUsuario().getIdUser();
                idType = voluntario.getUsuario().getTipoUser().getId();
                editTextName.setText(voluntario.getName());
                editTextLastName.setText(voluntario.getLastName());
                editTextDNI.setText(voluntario.getDni());
                editTextSkills.setText(voluntario.getSkills());
                editTextPhone.setText(voluntario.getPhone());
                editTextAvailability.setText(voluntario.getAvailability());
                editTextMail.setText(voluntario.getUsuario().getMail());
                editPassword.setText(voluntario.getUsuario().getPassword());

                logo = "https://btw.com.ar/app/"+voluntario.getPhoto();
                if (!voluntario.getPhoto().isEmpty())
                {
                    logo = "https://btw.com.ar/app/"+voluntario.getPhoto();
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
            }
        });

        examineCvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examineCvFile(view);
            }
        });

        cancelarEditarVolutario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        btnEditarVoluntario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                String lastName = editTextLastName.getText().toString();
                String dni = editTextDNI.getText().toString();
                String skills = editTextSkills.getText().toString();
                String phone = editTextPhone.getText().toString();
                String availability = editTextAvailability.getText().toString();
                String mail = editTextMail.getText().toString();
                String contraseña = editPassword.getText().toString();

                if (cvFileName == null) {
                    cvFileName = voluntarioLogueado.getCv();
                }

                Boolean isValidateInputs = validateInputsVoluntarios(name, lastName, dni, skills, phone, availability, mail, contraseña, cvFileName);

                if (!isValidateInputs) {
                    Toast.makeText(requireContext(), "Por favor verificar los campos", Toast.LENGTH_SHORT).show();
                } else {
                    TipoUser tipoUser = new TipoUser(idType);
                    Usuario usuario = new Usuario(mail, contraseña, tipoUser);
                    usuario.setIdUser(idUser);
                    Voluntario voluntario = new Voluntario(voluntarioLogueado.getIdVoluntario(), name, lastName, dni, phone, skills, availability, cvFileName, "");
                    voluntario.setUsuario(usuario);

                    Boolean isVoluntarioSave = mViewModel.saveVoluntarioLiveData(voluntario);

                    if (isVoluntarioSave) {

                        loadImg();

                        Toast.makeText(requireContext(), "Se editaron los datos con éxito!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "Error al guardar el voluntario, intente nuevamente", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return rootView;
    }

    private void reloadFragment() {
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_editar_perfil_voluntarios, new EditarPerfilVoluntariosFragment());
        ft.commit();
    }


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

        String url ="https://btw.com.ar/app/upload-v.php";

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

                Log.d("Aviso","id usario imagen: "+idUser);
                paramV.put("image", base64Image);
                paramV.put("usuario",String.valueOf(idUser));

                return paramV;
            }
        };
        queue.add(stringRequest);

    }

    private Boolean validateInputsVoluntarios(String name, String lastName, String dni, String skills, String phone,
                                              String availability, String mail,String contraseña, String cvFileName) {
        List<String> inputs = Arrays.asList(name, lastName, dni, dni, skills, phone, availability, mail, contraseña, cvFileName);

        Boolean isValidateInputs = validateInputs.apply(inputs);

        if (isValidateInputs) {
            return validateMail.validate(mail, contraseña);
        }
        return false;
    }

    private void examineCvFile(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType(PDF_TYPE);

        startActivityForResult(intent, RequestType.PDF.getValue());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestType.PDF.getValue()) {
            Uri selectedFileUri = data.getData();

            cvFileName = validatePdfFiles.processSelectedPdfFile(requireContext(), selectedFileUri);
        }
    }

    private void showMessage(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

}
