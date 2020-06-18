package com.gp.cspd.forms;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gp.cspd.R;

import java.util.List;

public class RecycleViewForm extends RecyclerView.Adapter<RecycleViewForm.ViewHolder> {

    private List<RequestFormDB> requestFormDBList;
    private Context con=null;
    private String order=null;
    private String type=null;

    private String order2=null;
    private String type2=null;

    public RecycleViewForm(List<RequestFormDB> requestFormDBList) {
        this.requestFormDBList = requestFormDBList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        con=context;
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.form_list, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final RequestFormDB formDB = requestFormDBList.get(position);

        // Set item views based on your views and data model




        if (formDB.getFormName() != null){
            String arSTR[] = formDB.getFormName().split("\\s");
            Log.d("Log876","Added "+formDB.getFormName()+" "+arSTR.length);
            if (arSTR.length == 2){
                order=arSTR[0];
                type=arSTR[1];


                if (order.equals("renew")){
                    order2="تجديد";
                }
                if (order.equals("damaged")){
                    order2="بدل تالف";
                }
                if (order.equals("lost")){
                    order2="بدل فاقد";
                }
                if (type.equals("Passport")){
                    type2="جواز سفر";
                }
                if (type.equals("Family Book")){
                    type2="دفتر عائلة";
                }
                if (type.equals("ID Card")){
                    type2="هويه مدنية";
                }
                if (order.equals("damaged or replacement")){
                    order2="بدل تالف او فاقد";
                }
                if (order.equals("first time")){
                    order2="لأول مره";
                }
                if (type.equals("Birth Certificate")){
                    type2="شهاده ميلاد";
                }

                if (type.equals("Death Certificate")){
                    type2="شهاده وفاه";
                }

            }else if (arSTR.length == 3){
                order=arSTR[0];//+" "+arSTR[1];
                type=arSTR[1]+" "+arSTR[2];

                if (order.equals("renew")){
                    order2="تجديد";
                }
                if (order.equals("damaged")){
                    order2="بدل تالف";
                }
                if (order.equals("lost")){
                    order2="بدل فاقد";
                }
                if (type.equals("Passport")){
                    type2="جواز سفر";
                }
                if (type.equals("Family Book")){
                    type2="دفتر عائلة";
                }
                if (type.equals("ID Card")){
                    type2="هويه مدنية";
                }
                if (order.equals("damaged or replacement")){
                    order2="بدل تالف او فاقد";
                }
                if (order.equals("first time")){
                    order2="لأول مره";
                }
                if (type.equals("Birth Certificate")){
                    type2="شهاده ميلاد";
                }

                if (type.equals("Death Certificate")){
                    type2="شهاده وفاه";
                }
            }else if (arSTR.length == 4){
                order=arSTR[0]+" "+arSTR[1];
                type=arSTR[2]+" "+arSTR[3];

                if (order.equals("renew")){
                    order2="تجديد";
                }
                if (order.equals("damaged")){
                    order2="بدل تالف";
                }
                if (order.equals("lost")){
                    order2="بدل فاقد";
                }
                if (type.equals("Passport")){
                    type2="جواز سفر";
                }
                if (type.equals("Family Book")){
                    type2="دفتر عائلة";
                }
                if (type.equals("ID Card")){
                    type2="هويه مدنية";
                }
                if (order.equals("damaged or replacement")){
                    order2="بدل تالف او فاقد";
                }
                if (order.equals("first time")){
                    order2="لأول مره";
                }
                if (type.equals("Birth Certificate")){
                    type2="شهاده ميلاد";
                }

                if (type.equals("Death Certificate")){
                    type2="شهاده وفاه";
                }
            }
        }

      //  holder.formName.setText(formDB.getFormName());
      //  holder.statusRes.setText(formDB.getStatus());

        holder.formName.setText(order2+" "+type2);
        String st="";
        if (formDB.getStatus().equals("in process")){
            st="قيد التدقيق";
        }if (formDB.getStatus().equals("Accepted")){
            st="مقبول";
        }if (formDB.getStatus().equals("Not Accept")){
            st="مرفوض";
        }
        holder.statusRes.setText(st);

        Log.d("test1",formDB.getFormName());
        Log.d("test2",formDB.getStatus());

        ImageView imgV = holder.formImageView;
        imgV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (con != null){
                    FormDialog formDialog = new FormDialog(con);
                    if (order!=null && type!=null){
                        formDialog.setOrd(order);
                        formDialog.setTyp(type);
                        Log.d("Log67","image form");
                        formDialog.showFormDialog();
                    }

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return requestFormDBList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView formName;
        public TextView statusRes;
        public ImageView formImageView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            super(itemView);
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.

            formName = itemView.findViewById(R.id.type_name);
            statusRes =  itemView.findViewById(R.id.status);
            formImageView = itemView.findViewById(R.id.btn_form_img);



        }
    }

}
