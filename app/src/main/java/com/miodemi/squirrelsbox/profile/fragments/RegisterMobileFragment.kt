package com.miodemi.squirrelsbox.profile.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.miodemi.squirrelsbox.databinding.FragmentRegisterMobileBinding
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_register_mobile.*
import java.util.concurrent.TimeUnit

class RegisterMobileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    private var storedVerificationId: String?= ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    //binding
    internal lateinit var binding: FragmentRegisterMobileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        //init data binding in a fragment
        binding = FragmentRegisterMobileBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root

        FirebaseApp.initializeApp(this.requireActivity())

        binding.smsBtn.setOnClickListener{
            startPhoneNumberVerification(phoneNumberEt!!.text.toString())
        }

        binding.verifBtn.setOnClickListener{
            verifyPhoneNumberWithCode(storedVerificationId,verifCode!!.text.toString())
        }

        auth=Firebase.auth

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d(TAG, "onVerificationCompleted: $credential")
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.w(TAG, "onVerificationFailed", e)

                if(e is FirebaseAuthInvalidCredentialsException){
                    //
                }else if(e is FirebaseTooManyRequestsException){

                }

            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                Log.d(TAG, "onCodeSent: $verificationId")

                storedVerificationId = verificationId
                resendToken = token
            }

        }



       ////Edit text hop
       //binding.code1Et.addTextChangedListener {
       //    if (binding.code1Et.text.toString().length == 1)
       //        binding.code2Et.requestFocus()
       //}
       //binding.code2Et.addTextChangedListener {
       //    if (binding.code2Et.text.toString().length == 1)
       //        binding.code3Et.requestFocus()
       //}
       //binding.code3Et.addTextChangedListener {
       //    if (binding.code3Et.text.toString().length == 1)
       //        binding.code4Et.requestFocus()
       //}
       //binding.code4Et.addTextChangedListener {
       //    if (binding.code4Et.text.toString().length == 1)
       //        binding.code5Et.requestFocus()
       //}
       //binding.code5Et.addTextChangedListener {
       //    if (binding.code5Et.text.toString().length == 1)
       //        binding.code6Et.requestFocus()
       //}
       ////Sending action to confirm code
       //binding.code6Et.addTextChangedListener {
       //    if (binding.code6Et.text.toString().length == 1){
//     //          binding.code1Et.text.clear()
//     //          binding.code2Et.text.clear()
//     //          binding.code3Et.text.clear()
//     //          binding.code4Et.text.clear()
//     //          binding.code5Et.text.clear()
//     //          binding.code6Et.text.clear()
       //        Toast.makeText(this.activity , "Automatically sent", Toast.LENGTH_SHORT).show()
       //    }

       //}

        // Inflate the layout for this fragment
        return view
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    private fun startPhoneNumberVerification(phoneNumber: String){
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this.requireActivity())
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String){
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)

        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential){
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this.requireActivity()){task->
                if (task.isSuccessful){
                    Log.d(TAG, "signInWhithCredential: Succes")

                    val user = task.result?.user
                    Toast.makeText(this.requireActivity(), "Welcome to Squirrel's Box: "+user, Toast.LENGTH_SHORT).show()
                }else {
                    Log.w(TAG, "signInWhithCredential: failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException){
                        //
                    }
                }
            }
    }

    private fun updateUI(user: FirebaseUser? = auth.currentUser){
        // startActivity(Intent(this, HomeActivity::class.java))
    }

    companion object{
        private const val TAG = "RegisterMobileFragment"
    }

}