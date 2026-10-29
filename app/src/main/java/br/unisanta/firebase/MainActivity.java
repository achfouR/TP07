package br.unisanta.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView emailUsuario;
    private Button botaoSair;

    private final ActivityResultLauncher<Intent> loginLauncher =
            registerForActivityResult(
                    new FirebaseAuthUIActivityResultContract(),
                    this::receberResultadoLogin
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailUsuario = findViewById(R.id.txtEmailUsuario);
        botaoSair = findViewById(R.id.btnSair);
        botaoSair.setOnClickListener(v -> sair());

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            abrirLogin();
        } else {
            atualizarTela();
        }
    }

    private void abrirLogin() {
        List<AuthUI.IdpConfig> provedores = Collections.singletonList(
                new AuthUI.IdpConfig.EmailBuilder().build()
        );

        Intent loginIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(provedores)

                .build();

        loginLauncher.launch(loginIntent);
    }

    private void receberResultadoLogin(FirebaseAuthUIAuthenticationResult resultado) {
        if (resultado.getResultCode() == RESULT_OK) {
            atualizarTela();
            Toast.makeText(this, R.string.login_sucesso, Toast.LENGTH_SHORT).show();
        } else {
            // O usuario cancelou ou ocorreu um erro. Ao reabrir, o login aparece novamente.
            if (resultado.getIdpResponse() != null && resultado.getIdpResponse().getError() != null) {
                Toast.makeText(this, R.string.login_erro, Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }

    private void atualizarTela() {
        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
        if (usuario == null) {
            abrirLogin();
            return;
        }
        String email = usuario.getEmail();
        emailUsuario.setText(email == null ? getString(R.string.email_nao_disponivel) : email);
    }

    private void sair() {
        botaoSair.setEnabled(false);
        AuthUI.getInstance().signOut(this).addOnCompleteListener(tarefa -> {
            botaoSair.setEnabled(true);
            if (tarefa.isSuccessful()) {
                emailUsuario.setText("");
                abrirLogin();
            } else {
                Toast.makeText(this, R.string.logout_erro, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
