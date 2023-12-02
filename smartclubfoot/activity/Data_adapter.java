//package com.ant.smartclubfoot.activity;
//
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
//import com.firebase.ui.firestore.FirestoreRecyclerOptions;
//
//public class NoteAdapter extends FirestoreRecyclerAdapter<Note,NoteAdapter.NoteViewHolder> {
//    Context context;
//
//    public NoteAdapter(@NonNull FirestoreRecyclerOptions<Note> options, Context context) {
//        super(options);
//        this.context = context;
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note note) {
//        holder.titletextview.setText(note.Title);
//        holder.contenttextview.setText(note.content);
//        holder.timetextview.setText(Utility.ts_to_string(note.timestamp));
//
//        holder.itemView.setOnClickListener((v) ->
//                {Intent intent = new Intent(context,NoteDetails.class);
//                    intent.putExtra("title",note.Title);
//                    intent.putExtra("content",note.content);
//                    String docID = this.getSnapshots().getSnapshot(position).getId();
//                    intent.putExtra("docID",docID);
//                    context.startActivity(intent);
//                }
//        );
//
//    }
//
//    @NonNull
//    @Override
//    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclernoteitem,parent,false);
//        return new NoteViewHolder(view);
//    }
//
//    class NoteViewHolder extends RecyclerView.ViewHolder{
//
//        TextView titletextview,contenttextview, timetextview;
//
//        public NoteViewHolder(@NonNull View itemView) {
//            super(itemView);
//            titletextview  = itemView.findViewById(R.id.note_title_textview);
//            contenttextview = itemView.findViewById(R.id.note_content_textview);
//            timetextview = itemView.findViewById(R.id.note_time_textview);
//
//        }
//    }
//}
