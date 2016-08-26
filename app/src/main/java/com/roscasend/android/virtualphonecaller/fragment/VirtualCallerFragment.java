package com.roscasend.android.virtualphonecaller.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.roscasend.android.virtualphonecaller.R;
import com.roscasend.android.virtualphonecaller.activity.MainActivity;
import com.roscasend.android.virtualphonecaller.interfaces.Callable;
import com.roscasend.android.virtualphonecaller.service.CallReceiver;
import com.roscasend.android.virtualphonecaller.service.PhoneCallService;

import java.util.ArrayList;
import java.util.List;

public class VirtualCallerFragment extends Fragment implements View.OnClickListener{

    private Callable mListener;
   // private Handler handler ;
    private String currentNumber;
    PhoneCallService service ;
    public VirtualCallerFragment() {
        // Required empty public constructor
    }

    public static VirtualCallerFragment newInstance() {
        VirtualCallerFragment fragment = new VirtualCallerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            //Restore the fragment's instance
           // mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
            currentNumber = savedInstanceState.getString("Number");

                }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_virtual_caller, container, false);
        CallReceiver  call = new CallReceiver();
        service = new PhoneCallService();
        //Find the +1 button
         view.findViewById(R.id.callButton).setOnClickListener(this);
//        handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                Toast.makeText(getContext(), " Apelul numarul " + msg.arg1, Toast.LENGTH_SHORT).show();
//            }
//        };
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh the state of the +1 button each time the activity receives focus.

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callable) {
            mListener = (Callable) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement Callable");
        }
    }

    @Override
    public void onStart() {

        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.callButton) {

//           handler.postDelayed(
//                   new MyThread()
//           ,6) ;

            new Thread(new MyThread()).start();

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("Number", currentNumber);
    }

    public  void startActionCallNumber(String callNumber) {


        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        if (callNumber == null) {
            callNumber = currentNumber;
        }

        String uri = "tel:"+callNumber;

        intent.setData(Uri.parse(uri));

        startActivity(intent);





    }

    class MyThread implements Runnable {
        @Override
        public void run() {
            List<String> numbers = new ArrayList<>();

            numbers.add("0768348005");
            numbers.add("0762500704");

            //for (int i=0; i <10;i++) {
                for (String number : numbers) {
//                    Message m = Message.obtain();
//                    m.arg1 = Integer.parseInt(number);
//                    currentNumber = number;
                    service.startActionCallNumber(getContext(), number);

                   // startActionCallNumber(number);




                }

               // break;

           // }
        }
    }

}
