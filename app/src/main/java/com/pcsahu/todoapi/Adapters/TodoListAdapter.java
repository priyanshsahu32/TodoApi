package com.pcsahu.todoapi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.pcsahu.todoapi.R;
import com.pcsahu.todoapi.interfaces.RecycleViewClickListener;
import com.pcsahu.todoapi.models.TodoModel;

import java.util.ArrayList;
import java.util.Random;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.MyViewHolder> {

    ArrayList<TodoModel> arrayList;
    Context context;
    final private RecycleViewClickListener clickListener;

    public TodoListAdapter(Context context, ArrayList<TodoModel> arrayList, RecycleViewClickListener clickListener) {

        this.arrayList = arrayList;
        this.context = context;
        this.clickListener  = clickListener;


    }

    @NonNull
    @Override
    public TodoListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate( R.layout.todo_list_items,parent,false);
        final MyViewHolder myViewHolder = new MyViewHolder( view );
        int[] androidcolors = view.getResources().getIntArray( R.array.androidcolors );

        int randomColor = androidcolors[new Random().nextInt(androidcolors.length)];


        myViewHolder.accordian_title.setCardBackgroundColor( randomColor );

        myViewHolder.arrow.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewHolder.accordian_body.getVisibility() == view.VISIBLE){
                    myViewHolder.accordian_body.setVisibility( View.GONE );
                }
                else{
                    myViewHolder.accordian_body.setVisibility( View.VISIBLE );
                }
            }
        } );




        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListAdapter.MyViewHolder holder, int position) {
        final String title  = arrayList.get(position).getTitle();
        final String desc = arrayList.get(position).getDescription();
        final String id = arrayList.get(position).getId();


        holder.titletv.setText( title );
        if(!desc.equals( "" )){
            holder.desctv.setText( desc );
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CardView accordian_title;
        TextView titletv,desctv;
        public RelativeLayout accordian_body;

        ImageView arrow,editBtn,deleteBtn,doneBtn;

        public MyViewHolder(@NonNull View itemView) {
            super( itemView );

            titletv = (TextView) itemView.findViewById( R.id.task_title );
            desctv = (TextView) itemView.findViewById( R.id.task_desciption );

            accordian_title = (CardView) itemView.findViewById( R.id.accordian_title );
            accordian_body = (RelativeLayout) itemView.findViewById( R.id.accordian_body );

            arrow = (ImageView) itemView.findViewById( R.id.arrow );
            editBtn = (ImageView) itemView.findViewById( R.id.editBtn );
            deleteBtn = (ImageView) itemView.findViewById( R.id.deleteBtn );
            doneBtn = (ImageView) itemView.findViewById( R.id.doneBtn );





            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick( getAdapterPosition() );
                }
            } );

            itemView.setOnLongClickListener( new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    clickListener.onLongItemClick( getAdapterPosition() );
                    return true;
                }
            } );




            editBtn.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onEditButtonClick( getAdapterPosition() );
                }
            } );

            deleteBtn.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onDeleteButtonClick( getAdapterPosition() );
                }
            } );

            doneBtn.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onDoneButtonClick( getAdapterPosition() );
                }
            } );

        }
    }
}
