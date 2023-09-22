package ks.assignment.powerpod.signIn

import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val firebaseAuth: FirebaseAuth, private val gso: GoogleSignInOptions, private val gsc: GoogleSignInClient) : ViewModel() {
    fun getFirebaseAuth(): FirebaseAuth {
        return firebaseAuth
    }

    fun getGoogleSignInOptions(): GoogleSignInOptions {
        return gso
    }

    fun getGoogleSignInClient() : GoogleSignInClient {
        return gsc;
    }
}