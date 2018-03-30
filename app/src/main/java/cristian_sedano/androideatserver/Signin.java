package cristian_sedano.androideatserver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import cristian_sedano.androideatserver.Common.Common;
import cristian_sedano.androideatserver.Model.User;

public class Signin extends AppCompatActivity {

    EditText edtPhone, edtPassword;
    Button btnSingIn;

    FirebaseDatabase db;
    DatabaseReference user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText) findViewById(R.id.edtPhone);
        btnSingIn = (Button) findViewById(R.id.btnSignIn);

        // Init Firebase
        db = FirebaseDatabase.getInstance();
        user = db.getReference("User");

        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser(edtPhone.getText().toString(), edtPassword.getText().toString());
            }
        });
    }

    private void signInUser(final String phone, String password1) {

        final ProgressDialog mDialog = new ProgressDialog(Signin.this);
        mDialog.setMessage("Please waiting ....");
        mDialog.show();

        final String localPhone = phone;
        final String localPassword = password1;

        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(localPhone).exists())
                {
                    mDialog.dismiss();
                    User user = dataSnapshot.child(localPhone).getValue(User.class);
                    user.setPhone(localPhone);
                    if (Boolean.parseBoolean(user.getIsStaff())) // if IsStaff == true
                    {
                        if (user.getPassword().equals(localPassword))
                        {
                            Intent login = new Intent(Signin.this, Home.class);
                            Common.currentUser = user;
                            startActivity(login);
                            finish();
                        }
                        else
                            Toast.makeText(Signin.this, "Wrong password", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(Signin.this, "Please login with Staff account", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mDialog.dismiss();
                    Toast.makeText(Signin.this, "User not exist in Database", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
