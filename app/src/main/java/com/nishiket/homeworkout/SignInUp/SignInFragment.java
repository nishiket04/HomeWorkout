package com.nishiket.homeworkout.SignInUp;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentSignInBinding;
import com.nishiket.homeworkout.model.UserInsertModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;
import com.nishiket.homeworkout.user.MainActivity;
import com.nishiket.homeworkout.viewmodel.AuthViewModel;

import java.util.Arrays;
import java.util.concurrent.Executor;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignInFragment extends Fragment {

    private static final int RC_SIGN_IN = 9001;

    private FirebaseAuth mAuth;
    private FragmentSignInBinding signInBinding;
    private CallbackManager callbackManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        signInBinding = FragmentSignInBinding.inflate(inflater, container, false);
        return signInBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        mAuth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();

        AuthViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);

        // parent fragment manager
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        signInBinding.signUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft.replace(R.id.frame, new SignUpFragment()).commit();
            }
        });

        signInBinding.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                String email = signInBinding.editText2.getText().toString(); // email and convert it to string
                String pass = signInBinding.editText.getText().toString(); // password and convert it to string

                if (!email.isEmpty() && !pass.isEmpty()) { // if email and password is not empty then it will call viewModel signIn Method
                    viewModel.signIn(email, pass); // signIn method of viewModel
                    // now checking in viewModel MutableLiveData is changed or not
                    viewModel.getFirebaseUserMutableLiveData().observe(getViewLifecycleOwner(), new Observer<FirebaseUser>() {
                        @Override
                        public void onChanged(FirebaseUser firebaseUser) { // if its changed
                            if (firebaseUser != null) { // and firebaseUser its not Null then
                                Toast.makeText(getContext(), "Login Successfully", Toast.LENGTH_SHORT).show(); // make a toast that its Success
                                Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);
                                UserInsertModel userInsertModel = new UserInsertModel();
                                userInsertModel.setEmail(email);
                                userInsertModel.setName("nishiket");
                                userInsertModel.setPhone(Double.parseDouble("7405105330"));
                                Call<ResponseBody> call = retrofit.sendData(123,userInsertModel);
                                call.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (response.isSuccessful()) {
                                            // Data sent successfully
                                            Log.d("api", "onResponse: "+response.code());
                                        } else {
                                            // Error handling
//                                            Log.d("api", "onResponse: "+response.code());
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Log.d("api", "onResponse: "+t.getMessage());
                                    }
                                });
                                startActivity(i);
                                SignInUpActivity signInUpActivity = (SignInUpActivity) getActivity();
                                signInUpActivity.finish();
                            }
                        }
                    });
                } else { // else user name or password is empty then make a toast
                    Toast.makeText(getContext(), "Enter Email and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // google

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("514357553984-6jdqglnfnb99dmg2jmkhc4pjl8rojm35.apps.googleusercontent.com")
                .requestEmail()
                .build();

        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        signInBinding.logoGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInWithGoogle(mGoogleSignInClient);
            }
        });


        callbackManager = CallbackManager.Factory.create();
        signInBinding.logoFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleFacebookLogin();
            }
        });
    }

    private void handleFacebookLogin() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // Handle successful login
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // Handle canceled login
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("facebook", "handleFacebookAccessToken: fail");
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((Executor) this, task -> {
                    if (task.isSuccessful()) {
                        Log.d("facebook", "handleFacebookAccessToken: success ");
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);
                        SignInUpActivity signInUpActivity = (SignInUpActivity) getActivity();
                        signInUpActivity.finish();
                    } else {
                        Log.d("facebook", "handleFacebookAccessToken: fail");
                    }
                });
    }

    private void signInWithGoogle(GoogleSignInClient mGoogleSignInClient) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            try {
                GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w("login", "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            Log.d("login", "signInWithCredential:success");
                            // You can get the user information using task.getResult().getUser()
                            // or use mAuth.getCurrentUser() to get the current user.
                            Intent i = new Intent(getActivity(), MainActivity.class);
                            startActivity(i);
                            SignInUpActivity signInUpActivity = (SignInUpActivity) getActivity();
                            signInUpActivity.finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("login", "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }
}