package com.example.side.ui.family;
import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.wafflecopter.multicontactpicker.ContactResult;
import com.wafflecopter.multicontactpicker.LimitColumn;
import com.wafflecopter.multicontactpicker.MultiContactPicker;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.side.R;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class FamilyFragment extends Fragment {

    private FamilyViewModel mViewModel;

    private static final int CONTACT_PICKER_REQUEST = 202;

    public List<ContactResult> results = new ArrayList<>();
    EditText enter_num;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_family, container, false);
        TextView enter_msg = (TextView) view.findViewById(R.id.et_msg);
        enter_num = (EditText) view.findViewById(R.id.et_num);
        Button b2 = (Button) view.findViewById(R.id.b2_sms);
        Button choose = (Button) view.findViewById(R.id.choose);

        Dexter.withContext(getContext())
                .withPermissions(
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.READ_CONTACTS
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

            }

        }).check();

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MultiContactPicker.Builder(FamilyFragment.this) //Activity/fragment context
                        .hideScrollbar(false) //Optional - default: false
                        .showTrack(true) //Optional - default: true
                        .searchIconColor(Color.WHITE) //Option - default: White
                        .setChoiceMode(MultiContactPicker.CHOICE_MODE_MULTIPLE) //Optional - default: CHOICE_MODE_MULTIPLE
//                        .handleColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)) //Optional - default: Azure Blue
//                        .bubbleColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)) //Optional - default: Azure Blue
                        .bubbleTextColor(Color.WHITE) //Optional - default: White
                        .setTitleText("Select Contacts") //Optional - default: Select Contacts
                        .setLoadingType(MultiContactPicker.LOAD_ASYNC) //Optional - default LOAD_ASYNC (wait till all loaded vs stream results)
                        .limitToColumn(LimitColumn.NONE) //Optional - default NONE (Include phone + email, limiting to one can improve loading time)
                        .setActivityAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                                android.R.anim.fade_in,
                                android.R.anim.fade_out) //Optional - default: No animation overrides
                        .showPickerForResult(CONTACT_PICKER_REQUEST);

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (!results.isEmpty()) {
                        for (int j = 0; j < results.size(); j++) {
//                            Toast.makeText(requireActivity(), position + "", Toast.LENGTH_LONG).show();
//                            Toast.makeText(requireActivity(), "SMS Sent", Toast.LENGTH_SHORT).show();
                            Toast.makeText(requireActivity(), "SMS Sent", Toast.LENGTH_SHORT).show();
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(results.get(j).getPhoneNumbers().get(0).getNumber(), null, enter_msg.getText().toString(), null, null);
                        }
                    }
//                    else {
//                        Toast.makeText(requireActivity(), "SMS Failed", Toast.LENGTH_SHORT).show();

//                    }

                } catch (Exception e) {
                    Toast.makeText(requireActivity(), " SMS failed", Toast.LENGTH_SHORT).show();
                }
            }

        });

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CONTACT_PICKER_REQUEST){
            if(resultCode == RESULT_OK) {
                results = MultiContactPicker.obtainResult(data);
                String names=results.get(0).getDisplayName();
                for(int j=0;j<results.size();j++){
                    if(j!=0)
                        names=names+ " , "+results.get(j).getDisplayName();
                }
                enter_num.setText(names);
                Log.d("MyTag", results.get(0).getDisplayName());
            } else if(resultCode == RESULT_CANCELED){
                System.out.println("User closed the picker without selecting items.");
            }
        }
    }
}